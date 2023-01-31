package com.liaojacky.springbootmenu.rowmapper;

import com.liaojacky.springbootmenu.constant.MenuCategory;
import com.liaojacky.springbootmenu.model.Menu;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuRowMapper implements RowMapper<Menu> {

    @Override
    public Menu mapRow(ResultSet resultSet, int i) throws SQLException {
        Menu menu = new Menu();

        menu.setMenuId(resultSet.getInt("menu_id"));
        menu.setMenuName(resultSet.getString("menu_name"));

        menu.setCategory(MenuCategory.valueOf(resultSet.getString("category")));

        menu.setImageUrl(resultSet.getString("image_url"));
        menu.setPrice(resultSet.getInt("price"));
        menu.setStock(resultSet.getInt("stock"));
        menu.setDescription(resultSet.getString("description"));
        menu.setCreateDate(resultSet.getTimestamp("create_date"));
        menu.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));

        return menu;
    }
}
