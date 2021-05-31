package com.demo.project.gui;

import com.demo.project.dao.StudentDao;
import com.demo.project.service.RegistService;
import com.demo.project.util.JdbcUtil;
import javafx.scene.control.RadioButton;
import org.springframework.util.CollectionUtils;
import org.springframework.util.CommonsLogWriter;
import org.springframework.util.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 注册界面
 * @author xc
 * @date 2021-05-27
 */
public class RegistUI extends JFrame {

    private static final long serialVersionUID = -6788045638380819221L;

    private JTextField studentNo;//学号
    private JTextField name;//姓名
    private JRadioButton boy;//性别 男
    private JRadioButton girl;//性别 女
    private JTextField age;//年龄
    private JTextField uname;//用户名
    private JPasswordField pwd;//密码
    private JPasswordField confirmPwd;//确认密码
    private JTextField email;//邮箱
    private JTextField mobile;//联系电话
    private JTextField qq;//qq号
    private JTextField classNo;//班级
    private JTextField birthday;//生日
    private JTextField address;//地址
    private JTextField postCode;//邮编
    //小容器
    private JLabel jTitle;
    private JLabel j1;
    private JLabel j2;
    private JLabel j3;
    private JLabel j4;
    private JLabel j5;
    private JLabel j6;
    private JLabel j7;
    private JLabel j8;
    private JLabel j9;
    private JLabel j10;
    private JLabel j11;
    private JLabel j12;
    private JLabel j13;
    private JLabel j14;
    private JLabel j15;
    private JLabel j16;
    private JLabel j17;
    private JLabel jboy;
    private JLabel jgirl;
    //小按钮
    private JButton b2;
    private JButton b3;

    private ButtonGroup bg;

