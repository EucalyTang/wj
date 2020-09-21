package com.ciel.wj.controller;

import com.ciel.wj.pojo.AdminRole;
import com.ciel.wj.result.Result;
import com.ciel.wj.result.ResultFactory;
import com.ciel.wj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class RoleController {
    @Autowired
    AdminRoleService adminRoleService;
    @Autowired
    AdminPermissionService adminPermissionService;
    @Autowired
    AdminRolePermissionService adminRolePermissionService;
    @Autowired
    AdminRoleMenuService adminRoleMenuService;

    @GetMapping("/api/admin/role")
    public Result listRoles() {
        //return adminRoleService.list();
        return ResultFactory.buildSuccessResult(adminRoleService.listWithPermsAndMenus());
    }

    @PutMapping("/api/admin/role/status")
    public Result updateRoleStatus(@RequestBody AdminRole requestRole) {
//        AdminRole adminRole = adminRoleService.findById(requestRole.getId());
//        adminRole.setEnabled(requestRole.isEnabled());
//        adminRoleService.addOrUpdate(adminRole);
//        String message = "角色" + requestRole.getNameZh() + "状态更新成功";
//        return ResultFactory.buildSuccessResult(message);
        AdminRole adminRole = adminRoleService.updateRoleStatus(requestRole);
        String message = "用户" + adminRole.getNameZh() + "状态更新成功";
        return ResultFactory.buildSuccessResult(message);
    }

    @PutMapping("/api/admin/role")
    public Result editRole(@RequestBody AdminRole requestRole) {
        adminRoleService.addOrUpdate(requestRole);
        adminRolePermissionService.savePermChanges(requestRole.getId(),requestRole.getPerms());
        String message = "修改角色信息成功";
        return ResultFactory.buildSuccessResult(message);
    }

    @PostMapping("/api/admin/role")
    public Result addRole(@RequestBody AdminRole requestRole) {
//        adminRoleService.addOrUpdate(requestRole);
//        String message = "添加角色成功";
//        return ResultFactory.buildSuccessResult(message);
        adminRoleService.editRole(requestRole);
        return ResultFactory.buildSuccessResult("修改用户成功");
    }

//    @GetMapping("/api/admin/role/perm")
//    public List<AdminPermission> listPerms() {
//        return  adminPermmsionService.list();
//    }

    @GetMapping("/api/admin/role/perm")
    public Result listPerms() {
        return ResultFactory.buildSuccessResult(adminPermissionService.list());
    }

//    @PutMapping("/api/admin/role/menu")
//    public void updateRoleMenu(@RequestParam int rid, @RequestBody LinkedHashMap menusIds) {
//        adminRoleMenuService.deleteAllByRid(rid);
//        for (Object value : menusIds.values()) {
//            for (int mid : (List<Integer>)value) {
//                AdminRoleMenu rm = new AdminRoleMenu();
//                rm.setRid(rid);
//                rm.setMid(mid);
//                adminRoleMenuService.save(rm);
//            }
//        }
//    }
    @PutMapping("/api/admin/role/menu")
    public Result updateRoleMenu(@RequestParam int rid, @RequestBody Map<String, List<Integer>> menusIds) {
        adminRoleMenuService.updateRoleMenu(rid, menusIds);
        return ResultFactory.buildSuccessResult("更新成功");
    }
}
