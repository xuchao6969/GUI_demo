package com.demo.project.gui;

import com.demo.project.service.LoginService;
import com.demo.project.service.QueryService;

import javax.swing.*;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;

/**
 * 多条件查询界面
 * @author xc
 * @date 2021-05-28
 */
public class QueryUI extends JFrame {

    private static final long serialVersionUID = -6788045638380819221L;

    private JTextField studentNo;//学号
    private JRadioButton boy;//性别 男
    private JRadioButton girl;//性别 女
    //小容器
    private JLabel j1;
    private JLabel j2;
    private JLabel j3;
    private JLabel j4;
    private JLabel j5;
    private JLabel jboy;
    private JLabel jgirl;
    //小按钮
    private JButton b2;
    private JButton b3;

    private ButtonGroup bg;
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scrollPane;

    /**
     * 查询UI的构造
     * */
    public QueryUI() {
        //设置登录窗口标题
        this.setTitle("");
        //去掉窗口的装饰(边框)
        //  this.setUndecorated(true);
        //采用指定的窗口装饰风格
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        //窗体组件初始化
        init();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置布局为绝对定位
        this.setLayout(null);
        this.setBounds(0, 0, 900, 300);
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
        j1.setBounds(0, 0, 900, 300);

        j2 = new JLabel();
        Image img2 = new ImageIcon("").getImage();
        j2.setIcon(new ImageIcon(img2));
        j2.setBounds(40, 95, 50, 53);

        //学号输入标签
        j3 = new JLabel("请输入学生的学号进行查询：");
        j3.setBounds(20, 20, 200, 20);
        //学号输入框
        studentNo = new JTextField();
        studentNo.setBounds(200, 20, 150, 20);

        boy = new JRadioButton("男", true);//性别 男
        boy.setBounds(20, 50, 20, 20);
        jboy = new JLabel("男");//性别标签
        jboy.setBounds(40, 50, 250, 20);
        girl = new JRadioButton("女");//性别 女
        girl.setBounds(60, 50, 20, 20);
        jgirl = new JLabel("女");//性别标签
        jgirl.setBounds(80, 50, 250, 20);
        bg = new ButtonGroup();
        bg.add(boy);
        bg.add(girl);

        //查询按钮
        b2 = new JButton("查询");
        b2.setBounds(800, 20, 60, 60);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                //遍历清除表格当前行
                for (int i=0; i<table.getRowCount(); i++){
                    model.removeRow(i);
                    i--;
                }
                if("查询".equals(cmd)) {
                    String studentNoStr = studentNo.getText();
                    String gender = "";
                    Enumeration<AbstractButton> btns = bg.getElements();
                    while(btns.hasMoreElements()){
                        AbstractButton btn = btns.nextElement();
                        if (btn.isSelected()){
                            gender = btn.getText().equals("男")?"1":btn.getText().equals("女")?"2":"3";
                            break;
                        }
                    }
                    //查询行数
                    List<String[]> rows = QueryService.getStudentByStudentNoAndGender(studentNoStr, gender);

                    //遍历渲染新的查询行数
                    for (String[] row: rows){
                        model.addRow(row);
                    }
                }
            }
        });

        String[][] datas = {};
        String[] titles = { "姓名", "年龄", "性别", "学号", "用户名", "密码", "E-mail",
                "电话", "qq", "班级", "生日", "地址", "邮编" };
        model = new DefaultTableModel(datas, titles);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20,90, 850,150);
        scrollPane.setBackground(Color.green);
        table.setVisible(true);
        getContentPane().add(scrollPane,BorderLayout.CENTER);

        //所有组件用容器装载
        j1.add(j2);
        j1.add(j3);
        j1.add(boy);
        j1.add(girl);
        j1.add(jboy);
        j1.add(jgirl);
        j1.add(b2);

        container.add(j1);
        container.add(scrollPane);
        container.add(studentNo);
    }

    public static void main(String[] args) {
        new QueryUI();
    }

}
