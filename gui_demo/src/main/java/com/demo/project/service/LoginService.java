package com.demo.project.service;

import com.demo.project.dao.StudentDao;
import com.demo.project.util.JdbcUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.util.List;

/**
 * 登录方法
 * @author xc
 * @date 2021-05-27
 */
public class LoginService {

    /**
     * 登录方法
     */
    public static String login(String uname, String pwd){
        //入参校验
        if (!StringUtils.hasText(uname) || !StringUtils.hasText(pwd)){
            return "用户名密码错误";
        }
        //根据用户名密码查询用户
        List<StudentDao> list = JdbcUtil.query(uname, pwd);
        //判断查询结果
        if (CollectionUtils.isEmpty(list)){
            return "没有此用户！";
        }else if (list.size()>1){
           return "用户不唯一，请联系管理员处理！";
        }else{
           return "登录成功！";
        }
    }
}
