package com.ciel.wj.config;

import com.ciel.wj.service.SyslogService;
import com.ciel.wj.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ciel.wj.pojo.SysLog;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {
    private final static Logger log = org.slf4j.LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private SyslogService syslogService;

    @Autowired
    UserService userService;

    //表示匹配带有自定义注解的方法
    @Pointcut("@annotation(com.ciel.wj.config.Log)")
    public void pointcut() {}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        long beginTime = System.currentTimeMillis();

        try {
            //log.info("我在目标方法之前执行！");
            result = point.proceed();
            long endTime = System.currentTimeMillis();
            insertLog(point,endTime-beginTime);
        } catch (Throwable e) {
            // TODO Auto-generated catch block
        }
        return result;
    }

    private void insertLog(ProceedingJoinPoint point, long time) {
        //log.info(new Date(System.currentTimeMillis()).toString());
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();
        SysLog sys_log = new SysLog();

        Log userAction = method.getAnnotation(Log.class);
        if (userAction != null) {
            //注解上的描述
            sys_log.setUserAction(userAction.value());
        }

        // 请求的类名
        String className = point.getTarget().getClass().getName();
        // 请求的方法名
        String methodName = signature.getName();
        // 请求的方法参数值
        String args = Arrays.toString(point.getArgs());

        //从Shiro中获取当前登陆人id
        //User user = (User) SecurityUtils.getSubject().getPrincipal();
        String userName = SecurityUtils.getSubject().getPrincipal().toString();
        int userid = userService.findByUsername(userName).getId();
        sys_log.setUserId(userid);
        sys_log.setMethodName(methodName);
        sys_log.setMethodArgs(args);
        sys_log.setExecTime(time);
        sys_log.setCreateDatetime(new Date(System.currentTimeMillis()));
        log.info("当前登陆人：{},类名:{},方法名:{},参数：{},执行时间：{}",userid, className, methodName, args, time);
        syslogService.addOrUpdate(sys_log);
    }
}
