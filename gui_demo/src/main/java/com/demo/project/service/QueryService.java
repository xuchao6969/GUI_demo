package com.demo.project.service;

import com.demo.project.dao.StudentDao;
import com.demo.project.util.JdbcUtil;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 条件查询方法
 * @author xc
 * @date 2021-05-28
 */
public class QueryService {

    /**
     * 根据学号和性别查询学生
     */
    public static List<String[]> getStudentByStudentNoAndGender(String studentNo, String gender){
        String condition = "";
        //参数为空 全查询
        if (!StringUtils.hasText(studentNo) && !StringUtils.hasText(gender)){
            condition = "1=1";//全查询
        }
        //按照学号查询
        if (StringUtils.hasText(studentNo) && !StringUtils.hasText(gender)){
            condition = "studentNo = '"+studentNo+ "'";//学号查询
        }
        //按照性别查询
        if (!StringUtils.hasText(studentNo) && StringUtils.hasText(gender)){
            condition = "gender = '"+gender+ "'";//性别查询
        }
        //按照学号和性别查询
        if (StringUtils.hasText(studentNo) && StringUtils.hasText(gender)){
            condition = "studentNo = '"+studentNo+ "' and gender = '"+gender+"'";//学号 性别 查询
        }
        //条件查询
        List<StudentDao> list = JdbcUtil.query(condition);
        //定义 表格行
        List<String> rowList = null;
        //定义 表格行数组
        List<String[]> rows = new ArrayList<>();
        //组装表格行
        for (StudentDao sd:list){
            rowList = new ArrayList<>();
            rowList.add(sd.getName());
            rowList.add(sd.getAge());
            rowList.add("1".equals(sd.getGender())?"男":"2".equals(sd.getGender())?"女":"未知");
            rowList.add(sd.getStudentNo());
            rowList.add(sd.getUname());
            rowList.add(sd.getPwd());
            rowList.add(sd.getEmail());
            rowList.add(sd.getMobile());
            rowList.add(sd.getQq());
            rowList.add(sd.getClassNo());
            rowList.add(sd.getBirthday());
            rowList.add(sd.getAddress());
            rowList.add(sd.getPostCode());
            rows.add(rowList.toArray(new String[rowList.size()]));
        }
        return rows;
    }
}
