package com.ciel.wj.controller;

import com.ciel.wj.pojo.User;
import com.ciel.wj.result.Result;
import com.ciel.wj.result.ResultFactory;
import com.ciel.wj.service.AdminUserRoleService;
import com.ciel.wj.service.UserService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;

    @GetMapping("/api/admin/user")
    public List<User> listUsers() throws Exception {
        return userService.list();
    }

    @PutMapping("/api/admin/user/status")
    public Result updateUserStatus(@RequestBody User requestUser) {
        User user = userService.getByUserName(requestUser.getUsername());
        user.setEnabled(requestUser.isEnabled());
        userService.addOrUpdate(user);
        String message = "用户" + requestUser.getUsername() + "状态更新成功";
        return ResultFactory.buildSuccessResult(message);
    }

    @PutMapping("/api/admin/user/password")
    public Result resetPassword(@RequestBody User requestUser) {
        User user = userService.getByUserName(requestUser.getUsername());
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        user.setSalt(salt);
        String encodedPassword = new SimpleHash("md5","123",salt,times).toString();
        user.setPassword(encodedPassword);
        userService.addOrUpdate(user);
        String message = "密码重置成功";
        return ResultFactory.buildSuccessResult(message);
    }

    @PutMapping("/api/admin/user")
    public Result editUser(@RequestBody User requestUser) {
        User user = userService.getByUserName(requestUser.getUsername());
        user.setName(requestUser.getName());
        user.setPhone(requestUser.getPhone());
        user.setEmail(requestUser.getEmail());
        userService.addOrUpdate(user);
        adminUserRoleService.saveRoleChanges(user.getId(),requestUser.getRoles());
        String message = "修改用户信息成功";
        return ResultFactory.buildSuccessResult(message);
    }
}
