package com.ciel.wj.controller;

import com.ciel.wj.pojo.AdminMenu;
import com.ciel.wj.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {
    @Autowired
    AdminMenuService adminMenuService;

    @GetMapping("/api/menu")
    public List<AdminMenu> menu() {
        List<AdminMenu> menus = adminMenuService.getMenusByCurrentUser();
        return menus;
    }

    @GetMapping("/api/admin/role/menu")
    public List<AdminMenu> listAllMenus() {
        List<AdminMenu> menus = adminMenuService.getMenusByRoleId(1);
        return menus;
    }
}
