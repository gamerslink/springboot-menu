package com.liaojacky.springbootmenu.controller;

import com.liaojacky.springbootmenu.model.Menu;
import com.liaojacky.springbootmenu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/menus/{menuId}")
    public ResponseEntity<Menu> getMenu(@PathVariable Integer menuId) {
        Menu menu = menuService.getMenuById(menuId);

        if (menu != null) {
            return ResponseEntity.status(HttpStatus.OK).body(menu);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
