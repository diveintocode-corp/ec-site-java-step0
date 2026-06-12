-- Production seed: 95 product_images (PostgreSQL, idempotent)
-- S3_BASE_URL is replaced at runtime with configured S3 bucket URL

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (1, 3, 'S3_BASE_URL/image33-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (3, 1, 'S3_BASE_URL/image31-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (4, 4, 'S3_BASE_URL/image30-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (5, 5, 'S3_BASE_URL/image29-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (6, 6, 'S3_BASE_URL/image28-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (7, 7, 'S3_BASE_URL/image27-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (8, 8, 'S3_BASE_URL/image26-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (9, 9, 'S3_BASE_URL/image25-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (10, 10, 'S3_BASE_URL/image24-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (11, 11, 'S3_BASE_URL/image23-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (12, 12, 'S3_BASE_URL/image22-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (13, 13, 'S3_BASE_URL/image21-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (14, 14, 'S3_BASE_URL/image20-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (15, 15, 'S3_BASE_URL/image19-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (16, 16, 'S3_BASE_URL/image18-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (17, 17, 'S3_BASE_URL/image17-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (18, 18, 'S3_BASE_URL/image16-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (19, 19, 'S3_BASE_URL/image15-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (20, 20, 'S3_BASE_URL/image14-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (21, 21, 'S3_BASE_URL/image13-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (23, 23, 'S3_BASE_URL/image11-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (24, 24, 'S3_BASE_URL/image10-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (25, 25, 'S3_BASE_URL/image9-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (26, 26, 'S3_BASE_URL/image8-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (27, 27, 'S3_BASE_URL/image7-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (28, 28, 'S3_BASE_URL/image6-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (29, 29, 'S3_BASE_URL/image5-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (30, 30, 'S3_BASE_URL/image4-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (31, 31, 'S3_BASE_URL/image3-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (32, 32, 'S3_BASE_URL/image2-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (33, 33, 'S3_BASE_URL/image1-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (34, 33, 'S3_BASE_URL/image1-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (35, 33, 'S3_BASE_URL/image1-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (36, 31, 'S3_BASE_URL/image3-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (37, 31, 'S3_BASE_URL/image3-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (38, 30, 'S3_BASE_URL/image4-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (39, 30, 'S3_BASE_URL/image4-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (40, 29, 'S3_BASE_URL/image5-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (41, 29, 'S3_BASE_URL/image5-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (42, 28, 'S3_BASE_URL/image6-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (43, 27, 'S3_BASE_URL/image7-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (44, 27, 'S3_BASE_URL/image7-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (45, 26, 'S3_BASE_URL/image8-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (46, 25, 'S3_BASE_URL/image9-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (47, 25, 'S3_BASE_URL/image9-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (48, 24, 'S3_BASE_URL/image10-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (49, 24, 'S3_BASE_URL/image10-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (50, 23, 'S3_BASE_URL/image11-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (51, 23, 'S3_BASE_URL/image11-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (52, 22, 'S3_BASE_URL/image12-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (53, 22, 'S3_BASE_URL/image12-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (54, 22, 'S3_BASE_URL/image12-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (55, 21, 'S3_BASE_URL/image13-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (56, 21, 'S3_BASE_URL/image13-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (57, 20, 'S3_BASE_URL/image14-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (58, 20, 'S3_BASE_URL/image14-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (59, 19, 'S3_BASE_URL/image15-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (60, 19, 'S3_BASE_URL/image15-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (61, 18, 'S3_BASE_URL/image16-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (62, 18, 'S3_BASE_URL/image16-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (63, 17, 'S3_BASE_URL/image17-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (64, 17, 'S3_BASE_URL/image17-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (65, 16, 'S3_BASE_URL/image18-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (66, 16, 'S3_BASE_URL/image18-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (67, 15, 'S3_BASE_URL/image19-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (68, 15, 'S3_BASE_URL/image19-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (69, 14, 'S3_BASE_URL/image20-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (70, 14, 'S3_BASE_URL/image20-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (71, 13, 'S3_BASE_URL/image21-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (72, 13, 'S3_BASE_URL/image21-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (73, 12, 'S3_BASE_URL/image22-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (74, 12, 'S3_BASE_URL/image22-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (75, 11, 'S3_BASE_URL/image23-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (76, 11, 'S3_BASE_URL/image23-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (77, 10, 'S3_BASE_URL/image24-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (78, 10, 'S3_BASE_URL/image24-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (79, 9, 'S3_BASE_URL/image25-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (80, 9, 'S3_BASE_URL/image25-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (81, 8, 'S3_BASE_URL/image26-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (82, 8, 'S3_BASE_URL/image26-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (83, 7, 'S3_BASE_URL/image27-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (84, 7, 'S3_BASE_URL/image27-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (85, 6, 'S3_BASE_URL/image28-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (86, 6, 'S3_BASE_URL/image28-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (87, 5, 'S3_BASE_URL/image29-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (88, 5, 'S3_BASE_URL/image29-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (89, 4, 'S3_BASE_URL/image30-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (90, 4, 'S3_BASE_URL/image30-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (91, 1, 'S3_BASE_URL/image31-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (92, 1, 'S3_BASE_URL/image31-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (99, 3, 'S3_BASE_URL/image33-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (100, 3, 'S3_BASE_URL/image33-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (101, 2, 'S3_BASE_URL/image32-1.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (102, 2, 'S3_BASE_URL/image32-2.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;

INSERT INTO product_images (product_image_id, product_id, image_path, created_at, updated_at)
VALUES (103, 2, 'S3_BASE_URL/image32-3.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (product_image_id) DO NOTHING;
