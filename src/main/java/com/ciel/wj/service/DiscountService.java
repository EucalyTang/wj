package com.ciel.wj.service;

import com.ciel.wj.dao.DiscountDAO;
import com.ciel.wj.pojo.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {
    @Autowired
    DiscountDAO discountDAO;

    public Page list(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return discountDAO.findAll(PageRequest.of(page, size, sort));
    }

    public Discount findById(int id) {
        return  discountDAO.findById(id);
    }

    public void addOrUpdate(Discount discount) {
        discountDAO.save(discount);
    }

    public void deleteById(int id){
        discountDAO.deleteById(id);
    }
}
