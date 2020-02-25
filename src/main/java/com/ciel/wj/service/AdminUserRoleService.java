package com.ciel.wj.service;

import com.ciel.wj.dao.AdminUserRoleDAO;
import com.ciel.wj.pojo.AdminRole;
import com.ciel.wj.pojo.AdminUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminUserRoleService {
    @Autowired
    AdminUserRoleDAO adminUserRoleDAO;

    public List<AdminUserRole> listAllByUid(int uid) {
        return adminUserRoleDAO.findAllByUid(uid);
    }

    @Transactional
    public void saveRoleChanges(int uid, List<AdminRole> roles) {
        adminUserRoleDAO.findAllByUid(uid);
        for (AdminRole role:roles) {
            AdminUserRole ur = new AdminUserRole();
            ur.setUid(uid);
            ur.setRid(role.getId());
            adminUserRoleDAO.save(ur);
        }
    }
}
