package com.demo.project.service;

import com.demo.project.dao.StudentDao;
import com.demo.project.util.DateUtil;
import com.demo.project.util.JdbcUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 注册方法
 * @author xc
 * @date 2021-05-27
 */
public class RegistService {

    /**
     * 注册方法
     */
    public static String regist(StudentDao sd){
        //正则表达式 数字校验
        Pattern pattern = Pattern.compile("[0-9]*");

        String studentNo = sd.getStudentNo();
        if (StringUtils.hasText(studentNo)  && studentNo.length()>36){
            return "学号长度过长,超过36位！";
        }
        String name = sd.getName();
        if (!StringUtils.hasText(name)){
            return "请输入姓名！";
        }
        if (name.length()>10 || name.length() < 2){
            return "姓名长度不合法！";
        }

        String age = sd.getAge();
        int ageStr = 0;
        if (StringUtils.hasText(age)){
            if (age.length()>3){
                return "年龄长度过长,超过3位！";
            }
            boolean flag = pattern.matcher(age).matches();
            if (!flag){
                return "年龄不是正确数字！";
            }
            ageStr = Integer.parseInt(age);
            if (10 > ageStr){
                return "仅支持10岁以上注册!";
            }
        }
        String uname = sd.getUname();
        if (!StringUtils.hasText(uname)){
            return "请输入用户名！";
        }
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{3,15}$";
        if (!uname.matches(regex)){
            return "用户名不合法！";
        }
        String pwd = sd.getPwd();
        if (!StringUtils.hasText(pwd)){
            return "请输入密码！";
        }
        if (pwd.length()>15 || pwd.length()<6){
            return "密码长度不正确！";
        }

        String confirmPwd = sd.getConfirmPwd();
        if (!pwd.equals(confirmPwd)){
            return "两次密码输入的不一致！";
        }
        String email = sd.getEmail();
        if (!StringUtils.hasText(email)){
            return "请输入邮箱地址！";
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(email);
        if(!m.matches()){
            return "邮箱地址不合法！";
        }

        String mobile = sd.getMobile();
        if (StringUtils.hasText(mobile)){
            boolean flag = pattern.matcher(mobile).matches();
            if (!flag){
                return "联系电话不是正确数字！";
            }
            if (mobile.length()>11){
                return "联系电话长度错误！";
            }
        }
        String qq = sd.getQq();
        if (StringUtils.hasText(qq)){
            boolean flag = pattern.matcher(qq).matches();
            if (!flag){
                return "qq不是正确数字！";
            }
        }
        String birthday = sd.getBirthday();
        if (!DateUtil.checkDate(birthday)){
            return "生日日期格式错误！";
        }
        ageStr = DateUtil.getAge(birthday);
        if (10 > ageStr){
            return "仅支持10岁以上注册!";
        }

        String address = sd.getAddress();
        if (StringUtils.hasText(address) && address.length() > 100){
            return "地址长度超过100！";
        }
        String postCode = sd.getPostCode();
        if (StringUtils.hasText(postCode) && 6 != postCode.length()){
            return "邮编长度不合法!";
        }
        //判断用户名有没有被使用
        String condition = "uname = "+"'"+uname+"'";
        List<StudentDao> list = JdbcUtil.query(condition);
        if (!CollectionUtils.isEmpty(list)){
            return "该用户名已被使用";
        }
        try {
            sd.setId(UUID.randomUUID().toString());
            JdbcUtil.regist(sd);
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误！";
        }
        return "恭喜你，注册成功！";
    }


}
