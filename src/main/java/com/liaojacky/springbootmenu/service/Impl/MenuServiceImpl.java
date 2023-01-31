package com.liaojacky.springbootmenu.service.Impl;

import com.liaojacky.springbootmenu.dao.MenuDao;
import com.liaojacky.springbootmenu.model.Menu;
import com.liaojacky.springbootmenu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public Menu getMenuById(Integer menuId) {
        return menuDao.getMenuById(menuId);
    }
}
