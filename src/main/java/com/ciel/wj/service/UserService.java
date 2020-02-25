package com.ciel.wj.service;

import com.ciel.wj.dao.UserDAO;
import com.ciel.wj.pojo.AdminRole;
import com.ciel.wj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDAO userDao;
    @Autowired
    AdminRoleService adminRoleService;

    public boolean isExist(String username) {
        User user = getByUserName(username);
        return null!=user;
    }

    public User getByUserName(String username) {
        return userDao.findByUsername(username);
    }

    public User get(String username, String password) {
        return userDao.getByUsernameAndPassword(username, password);
    }

    public void addOrUpdate(User user) {
        userDao.save(user);
    }

    public List<User> list() {
        List<User> users = userDao.list();
        List<AdminRole> roles;
        for (User user:users) {
            roles = adminRoleService.listRolesByUser(user.getUsername());
            user.setRoles(roles);
        }
        return users;
    }
}
