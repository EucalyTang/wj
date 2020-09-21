package com.ciel.wj.controller;

import com.ciel.wj.config.Log;
import com.ciel.wj.dao.OrderEquipDAO;
import com.ciel.wj.dto.OrderEquipDTO;
import com.ciel.wj.result.Result;
import com.ciel.wj.result.ResultFactory;
import com.ciel.wj.service.OrderEquipService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderEquipController {
    @Autowired
    OrderEquipService orderEquipService;
    @Autowired
    OrderEquipDAO orderEquipDAO;

    @GetMapping("/api/admin/order/equip")
    public List<OrderEquipDTO> listOrderEquips() {
        return orderEquipService.list();
    }

    @GetMapping("api/reserve/equip/{size}/{page}")
    public Page<OrderEquipDTO> listOrderEquipsByUser(@PathVariable("size") int size, @PathVariable("page") int page) {
        String userName = SecurityUtils.getSubject().getPrincipal().toString();
        return orderEquipService.listBySubmitUsername(userName,page-1,size);
    }

    @CrossOrigin
    @GetMapping("api/admin/order/equip/{size}/{page}")
    public Page<OrderEquipDTO> listOrderEquipsByUser(@PathVariable("size") int size, @PathVariable("page") int page, @RequestParam("option") String option, @RequestParam("keyword") String keyword) {
        if (option.equals("memberNameZh") && keyword !=null && keyword.length() != 0) {
            return orderEquipService.queryByMemberName(keyword,page-1,size);
        } else if (option.equals("submitUserName") && keyword !=null && keyword.length() != 0) {
            return orderEquipService.queryBySubmitUserName(keyword,page-1,size);
        }
        return orderEquipService.listDTOs(page-1,size);
    }

    @Log("删除器械订单")
    @DeleteMapping("api/admin/order/equip/{id}")
    public Result deleteMember(@PathVariable("id") int id) {
        if (orderEquipDAO.findById(id).getStatus()!=0) {
            return ResultFactory.buildFailResult("已审核，无法删除");
        } else {
            orderEquipService.delete(id);
            return ResultFactory.buildSuccessResult("删除成功");
        }
    }

    @Log("审核器械订单")
    @PutMapping("/api/admin/order/equip/verify/{id}")
    public Result updateStatus(@PathVariable("id") int id) {
        orderEquipService.verify(orderEquipDAO.findById(id));
        return ResultFactory.buildSuccessResult("订单状态更新成功");
    }

    @Log("修改器械订单价格")
    @PutMapping("/api/admin/order/equip/change")
    public Result updatePrice(@RequestBody OrderEquipDTO orderEquipDTO) {
        orderEquipService.changePrice(orderEquipDAO.findById(orderEquipDTO.getId()),orderEquipDTO.getFinalTotal());
        return ResultFactory.buildSuccessResult("订单价格更新成功");
    }

    @PutMapping("api/admin/order/equip/modify")
    @Log("修改器械订单信息")
    public Result updateOrderEquip(@RequestBody OrderEquipDTO orderEquipDTO){
        String userName = SecurityUtils.getSubject().getPrincipal().toString();
        orderEquipService.addOrUpdate(orderEquipDTO,userName);
        return ResultFactory.buildSuccessResult("保存成功");
    }
}
