package com.liaojacky.springbootmenu.dao;

import com.liaojacky.springbootmenu.model.Menu;

public interface MenuDao {

    Menu getMenuById(Integer menuId);
}
