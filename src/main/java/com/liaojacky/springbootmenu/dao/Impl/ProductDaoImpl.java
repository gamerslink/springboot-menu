package com.liaojacky.springbootmenu.dao.Impl;

import com.liaojacky.springbootmenu.dao.ProductDao;
import com.liaojacky.springbootmenu.dto.ProductRequest;
import com.liaojacky.springbootmenu.model.Product;
import com.liaojacky.springbootmenu.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    // 依單號單筆查詢
    @Override
    public Product getProductById(Integer productId) {
        String sql = "SELECT productId, productName, price, stock FROM product WHERE productId = :productId";

        //
        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId); // 傳入SQL參數

        // 1. sql變數 2. map變數
        // 3. 轉換數據的RowMapper(將資料庫查詢的數據轉換成 Java Object)
        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if (productList.size() > 0) {
            return productList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) { // 前端傳回的參數
        String sql = "INSERT INTO product (productName, price, stock) VALUES (:productName, :price, :stock)";

        Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());

        KeyHolder keyHolder = new GeneratedKeyHolder(); // 儲存資料庫自動生成的 productId

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map), keyHolder);

        int productId = keyHolder.getKey().intValue();

        return productId;
    }
}
