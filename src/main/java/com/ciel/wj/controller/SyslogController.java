package com.ciel.wj.controller;


import com.ciel.wj.pojo.User;
import com.ciel.wj.service.SyslogService;
import com.ciel.wj.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SyslogController {
    @Autowired
    SyslogService syslogService;

    @Autowired
    UserService userService;

    @GetMapping("api/log/{size}/{page}")
    public Page listLogs(@PathVariable("size") int size, @PathVariable("page") int page){
        String userName = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.findByUsername(userName);
        int userId = user.getId();
        //return syslogService.listByUserId(userId,page-1,size);
        return  syslogService.listByUserId(userId,page-1, size);
    }

}
