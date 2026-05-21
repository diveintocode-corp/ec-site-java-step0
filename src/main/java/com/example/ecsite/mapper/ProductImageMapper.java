package com.example.ecsite.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.ecsite.entity.ProductImage;

@Mapper
public interface ProductImageMapper {

    @Select("SELECT * FROM product_images WHERE product_id = #{productId} ORDER BY created_at ASC")
    List<ProductImage> findByProductId(Long productId);

    @Select("SELECT * FROM product_images WHERE product_image_id = #{id}")
    ProductImage findById(Long id);

    @Insert("INSERT INTO product_images (product_id, image_path) VALUES (#{productId}, #{imagePath})")
    @Options(useGeneratedKeys = true, keyProperty = "productImageId")
    void insert(ProductImage productImage);

    @Delete("DELETE FROM product_images WHERE product_image_id = #{id}")
    void deleteById(Long id);

    @Update("UPDATE product_images SET image_path = #{imagePath}, " +
            "updated_at = CURRENT_TIMESTAMP WHERE product_image_id = #{productImageId}")
    void update(ProductImage productImage);

    @Select("SELECT COUNT(*) FROM product_images WHERE product_id = #{productId}")
    int countByProductId(Long productId);
}
