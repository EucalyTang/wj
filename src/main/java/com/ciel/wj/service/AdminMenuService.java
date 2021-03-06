package com.ciel.wj.service;

import com.ciel.wj.dao.AdminMenuDAO;
import com.ciel.wj.pojo.AdminMenu;
import com.ciel.wj.pojo.AdminRoleMenu;
import com.ciel.wj.pojo.AdminUserRole;
import com.ciel.wj.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminMenuService {
    @Autowired
    AdminMenuDAO adminMenuDAO;
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    @Autowired
    AdminRoleMenuService adminRoleMenuService;

    public List<AdminMenu> getAllByParentId(int parentId) {
        return adminMenuDAO.findAllByParentId(parentId);
    }

    public List<AdminMenu> getMenusByCurrentUser() {
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.findByUsername(username);
        List<AdminUserRole> userRoleList = adminUserRoleService.listAllByUid(user.getId());
        List<AdminMenu> menus = new ArrayList<>();
        for (AdminUserRole userRole : userRoleList) {
            List<AdminRoleMenu> rms = adminRoleMenuService.findAllByRid(userRole.getRid());
            for (AdminRoleMenu rm : rms) {
                // 增加防止多角色状态下菜单重复的逻辑
                AdminMenu menu = adminMenuDAO.findById(rm.getMid());
                boolean isExist = false;
                for (AdminMenu m : menus){
                    if (m.getId() == menu.getId()){
                        isExist = true;
                    }
                }
                if (!isExist) {
                    menus.add(menu);
                }
            }
        }
        handleMenus(menus);
        return menus;
    }

    public void handleMenus(List<AdminMenu> menus) {
//        for (AdminMenu menu : menus) {
//            menu.setChildren(getAllByParentId(menu.getId()));
//        }
//
//        Iterator<AdminMenu> iterator = menus.iterator();
//        while (iterator.hasNext()) {
//            AdminMenu menu = iterator.next();
//            if (menu.getParentId() != 0) {
//                iterator.remove();
//            }
//        }
        menus.forEach(m -> {
            List<AdminMenu> children = getAllByParentId(m.getId());
            m.setChildren(children);
        });

        menus.removeIf(m -> m.getParentId() != 0);
    }

    public List<AdminMenu> getMenusByRoleId(int rid) {
//        List<AdminMenu> menus = new ArrayList<>();
//        List<AdminRoleMenu> rms = adminRoleMenuService.findAllByRid(rid);
//        for (AdminRoleMenu rm : rms) {
//            menus.add(adminMenuDAO.findById(rm.getMid()));
//        }
        List<Integer> menuIds = adminRoleMenuService.findAllByRid(rid)
                .stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());
        List<AdminMenu> menus = adminMenuDAO.findAllById(menuIds);

        handleMenus(menus);
        return menus;
    }

}
