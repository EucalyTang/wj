package com.ciel.wj.service;

import com.ciel.wj.dao.AdminRoleDAO;
import com.ciel.wj.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminRoleService {
    @Autowired
    AdminRoleDAO adminRoleDAO;
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    @Autowired
    AdminPermissionService adminPermissionService;
    @Autowired
    AdminRolePermissionService adminRolePermissionService;
    @Autowired
    AdminMenuService adminMenuService;

//    public List<AdminRole> listWithPermsAndMenus() {
//        List<AdminRole> roles = adminRoleDAO.findAll();
//        List<AdminPermission> perms;
//        List<AdminMenu> menus;
//        for (AdminRole role : roles) {
//            perms = adminPermissionService.listPermsByRoleId(role.getId());
//            menus = adminMenuService.getMenusByRoleId(role.getId());
//            role.setPerms(perms);
//            role.setMenus(menus);
//        }
//        return roles;
//    }
    public List<AdminRole> listWithPermsAndMenus() {
        List<AdminRole> roles = adminRoleDAO.findAll();
        List<AdminPermission> perms;
        List<AdminMenu> menus;
        for (AdminRole role : roles) {
            perms = adminPermissionService.listPermsByRoleId(role.getId());
            menus = adminMenuService.getMenusByRoleId(role.getId());
            role.setPerms(perms);
            role.setMenus(menus);
        }
        return roles;
    }

    public List<AdminRole> findAll() {
        return adminRoleDAO.findAll();
    }

//    public List<AdminRole> list() {
//        List<AdminRole> roles = adminRoleDAO.findAll();
//        List<AdminPermission> perms;
//        List<AdminMenu> menus;
//        for (AdminRole role : roles) {
//            perms = adminPermmsionService.listPermsByRoleId(role.getId());
//            menus = adminMenuService.getMenusByRoleId(role.getId());
//            role.setMenus(menus);
//            role.setPerms(perms);
//        }
//        return roles;
//    }

//    public AdminRole findById(int id) {
//        return adminRoleDAO.findById(id);
//    }

    public void addOrUpdate(AdminRole adminRole) {
        adminRoleDAO.save(adminRole);
    }

    public List<AdminRole> listRolesByUser(String username) {
        int uid = userService.findByUsername(username).getId();
//        List<AdminRole> roles = new ArrayList<>();
//        List<AdminUserRole> urs = adminUserRoleService.listAllByUid(uid);
//        for (AdminUserRole ur : urs) {
//            roles.add(adminRoleDAO.findById(ur.getRid()));
//        }
//        return roles;
        List<Integer> rids = adminUserRoleService.listAllByUid(uid)
                .stream().map(AdminUserRole::getRid).collect(Collectors.toList());
        return adminRoleDAO.findAllById(rids);
    }

    public List<Discount> listDiscountsByUser(String username) {
        int uid = userService.findByUsername(username).getId();
        List<Discount> discounts = new ArrayList<>();
        List<AdminUserRole> urs = adminUserRoleService.listAllByUid(uid);
        for (AdminUserRole ur : urs) {
            discounts.add(adminRoleDAO.findById(ur.getRid()).getRoleDiscount());
        }
        return discounts;
    }

    public AdminRole updateRoleStatus(AdminRole role) {
        AdminRole roleInDB = adminRoleDAO.findById(role.getId());
        roleInDB.setEnabled(role.isEnabled());
        return adminRoleDAO.save(roleInDB);
    }

    public void editRole(@RequestBody AdminRole role) {
        adminRoleDAO.save(role);
        adminRolePermissionService.savePermChanges(role.getId(), role.getPerms());
    }
}
