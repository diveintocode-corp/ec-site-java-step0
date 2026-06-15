package com.example.ecsite.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.ecsite.entity.Product;

@Mapper
public interface ProductMapper {

        @Select("SELECT product_id, name, description, price, " +
                        "deleted,created_at, updated_at " +
                        "FROM products WHERE deleted = FALSE ORDER BY created_at DESC, product_id DESC")
        List<Product> findAllActive();

        @Select("SELECT product_id, name, description, price, " +
                        "deleted,created_at, updated_at " +
                        "FROM products ORDER BY created_at DESC, product_id DESC")
        List<Product> findAll();

        @Select("SELECT product_id, name, description, price, " +
                        "deleted,created_at, updated_at " +
                        "FROM products WHERE product_id = #{productId} AND deleted = FALSE")
        Product findActiveById(Long productId);

        @Select("SELECT product_id, name, description, price, " +
                        "deleted,created_at, updated_at " +
                        "FROM products WHERE product_id = #{productId}")
        Product findById(Long productId);

        @Insert("INSERT INTO products (name, description, price, deleted) " +
                        "VALUES (#{name}, #{description}, #{price}, #{deleted})")
        @Options(useGeneratedKeys = true, keyProperty = "productId")
        void insert(Product product);

        @Update("UPDATE products SET name = #{name}, description = #{description}, " +
                        "price = #{price}, deleted = #{deleted}, " +
                        "updated_at = CURRENT_TIMESTAMP WHERE product_id = #{productId}")
        void update(Product product);

        @Update("UPDATE products SET deleted = TRUE, " +
                        "updated_at = CURRENT_TIMESTAMP WHERE product_id = #{productId}")
        void softDelete(Long productId);
}
