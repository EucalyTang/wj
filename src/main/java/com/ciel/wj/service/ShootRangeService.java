package com.ciel.wj.service;

import com.ciel.wj.dao.ShootRangeDAO;
import com.ciel.wj.pojo.ShootRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShootRangeService {
    @Autowired
    ShootRangeDAO shootRangeDAO;

    public Page list(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return shootRangeDAO.findAll(PageRequest.of(page, size, sort));
    }

    public List list(){
        return shootRangeDAO.findAll();
    }

    public ShootRange findById(int id) {
        return shootRangeDAO.findById(id);
    }

    public void addOrUpdate(ShootRange shootRange) {
        shootRangeDAO.save(shootRange);
    }

    public void deleteById(int id) {
        shootRangeDAO.deleteById(id);
    }
}
