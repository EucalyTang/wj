package com.ciel.wj.controller;

import com.ciel.wj.config.Log;
import com.ciel.wj.pojo.Member;
import com.ciel.wj.pojo.User;
import com.ciel.wj.result.Result;
import com.ciel.wj.result.ResultFactory;
import com.ciel.wj.service.AdminUserRoleService;
import com.ciel.wj.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;

//    @GetMapping("/api/admin/user")
//    public List<User> listUsers() throws Exception {
//        return userService.list();
//    }
    @GetMapping("/api/admin/user")
    public Result listUsers() {
        return ResultFactory.buildSuccessResult(userService.list());
    }

//    @GetMapping("api/admin/user/{size}/{page}")
//    public Page listUsers(@PathVariable("size") int size, @PathVariable("page") int page) {
//        return userService.list(page-1, size);
//    }

    @GetMapping("/api/admin/user/members")
    public List<Member> listUserMembers() throws Exception {
        String userName = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.findByUsername(userName);
        return  user.getMembers();
    }

    @Log("审核用户")
    @PutMapping("/api/admin/user/status")
    public Result updateUserStatus(@RequestBody @Valid User requestUser) {
        userService.updateUserStatus(requestUser);
        return ResultFactory.buildSuccessResult("用户状态更新成功");
    }
//    @PutMapping("/api/admin/user/status")
//    public Result updateUserStatus(@RequestBody User requestUser) {
//        User user = userService.getByUserName(requestUser.getUsername());
//        user.setEnabled(requestUser.isEnabled());
//        userService.addOrUpdate(user);
//        String message = "用户" + requestUser.getUsername() + "状态更新成功";
//        return ResultFactory.buildSuccessResult(message);
//    }

    @Log("重置密码")
    @PutMapping("/api/admin/user/password")
    public Result resetPassword(@RequestBody @Valid User requestUser) {
        userService.resetPassword(requestUser);
        return ResultFactory.buildSuccessResult("重置密码成功");
    }
//    public Result resetPassword(@RequestBody User requestUser) {
//        User user = userService.getByUserName(requestUser.getUsername());
//        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
//        int times = 2;
//        user.setSalt(salt);
//        String encodedPassword = new SimpleHash("md5","12345678",salt,times).toString();
//        user.setPassword(encodedPassword);
//        userService.addOrUpdate(user);
//        String message = "密码重置成功";
//        return ResultFactory.buildSuccessResult(message);
//    }

    @Log("修改密码")
    @PutMapping("/api/admin/user/passwordchange")
    public Result changePassword(String newPassword) {
        String userName = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.findByUsername(userName);
        if (newPassword !=null && newPassword.length() != 0) {
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            int times = 2;
            user.setSalt(salt);
            String encodedPassword = new SimpleHash("md5",newPassword,salt,times).toString();
            userService.addOrUpdate(user);
            String message = "密码修改成功";
            return ResultFactory.buildSuccessResult(message);
        } else {
            String message = "密码为空";
            return ResultFactory.buildFailResult(message);
        }
    }

    @PutMapping("/api/admin/user")
    @Log("修改用户信息")
    public Result editUser(@RequestBody @Valid User requestUser) {
        userService.editUser(requestUser);
        return ResultFactory.buildSuccessResult("修改用户信息成功");
    }
//    public Result editUser(@RequestBody User requestUser) {
//        //String currentUser = SecurityUtils.getSubject().getPrincipal().toString();
//        User user = userService.getByUserName(requestUser.getUsername());
//        user.setName(requestUser.getName());
//        user.setPhone(requestUser.getPhone());
//        user.setEmail(requestUser.getEmail());
//        userService.addOrUpdate(user);
//        adminUserRoleService.saveRoleChanges(user.getId(),requestUser.getRoles());
//        String message = "修改用户信息成功";
//        return ResultFactory.buildSuccessResult(message);
//    }
}
