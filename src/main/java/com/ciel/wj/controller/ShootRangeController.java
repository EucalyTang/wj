package com.ciel.wj.controller;

import com.ciel.wj.config.Log;
import com.ciel.wj.pojo.ShootRange;
import com.ciel.wj.result.Result;
import com.ciel.wj.result.ResultFactory;
import com.ciel.wj.service.ShootRangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShootRangeController {
    @Autowired
    ShootRangeService shootRangeService;

    @GetMapping("api/admin/item/range/{size}/{page}")
    public Page listRanges(@PathVariable("size") int size, @PathVariable("page") int page){
        return shootRangeService.list(page-1,size);
    }

    @GetMapping("api/item/range")
    public List getRanges(){
        return shootRangeService.list();
    }

    @PutMapping("api/admin/item/range/modify")
    @Log("修改场地信息")
    public Result updateRange(@RequestBody ShootRange shootRange){
        shootRangeService.addOrUpdate(shootRange);
        return ResultFactory.buildSuccessResult("保存成功");
    }

    @DeleteMapping("api/admin/item/range/{id}")
    @Log("删除场地")
    public Result deleteRange(@PathVariable("id") int id) {
        shootRangeService.deleteById(id);
        return ResultFactory.buildSuccessResult("删除成功");
    }
}
