package com.ciel.wj.controller;

import com.ciel.wj.config.Log;
import com.ciel.wj.dao.OrderRangeDAO;
import com.ciel.wj.dto.OrderRangeDTO;
import com.ciel.wj.result.Result;
import com.ciel.wj.result.ResultFactory;
import com.ciel.wj.service.OrderRangeService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderRangeController {
    @Autowired
    OrderRangeService orderRangeService;
    @Autowired
    OrderRangeDAO orderRangeDAO;

    @GetMapping("/api/admin/order/range")
    public List<OrderRangeDTO> listOrderRanges() {
        return orderRangeService.list();
    }

//    @GetMapping("api/admin/order/range/{size}/{page}")
//    public Page<OrderRangeDTO> listOrderRanges(@PathVariable("size") int size, @PathVariable("page") int page) {
//        return orderRangeService.listDTOs(page-1,size);
//    }

    @GetMapping("api/reserve/range/{size}/{page}")
    public Page<OrderRangeDTO> listOrderRangesByUser(@PathVariable("size") int size, @PathVariable("page") int page) {
        String userName = SecurityUtils.getSubject().getPrincipal().toString();
        return orderRangeService.listBySubmitUsername(userName,page-1,size);
    }

    @CrossOrigin
    @GetMapping("api/admin/order/range/{size}/{page}")
    public Page<OrderRangeDTO> listOrderRangesByUser(@PathVariable("size") int size, @PathVariable("page") int page,@RequestParam("option") String option,@RequestParam("keyword") String keyword) {
        if (option.equals("memberNameZh") && keyword !=null && keyword.length() != 0) {
            return orderRangeService.queryByMemberName(keyword,page-1,size);
        } else if (option.equals("submitUserName") && keyword !=null && keyword.length() != 0) {
            return orderRangeService.queryBySubmitUserName(keyword,page-1,size);
        }
        return orderRangeService.listDTOs(page-1,size);
    }

//    @CrossOrigin
//    @PostMapping("api/admin/order/range/test")
//    public Page<OrderRangeDTO> listOrderRangesByUser(@RequestBody Map<String,Object> params) {
//        String option = params.get("option").toString();
//        String keyword = params.get("keyword").toString();
//        int page = 1;
//        int size = 10;
//        return orderRangeService.queryByMemberName(keyword,page-1,size);
//    }

    @Log("删除场地订单")
    @DeleteMapping("api/admin/order/range/{id}")
    public Result deleteMember(@PathVariable("id") int id) {
        if (orderRangeDAO.findById(id).getStatus()!=0) {
            return ResultFactory.buildFailResult("已审核，无法删除");
        } else {
            orderRangeService.delete(id);
            return ResultFactory.buildSuccessResult("删除成功");
        }
    }

    @Log("审核场地订单")
    @PutMapping("/api/admin/order/range/verify/{id}")
    public Result updateUserStatus(@PathVariable("id") int id) {
        orderRangeService.verify(orderRangeDAO.findById(id));
        return ResultFactory.buildSuccessResult("订单状态更新成功");
    }

    @PutMapping("api/admin/order/range/modify")
    @Log("修改场地订单信息")
    public Result updateOrderRange(@RequestBody OrderRangeDTO orderRangeDTO){
        String userName = SecurityUtils.getSubject().getPrincipal().toString();
        orderRangeService.addOrUpdate(orderRangeDTO,userName);
        return ResultFactory.buildSuccessResult("保存成功");
    }
}
