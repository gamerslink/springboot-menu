package com.liaojacky.springbootmenu.rowmapper;

import com.liaojacky.springbootmenu.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    // ResultSet 資料庫儲存的數據
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {

        Product product = new Product(); // 將資料庫儲存到 menu
        product.setProductId(resultSet.getInt("productId"));
        product.setProductName(resultSet.getString("productName"));
        product.setPrice(resultSet.getInt("price"));
        product.setStock(resultSet.getInt("stock"));

        return product;
    }
}
