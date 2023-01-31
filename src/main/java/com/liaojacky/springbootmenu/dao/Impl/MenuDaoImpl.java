package com.liaojacky.springbootmenu.dao.Impl;

import com.liaojacky.springbootmenu.dao.MenuDao;
import com.liaojacky.springbootmenu.model.Menu;
import com.liaojacky.springbootmenu.rowmapper.MenuRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MenuDaoImpl implements MenuDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Menu getMenuById(Integer menuId) {
        String sql = "SELECT menu_id, menu_name, category, image_url, price, stock, " +
                "description, create_date, last_modified_date " +
                "FROM menu WHERE menu_id = :menuId";

        Map<String, Object> map = new HashMap<>();
        map.put("menuId", menuId);

        List<Menu> menuList = namedParameterJdbcTemplate.query(sql, map, new MenuRowMapper());

        if (menuList.size() > 0) {
            return menuList.get(0);
        } else {
            return null;
        }
    }
}
