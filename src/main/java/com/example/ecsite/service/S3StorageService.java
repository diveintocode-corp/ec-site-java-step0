package com.example.ecsite.service;

import java.io.InputStream;

import jakarta.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
@ConditionalOnProperty(name = "app.s3.bucket")
public class S3StorageService {

    private final S3Client s3;
    private final String bucket;
    private final String region;

    public S3StorageService(
            @Value("${app.s3.bucket}") String bucket,
            @Value("${app.s3.region}") String region) {
        this.bucket = bucket;
        this.region = region;
        this.s3 = S3Client.builder().region(Region.of(region)).build();
    }

    public boolean exists(String key) {
        try {
            s3.headObject(HeadObjectRequest.builder().bucket(bucket).key(key).build());
            return true;
        } catch (NoSuchKeyException e) {
            return false;
        } catch (S3Exception e) {
            throw new IllegalStateException(
                "S3 check failed for s3://" + bucket + "/" + key +
                " (HTTP " + e.statusCode() + "): " + e.awsErrorDetails().errorMessage(), e);
        } catch (SdkClientException e) {
            throw new IllegalStateException(
                "S3 connectivity error for bucket '" + bucket + "': " + e.getMessage(), e);
        }
    }

    @PreDestroy
    public void close() {
        s3.close();
    }

    public String upload(String key, byte[] content, String contentType) {
        s3.putObject(
            PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .contentType(contentType)
                .build(),
            RequestBody.fromBytes(content)
        );
        return publicUrl(key);
    }

    public String upload(String key, InputStream in, long contentLength, String contentType) {
        s3.putObject(
            PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .contentType(contentType)
                .build(),
            RequestBody.fromInputStream(in, contentLength)
        );
        return publicUrl(key);
    }

    public void delete(String key) {
        s3.deleteObject(DeleteObjectRequest.builder().bucket(bucket).key(key).build());
    }

    public String publicUrl(String key) {
        return "https://" + bucket + ".s3." + region + ".amazonaws.com/" + key;
    }

    public String s3BaseUrl() {
        return "https://" + bucket + ".s3." + region + ".amazonaws.com";
    }
}
