# AWS EC2 Deployment Guide

This document describes the configuration and deployment of the ec-site application on AWS EC2.

## GitHub Actions Secrets

The deploy workflow requires these secrets (repository-level):

| Secret | Description |
|---|---|
| `AWS_EC2_HOST` | Public DNS or IP of the EC2 instance |
| `AWS_EC2_USER` | SSH username (e.g., `ec2-user`) |
| `AWS_EC2_SSH_KEY` | Private SSH key in PEM format |

No database credentials are stored in GitHub — they live on the EC2 instance only.

## EC2 Directory Structure

| Path | Purpose |
|---|---|
| `/home/ec2-user/app/` | Deployed JAR file (`ec-site.jar`) |
| `/home/ec2-user/data/` | H2 database file storage (local development only; unused in prod) |
| `/home/ec2-user/uploads/` | Product image uploads |
| `/etc/ec-site/db.conf` | PostgreSQL connection credentials (owned by root, mode 600) |

## systemd Service

**Location:** `/etc/systemd/system/ec-site.service`

**Key configuration:**

```
[Service]
User=ec2-user
WorkingDirectory=/home/ec2-user/app
EnvironmentFile=/etc/ec-site/db.conf
ExecStart=/usr/bin/java -jar /home/ec2-user/app/ec-site.jar --spring.profiles.active=prod
Restart=on-failure
RestartSec=5
```

**Management commands:**

```bash
sudo systemctl status ec-site     # Check status
sudo systemctl stop ec-site       # Stop
sudo systemctl start ec-site      # Start
sudo systemctl restart ec-site    # Restart
sudo journalctl -u ec-site -f     # Follow logs
```

## Nginx Configuration

**Location:** `/etc/nginx/conf.d/ec-site.conf`

The canonical template is at `deploy/nginx/ec-site.conf` in this repository. Copy it to the server and replace `<HTPASSWD_FILE_PATH>` with the actual path before reloading.

**Key configuration:**

```
server {
    listen 80;
    server_name _;

    auth_basic "Student Training Environment";
    auth_basic_user_file <HTPASSWD_FILE_PATH>;

    # Prevent search engine indexing at the HTTP layer (server level applies to all locations)
    add_header X-Robots-Tag "noindex, nofollow, noarchive" always;

    location / {
        proxy_pass http://127.0.0.1:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```

**Management commands:**

```bash
sudo systemctl status nginx       # Check status
sudo nginx -t                     # Test configuration
sudo systemctl reload nginx       # Reload configuration
```

## Basic Authentication Setup

The student training environment requires Basic Authentication at the Nginx layer. This protects the entire site before requests reach Spring Boot. The application-level Spring Security login is unaffected.

**Training credential:** `student` / `password`

### Create the htpasswd file

```bash
# Install apache2-utils if not present
sudo apt-get install -y apache2-utils   # Debian/Ubuntu
# or
sudo yum install -y httpd-tools         # Amazon Linux / RHEL

# Create the htpasswd file with bcrypt (-B); enter "password" when prompted
sudo htpasswd -cB /etc/nginx/.htpasswd student
```

### Apply to Nginx config

Replace `<HTPASSWD_FILE_PATH>` in `/etc/nginx/conf.d/ec-site.conf` with `/etc/nginx/.htpasswd`, then reload:

```bash
sudo nginx -t && sudo systemctl reload nginx
```

### Verify Basic Authentication is active

```bash
# Should return 401 without credentials
curl -I http://<EC2_PUBLIC_IP>/

# Should return 200 (or 302) with correct credentials
curl -I -u student:password http://<EC2_PUBLIC_IP>/
```

## NOINDEX Protection

Two layers of noindex protection are configured:

| Layer | Location | What it does |
|---|---|---|
| HTML meta tag | `layout.html`, `layout-admin.html` | Instructs crawlers in rendered HTML |
| HTTP header | Nginx `add_header X-Robots-Tag` | Applies to all responses regardless of content type |
| `robots.txt` | `src/main/resources/static/robots.txt` | `Disallow: /` for all crawlers |

### Verify NOINDEX is active

```bash
# X-Robots-Tag header present
curl -sI -u student:password http://<EC2_PUBLIC_IP>/ | grep -i x-robots

# robots.txt returns Disallow: /
curl -s -u student:password http://<EC2_PUBLIC_IP>/robots.txt
```

Expected outputs:

```
x-robots-tag: noindex, nofollow, noarchive

User-agent: *
Disallow: /
```

## PostgreSQL Connection (RDS)

**Credentials file:** `/etc/ec-site/db.conf`

```
DB_HOST=<rds-endpoint>
DB_NAME=ecsitedb
DB_USER=ecsiteadmin
DB_PASSWORD=<password>
```

Accessed by systemd via `EnvironmentFile` — never committed to the repository. The `application-prod.properties` reads all values from environment variables with no fallback defaults.

## Spring Profiles

| Profile | Active in | Database | Port | Flyway |
|---|---|---|---|---|
| Default (none) | Local development | H2 (file-based) | 8080 | Disabled |
| `prod` | EC2 deployment | PostgreSQL (RDS) | 8080 | Enabled |

The `prod` profile is activated by systemd via `--spring.profiles.active=prod`.

## How to Verify Deployment

### From outside the server

```bash
# With Basic Auth active: returns 401 (Nginx challenges before Spring Boot)
curl -I http://<EC2_PUBLIC_IP>/

# With credentials: returns 302 (Spring Security redirect to login)
curl -I -u student:password http://<EC2_PUBLIC_IP>/
```

### On the server

```bash
# Service running?
sudo systemctl status ec-site
# Active: active (running)

# Database connected?
sudo journalctl -u ec-site --no-pager | grep "HikariPool"
# HikariPool-1 - Added connection org.postgresql.jdbc.PgConnection

# Flyway migrated?
sudo journalctl -u ec-site --no-pager | grep Flyway
# Database: jdbc:postgresql://... (PostgreSQL 16.9)

# Ports listening?
sudo ss -tlnp | grep -E '80|8080'
# nginx on :80, java on :8080
```

## How to Check Logs When Deployment Fails

### GitHub Actions

1. Go to **Actions** tab → click the failed run
2. Expand the failing step
3. Common failures:
   - **Action not found** — stale action version tag
   - **Permission denied (SCP)** — SSH key or secrets misconfigured
   - **systemctl failed** — check journal on the server

### On the EC2 server

```bash
# Last 50 lines of application logs
sudo journalctl -u ec-site --no-pager -n 50

# Since last boot
sudo journalctl -u ec-site -b --no-pager

# Filter for errors
sudo journalctl -u ec-site --no-pager | grep -i ERROR

# Nginx error log
sudo tail -50 /var/log/nginx/error.log
```
