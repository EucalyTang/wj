package com.ciel.wj.controller;

import com.ciel.wj.config.Log;
import com.ciel.wj.pojo.Discount;
import com.ciel.wj.result.Result;
import com.ciel.wj.result.ResultFactory;
import com.ciel.wj.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
public class DiscountController {
    @Autowired
    DiscountService discountService;

    @GetMapping("api/admin/item/discount/{size}/{page}")
    public Page listDiscount(@PathVariable("size") int size, @PathVariable("page") int page){
        return discountService.list(page-1,size);
    }

    @PutMapping("api/admin/item/discount/modify")
    @Log("修改优惠方案信息")
    public Result updateDiscount(@RequestBody Discount discount) {
        discountService.addOrUpdate(discount);
        return ResultFactory.buildSuccessResult("保存成功");
    }

    @DeleteMapping("api/admin/item/discount/{id}")
    @Log("删除优惠方案")
    public Result deleteDiscount(@PathVariable("id") int id) {
        discountService.deleteById(id);
        return ResultFactory.buildSuccessResult("删除成功");
    }
}
