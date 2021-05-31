package com.demo.project.gui;

import com.demo.project.service.LoginService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 登录界面
 * @author xc
 * @date 2021-05-27
 */
public class LoginUI extends JFrame {

    private static final long serialVersionUID = -6788045638380819221L;
    //用户名
    private JTextField ulName;
    //密码
    private JPasswordField ulPasswd;
    //小容器
    private JLabel j1;
    private JLabel j2;
    private JLabel j3;
    private JLabel j4;
    private JLabel j5;
    //小按钮
    private JButton b2;
    private JButton b3;

    /**
     * 登录UI的构造
     * */
    public LoginUI() {
        //设置登录窗口标题
        this.setTitle("学生信息管理系统登录");
        //去掉窗口的装饰(边框)
        //  this.setUndecorated(true);
        //采用指定的窗口装饰风格
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        //窗体组件初始化
        init();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置布局为绝对定位
        this.setLayout(null);
        this.setBounds(0, 0, 355, 265);
        //设置窗体的图标
        Image img0 = new ImageIcon("").getImage();
        this.setIconImage(img0);
        //窗体大小不能改变
        this.setResizable(false);
        //居中显示
        this.setLocationRelativeTo(null);
        //窗体显示
        this.setVisible(true);
    }
    /**
     * 窗体组件初始化
     * */
    public void init() {
        //创建一个容器,其中的图片大小和setBounds第三、四个参数要基本一致(需要自己计算裁剪)
        Container container = this.getContentPane();
        j1 = new JLabel();
        //设置背景色
        Image img1 = new ImageIcon("").getImage();
        j1.setIcon(new ImageIcon(img1));
        j1.setBounds(0, 0, 355, 265);

        j2 = new JLabel();
        Image img2 = new ImageIcon("").getImage();
        j2.setIcon(new ImageIcon(img2));
        j2.setBounds(40, 95, 50, 53);
        //用户名输入框
        ulName = new JTextField();
        ulName.setBounds(100, 70, 150, 20);
        //用户名标签
        j3 = new JLabel("用户名");
        j3.setBounds(60, 70, 70, 20);
        //密码输入框
        ulPasswd = new JPasswordField();
        ulPasswd.setBounds(100, 100, 150, 20);
        //密码标签
        j4= new JLabel("密"+" "+"码");
        j4.setBounds(60, 100, 70, 20);
        //友情提示框
        j5 = new JLabel();
        j5.setBounds(60, 130, 250, 20);
        //登录按钮
        b2 = new JButton("登录");
        b2.setBounds(70, 180, 75, 20);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();

                if("登录".equals(cmd)) {
                    String uname = ulName.getText();
                    String pwd = new String(ulPasswd.getPassword());
                    String msg = LoginService.login(uname,pwd);
                    j5.setText("系统友情提示：" + msg);
                    j5.setForeground(Color.RED);
                }
            }
        });
        //取消按钮
        b3 = new JButton("取消");
        b3.setBounds(205, 180, 65, 20);
        //所有组件用容器装载
        j1.add(j2);
        j1.add(j3);
        j1.add(j4);
        j1.add(j5);
        j1.add(b2);
        j1.add(b3);
        container.add(j1);
        container.add(ulName);
        container.add(ulPasswd);
    }

    public static void main(String[] args) {
        new LoginUI();
    }


}