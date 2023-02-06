package com.liaojacky.springbootmenu.dao.Impl;

import com.liaojacky.springbootmenu.dao.ProductDao;
import com.liaojacky.springbootmenu.model.Product;
import com.liaojacky.springbootmenu.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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


}
