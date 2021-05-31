package com.demo.project.util;

import com.demo.project.dao.StudentDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 数据库链接工具类
 * @author xc
 * @date 2021-05-26
 */
public class JdbcUtil {
    //初始化数据库连接
    public static final String URL = "jdbc:mysql://localhost:3306/sss?useUnicode=true&characterEncoding=utf8";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    private static Connection conn = null;
    static{
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2. 获得数据库连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     */
    public static Connection getConnection(){
        return conn;
    }

    /**
     * 查询用户
     */
    public static List<StudentDao> query(String uname, String pwd) {
        Connection conn = JdbcUtil.getConnection();
        List<StudentDao> list = null;
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT uname,pwd FROM user where uname = ? and pwd = ?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, uname);
            ptmt.setString(2, pwd);
            ResultSet rs = ptmt.executeQuery();

            list = new ArrayList<>();
            StudentDao sd = null;
            while(rs.next()){
                sd = new StudentDao();
                sd.setUname(rs.getString("uname"));
                sd.setPwd(rs.getString("pwd"));
                list.add(sd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 注册用户
     */
    public static void regist(StudentDao sd) {
        //获取连接
        Connection conn = JdbcUtil.getConnection();
        //sql
        String sql = "insert into `user` (`id`, `studentNo`, `name`, `gender`, `age`, `uname`,"+
                "`pwd`, `email`, `mobile`, `qq`, `classNo`, `birthday`, `address`, `postCode`)"
                +"values("+"?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            //预编译
            PreparedStatement ptmt = conn.prepareStatement(sql);
            //传参
            ptmt.setString(1, sd.getId());
            ptmt.setString(2, sd.getStudentNo());
            ptmt.setString(3, sd.getName());
            ptmt.setString(4, sd.getGender());
            ptmt.setString(5, sd.getAge());
            ptmt.setString(6, sd.getUname());
            ptmt.setString(7, sd.getPwd());
            ptmt.setString(8, sd.getEmail());
            ptmt.setString(9, sd.getMobile());
            ptmt.setString(10, sd.getQq());
            ptmt.setString(11, sd.getClassNo());
            ptmt.setString(12, sd.getBirthday());
            ptmt.setString(13, sd.getAddress());
            ptmt.setString(14, sd.getPostCode());

            //执行
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 条件查询
     */
    public static List<StudentDao> query(String condition) {
        Connection conn = JdbcUtil.getConnection();
        List<StudentDao> list = null;
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM user where " + condition;
            System.out.println(sql);
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();

            list = new ArrayList<>();
            StudentDao sd = null;
            while(rs.next()){
                sd = new StudentDao();
                sd.setId(rs.getString("id"));
                sd.setStudentNo(rs.getString("studentNo"));
                sd.setName(rs.getString("name"));
                sd.setGender(rs.getString("gender"));
                sd.setAge(rs.getString("age"));
                sd.setUname(rs.getString("uname"));
                sd.setPwd(rs.getString("pwd"));
                sd.setEmail(rs.getString("email"));
                sd.setMobile(rs.getString("mobile"));
                sd.setQq(rs.getString("qq"));
                sd.setClassNo(rs.getString("classNo"));
                sd.setBirthday(rs.getString("birthday"));
                sd.setAddress(rs.getString("address"));
                sd.setPostCode(rs.getString("postCode"));
                list.add(sd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
