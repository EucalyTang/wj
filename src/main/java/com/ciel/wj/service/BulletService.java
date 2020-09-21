package com.ciel.wj.service;

import com.ciel.wj.dao.BulletDAO;
import com.ciel.wj.pojo.Bullet;
import com.ciel.wj.pojo.Gun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BulletService {
    @Autowired
    BulletDAO bulletDAO;
    @Autowired
    GunService gunService;

    public Page list(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return bulletDAO.findAll(PageRequest.of(page, size, sort));
    }

    public List list(){
        return bulletDAO.findAll();
    }

    public List listByGunId(int gunId){
        Gun gun = gunService.findById(gunId);
        return bulletDAO.findAllByBore(gun.getBore());
    }

    public Bullet findById(int id) {
        return  bulletDAO.findById(id);
    }

    public void addOrUpdate(Bullet bullet) {
        bulletDAO.save(bullet);
    }

    public void deleteById(int id){
        bulletDAO.deleteById(id);
    }

}
