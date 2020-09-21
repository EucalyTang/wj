package com.ciel.wj.service;

import com.ciel.wj.dao.SysLogDAO;
import com.ciel.wj.pojo.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class SyslogService {
    @Autowired
    private SysLogDAO sysLogDAO;

    public void addOrUpdate(SysLog sysLog) { sysLogDAO.save(sysLog); }

    public Page list(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return sysLogDAO.findAll(PageRequest.of(page, size, sort));
    }

    public Page<SysLog> listByUserId(int uid, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        SysLog sysLog = new SysLog();
        sysLog.setUserId(uid);
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id").withIgnorePaths("execTime");  //忽略属性：是否关注。因为是基本类型，需要忽略掉
        Example<SysLog> example = Example.of(sysLog,matcher);
        Page<SysLog> sysLogPage = sysLogDAO.findAll(example,PageRequest.of(page, size, sort));
        //return  sysLogDAO.findByUserId(uid,PageRequest.of(page, size, sort));
        return  sysLogPage;
    }

}
