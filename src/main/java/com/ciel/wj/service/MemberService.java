package com.ciel.wj.service;

import com.ciel.wj.dao.MemberDAO;
import com.ciel.wj.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberDAO memberDAO;

    public Page list(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return memberDAO.findAll(PageRequest.of(page, size, sort));
    }

    public Member findById(int id) {
        return memberDAO.findById(id);
    }

    public void addOrUpdate(Member customer) {
        memberDAO.save(customer);
    }

    public void delete(int id) {
        memberDAO.deleteById(id);
    }

    public boolean isExist(String passport) {
        Member customer = memberDAO.findByPassportNumber(passport);
        return null!=customer;
    }

    public Page listByUserInviteCode(String code, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return memberDAO.findByUserInviteCode(code,PageRequest.of(page,size,sort));
    }

    public List listByUserInviteCode(String code) {
        return memberDAO.findAllByUserInviteCode(code);
    }
}
