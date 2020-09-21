package com.ciel.wj.service;

import com.ciel.wj.dao.GunDAO;
import com.ciel.wj.pojo.Gun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GunService {
    @Autowired
    GunDAO gunDAO;

    public Page list(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return gunDAO.findAll(PageRequest.of(page,size,sort));
    }

    public List list(){
        return gunDAO.findAll();
    }

    public Gun findById(int id) {
        return  gunDAO.findById(id);
    }

    public void addOrUpdate(Gun gun) {
        gunDAO.save(gun);
    }

    public void deleteById(int id) {
        gunDAO.deleteById(id);
    }
}
