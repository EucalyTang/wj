package com.ciel.wj.controller;

import com.ciel.wj.config.Log;
import com.ciel.wj.pojo.Gun;
import com.ciel.wj.result.Result;
import com.ciel.wj.result.ResultFactory;
import com.ciel.wj.service.GunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GunController {
    @Autowired
    GunService gunService;

    @GetMapping("api/item/gun")
    public List getRanges(){
        return gunService.list();
    }

    @GetMapping("api/admin/item/gun/{size}/{page}")
    public Page listGuns(@PathVariable("size") int size, @PathVariable("page") int page){
        return gunService.list(page-1,size);
    }

    @PutMapping("api/admin/item/gun/modify")
    @Log("修改枪型信息")
    public Result updateGun(@RequestBody Gun gun){
        gunService.addOrUpdate(gun);
        return ResultFactory.buildSuccessResult("保存成功");
    }

    @DeleteMapping("api/admin/item/gun/{id}")
    @Log("删除枪型")
    public Result deleteGun(@PathVariable("id") int id) {
        gunService.deleteById(id);
        return ResultFactory.buildSuccessResult("删除成功");
    }

}
