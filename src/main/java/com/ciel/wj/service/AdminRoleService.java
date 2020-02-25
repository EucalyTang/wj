package com.ciel.wj.service;

import com.ciel.wj.dao.AdminRoleDAO;
import com.ciel.wj.pojo.AdminMenu;
import com.ciel.wj.pojo.AdminPermission;
import com.ciel.wj.pojo.AdminRole;
import com.ciel.wj.pojo.AdminUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminRoleService {
    @Autowired
    AdminRoleDAO adminRoleDAO;
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    @Autowired
    AdminPermmsionService adminPermmsionService;
    @Autowired
    AdminMenuService adminMenuService;

    public List<AdminRole> list() {
        List<AdminRole> roles = adminRoleDAO.findAll();
        List<AdminPermission> perms;
        List<AdminMenu> menus;
        for (AdminRole role : roles) {
            perms = adminPermmsionService.listPermsByRoleId(role.getId());
            menus = adminMenuService.getMenusByRoleId(role.getId());
            role.setMenus(menus);
            role.setPerms(perms);
        }
        return roles;
    }

    public AdminRole findById(int id) {
        return adminRoleDAO.findById(id);
    }

    public void addOrUpdate(AdminRole adminRole) {
        adminRoleDAO.save(adminRole);
    }

    public List<AdminRole> listRolesByUser(String username) {
        int uid = userService.getByUserName(username).getId();
        List<AdminRole> roles = new ArrayList<>();
        List<AdminUserRole> urs = adminUserRoleService.listAllByUid(uid);
        for (AdminUserRole ur : urs) {
            roles.add(adminRoleDAO.findById(ur.getRid()));
        }
        return roles;
    }
}