    /**
     * 注册UI的构造
     * */
    public RegistUI() {
        //设置登录窗口标题
        this.setTitle("学生注册");
        //去掉窗口的装饰(边框)
        //  this.setUndecorated(true);
        //采用指定的窗口装饰风格
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        //窗体组件初始化
        init();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置布局为绝对定位
        this.setLayout(null);
        this.setBounds(0, 0, 355, 580);
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
        j1.setBounds(0, 0, 355, 540);

        j2 = new JLabel();
        Image img2 = new ImageIcon("").getImage();
        j2.setIcon(new ImageIcon(img2));
        j2.setBounds(40, 95, 50, 53);

        jTitle = new JLabel("学生注册");
        jTitle.setBounds(80, 10, 150, 53);
        jTitle.setForeground(new Color(208, 106, 92));
        jTitle.setFont(new Font("微软雅黑", Font.BOLD, 20));

        studentNo = new JTextField();//学号输入框
        studentNo.setBounds(100, 60, 150, 20);
        j3 = new JLabel("学号");//学号标签
        j3.setBounds(60, 60, 70, 20);

        name = new JTextField();//姓名输入框
        name.setBounds(100, 90, 150, 20);
        j4= new JLabel("姓"+" "+"名");//姓名标签
        j4.setBounds(60, 90, 70, 20);

        boy = new JRadioButton("男", true);//性别 男
        boy.setBounds(100, 120, 20, 20);
        jboy = new JLabel("男");//性别标签
        jboy.setBounds(120, 120, 250, 20);
        girl = new JRadioButton("女");//性别 女
        girl.setBounds(140, 120, 20, 20);
        jgirl = new JLabel("女");//性别标签
        jgirl.setBounds(160, 120, 250, 20);
        bg = new ButtonGroup();
        bg.add(boy);
        bg.add(girl);
        j5 = new JLabel("性"+" "+"别");//性别标签
        j5.setBounds(60, 120, 250, 20);

        age = new JTextField();//年龄输入框
        age.setBounds(100, 150, 150, 20);
        j6 = new JLabel("年"+" "+"龄");//年龄标签
        j6.setBounds(60, 150, 250, 20);

        uname = new JTextField();//用户名输入框
        uname.setBounds(100, 180, 150, 20);
        j7 = new JLabel("用户名");//用户名标签
        j7.setBounds(60, 180, 250, 20);

        pwd = new JPasswordField();//密码输入框
        pwd.setBounds(100, 210, 150, 20);
        j8 = new JLabel("密"+" "+"码");//密码标签
        j8.setBounds(60, 210, 250, 20);

        confirmPwd = new JPasswordField();//确认密码输入框
        confirmPwd.setBounds(100, 240, 150, 20);
        j9 = new JLabel("确认密码");//确认密码标签
        j9.setBounds(60, 240, 250, 20);

        email = new JTextField();//邮箱输入框
        email.setBounds(100, 270, 150, 20);
        j10 = new JLabel("邮箱");//邮箱标签
        j10.setBounds(60, 270, 250, 20);

        mobile = new JTextField();//电话输入框
        mobile.setBounds(100, 300, 150, 20);
        j11 = new JLabel("电话");//电话标签
        j11.setBounds(60, 300, 250, 20);

        qq = new JTextField();//qq输入框
        qq.setBounds(100, 330, 150, 20);
        j12 = new JLabel("QQ");//qq标签
        j12.setBounds(60, 330, 250, 20);

        classNo = new JTextField();//班级输入框
        classNo.setBounds(100, 360, 150, 20);
        j13 = new JLabel("班级");//班级标签
        j13.setBounds(60, 360, 250, 20);

        birthday = new JTextField();//生日输入框
        birthday.setBounds(100, 390, 150, 20);
        j15 = new JLabel("生日");//生日标签
        j15.setBounds(60, 390, 250, 20);

        address = new JTextField();//地址输入框
        address.setBounds(100, 420, 150, 20);
        j16 = new JLabel("地址");//地址标签
        j16.setBounds(60, 420, 250, 20);

        postCode = new JTextField();//邮编输入框
        postCode.setBounds(100, 450, 150, 20);
        j17 = new JLabel("邮编");//邮编标签
        j17.setBounds(60, 450, 250, 20);


        //友情提示框
        j14 = new JLabel();
        j14.setBounds(60, 480, 250, 20);

        //注册按钮
        b2 = new JButton("注册");
        b2.setBounds(70, 520, 75, 20);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                String gender = "";
                Pattern pattern = Pattern.compile("[0-9]*");//正则表达式 数字校验
                StudentDao sd = new StudentDao();
                if("注册".equals(cmd)) {
                    Enumeration<AbstractButton> btns = bg.getElements();
                    while(btns.hasMoreElements()){
                        AbstractButton btn = btns.nextElement();
                        if (btn.isSelected()){
                            gender = btn.getText().equals("男")?"1":btn.getText().equals("女")?"2":"3";
                            break;
                        }
                    }
                }
                sd.setGender(gender);
                sd.setStudentNo(studentNo.getText());
                sd.setName(name.getText());
                sd.setAge(age.getText());
                sd.setUname(uname.getText());
                sd.setPwd(new String(pwd.getPassword()));
                sd.setConfirmPwd(new String(confirmPwd.getPassword()));
                sd.setEmail(email.getText());
                sd.setMobile(mobile.getText());
                sd.setQq(qq.getText());
                sd.setClassNo(classNo.getText());
                sd.setBirthday(birthday.getText());
                sd.setAddress(address.getText());
                sd.setPostCode(postCode.getText());
                String msg = RegistService.regist(sd);
                j14.setText("系统友情提示：" + msg);
                j14.setForeground(Color.RED);
            }
        });
        //取消按钮
        b3 = new JButton("取消");
        b3.setBounds(205, 520, 65, 20);
        //所有组件用容器装载
        j1.add(jTitle);
        j1.add(j2);
        j1.add(j3);
        j1.add(j4);
        j1.add(j5);
        j1.add(j6);
        j1.add(j7);
        j1.add(j8);
        j1.add(j9);
        j1.add(j10);
        j1.add(j11);
        j1.add(j12);
        j1.add(j13);
        j1.add(j14);
        j1.add(j15);
        j1.add(j16);
        j1.add(j17);
        j1.add(b2);
        j1.add(b3);
        j1.add(boy);
        j1.add(girl);
        j1.add(jboy);
        j1.add(jgirl);
        container.add(j1);
        container.add(studentNo);
        container.add(name);
        container.add(age);
        container.add(uname);
        container.add(pwd);
        container.add(confirmPwd);
        container.add(email);
        container.add(mobile);
        container.add(qq);
        container.add(classNo);
        container.add(birthday);
        container.add(address);
        container.add(postCode);

    }
    public static void main(String[] args) {
        new RegistUI();
    }

}