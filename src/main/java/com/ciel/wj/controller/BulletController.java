package com.ciel.wj.controller;

import com.ciel.wj.config.Log;
import com.ciel.wj.pojo.Bullet;
import com.ciel.wj.result.Result;
import com.ciel.wj.result.ResultFactory;
import com.ciel.wj.service.BulletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BulletController {
    @Autowired
    BulletService bulletService;

    @GetMapping("api/admin/item/bullet/{size}/{page}")
    public Page listBullet(@PathVariable("size") int size, @PathVariable("page") int page){
        return bulletService.list(page-1,size);
    }

    @GetMapping("api/item/bullet")
    public List getBullets(@RequestParam("gunid") int gunid){
        if (gunid>0) {
            return bulletService.listByGunId(gunid);
        } else {
            return bulletService.list();
        }
    }

    @PutMapping("api/admin/item/bullet/modify")
    @Log("修改子弹信息")
    public Result updateGun(@RequestBody Bullet bullet) {
        bulletService.addOrUpdate(bullet);
        return ResultFactory.buildSuccessResult("保存成功");
    }

    @DeleteMapping("api/admin/item/bullet/{id}")
    @Log("删除子弹")
    public Result deleteGun(@PathVariable("id") int id) {
        bulletService.deleteById(id);
        return ResultFactory.buildSuccessResult("删除成功");
    }
}
