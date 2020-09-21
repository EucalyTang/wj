package com.ciel.wj.controller;

import com.ciel.wj.config.Log;
import com.ciel.wj.service.SyslogService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

    private final static Logger log = org.slf4j.LoggerFactory.getLogger(TestController.class);

    @Autowired
    private SyslogService logService;

    @RequestMapping("/aop")
    @ResponseBody
    @Log("测试aoplog")
    public Object aop(String name, String nick) {
        Map<String, Object> map = new HashMap<>();
        log.info("我被执行了！");
        map.put("res", "ok");
        return map;
    }
}
