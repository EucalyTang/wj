package com.ciel.wj.service;

import com.ciel.wj.dao.AdminPermissionDAO;
import com.ciel.wj.pojo.AdminPermission;
import com.ciel.wj.pojo.AdminRole;
import com.ciel.wj.pojo.AdminRolePermission;
import com.ciel.wj.pojo.AdminUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AdminPermmsionService {
    @Autowired
    AdminPermissionDAO adminPermissionDAO;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    @Autowired
    AdminRolePermissionService adminRolePermissionService;
    @Autowired
    AdminRoleService adminRoleService;
    @Autowired
    UserService userService;

    public AdminPermission findById(int id) {
        return adminPermissionDAO.findById(id);
    }

    public List<AdminPermission> list() {
        return adminPermissionDAO.findAll();
    }

    public List<AdminPermission> listPermsByRoleId(int rid) {
        List<AdminRolePermission> rps = adminRolePermissionService.findAllByRid(rid);
        List<AdminPermission> perms = new ArrayList<>();
        for (AdminRolePermission rp : rps) {
            perms.add(adminPermissionDAO.findById(rp.getPid()));
        }
        return perms;
    }

    public Set<String> listPermsURLsByUser(String username) {
        int uid = userService.getByUserName(username).getId();
        List<AdminRole> roles = new ArrayList<>();
        List<AdminPermission> permissions = new ArrayList<>();
        Set<String> URLs = new HashSet<>();

        List<AdminUserRole> urs = adminUserRoleService.listAllByUid(uid);
        for (AdminUserRole ur : urs) {
            roles.add(adminRoleService.findById(ur.getRid()));
        }
        for (AdminRole role : roles) {
            List<AdminRolePermission> rps = adminRolePermissionService.findAllByRid(role.getId());
            for (AdminRolePermission rp : rps) {
                URLs.add(adminPermissionDAO.findById(rp.getPid()).getUrl());
            }
        }
        return URLs;
    }

    public boolean needFilter(String requestAPI) {
        List<AdminPermission> perms = adminPermissionDAO.findAll();
        for (AdminPermission perm : perms) {
            if (perm.getUrl().equals(requestAPI)) {
                return true;
            }
        }
        return false;
    }
}
