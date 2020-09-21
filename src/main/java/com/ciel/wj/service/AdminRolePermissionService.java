package com.ciel.wj.service;

import com.ciel.wj.dao.AdminRolePermissionDAO;
import com.ciel.wj.pojo.AdminPermission;
import com.ciel.wj.pojo.AdminRolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminRolePermissionService {
    @Autowired
    AdminRolePermissionDAO adminRolePermissionDAO;

    List<AdminRolePermission> findAllByRid(int rid) {
        return adminRolePermissionDAO.findAllByRid(rid);
    }

    @Transactional
    public void savePermChanges(int rid, List<AdminPermission> perms) {
        adminRolePermissionDAO.deleteAllByRid(rid);
//        for (AdminPermission perm : perms) {
//            AdminRolePermission rp = new AdminRolePermission();
//            rp.setRid(rid);
//            rp.setPid(perm.getId());
//            adminRolePermissionDAO.save(rp);
//        }
        List<AdminRolePermission> rps = new ArrayList<>();
        perms.forEach(p -> {
            AdminRolePermission rp = new AdminRolePermission();
            rp.setRid(rid);
            rp.setPid(p.getId());
            rps.add(rp);
        });
        adminRolePermissionDAO.saveAll(rps);
    }
}
