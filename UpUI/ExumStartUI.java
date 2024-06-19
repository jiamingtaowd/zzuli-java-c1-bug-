package UpUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ExumStartUI {
    private JLabel welcomeLabel;
    private JFrame mainFrame;
    private JFrame specializedFrame;
    private JFrame unitFrame;
    private JFrame wrongFrame;
    private JFrame examFrame;
    private JFrame garder;

    public ExumStartUI() {
        welcomeLabel = new JLabel("欢迎使用练习系统！");

        mainFrame = new JFrame("练习系统主界面");
        mainFrame.setBounds(400, 100, 800, 640);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);

        welcomeLabel.setBounds(350, 50, 200, 200);
        mainFrame.add(welcomeLabel);

        JButton specializedBtn = new JButton("专项练习");
        specializedBtn.setBounds(350, 180, 100, 30);
        specializedBtn.addActionListener(e ->
        {

            specializedFrame.setVisible(true);
        });
        mainFrame.add(specializedBtn);

        JButton unitBtn = new JButton("单元练习");
        unitBtn.setBounds(350, 240, 100, 30);
        unitBtn.addActionListener(e -> {
            QueryAndDisplay q=new QueryAndDisplay();
        });
        mainFrame.add(unitBtn);

        JButton wrongBtn = new JButton("错题练习");
        wrongBtn.setBounds(350, 300, 100, 30);
        wrongBtn.addActionListener(e -> {
            WrongQuestionsPractice w=new WrongQuestionsPractice();
            //wrongFrame.setVisible(true);
        });
        mainFrame.add(wrongBtn);

        JButton examBtn = new JButton("模拟考试");
        examBtn.setBounds(350, 360, 100, 30);
        examBtn.addActionListener(e -> {
            ExamFrameUI ex=new ExamFrameUI();
            try {
                ex.displayQuestionsFromDatabase();
            } catch (ClassNotFoundException exc) {
                throw new RuntimeException(exc);
            }

        });
        JButton ga = new JButton("历史成绩");
        ga.setBounds(350, 420, 100, 30);
        ga.addActionListener(e -> {
            try {
                HistoryGradeUI historyGradeUI=new HistoryGradeUI();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        });
        mainFrame.add(ga);
        mainFrame.add(examBtn);

        specializedFrame = new JFrame("专项练习");
        unitFrame = new JFrame("单元练习");
        wrongFrame = new JFrame("错题练习");
        examFrame = new JFrame("模拟考试");
        garder=new JFrame("历史成绩");


        specializedFrame.setBounds(400, 100, 800, 640);
        unitFrame.setBounds(400, 100, 800, 640);
        wrongFrame.setBounds(400, 100, 800, 640);
        examFrame.setBounds(400, 100, 800, 640);
garder.setBounds(400,100,800,640);
        specializedFrame.setLayout(null);
        unitFrame.setLayout(null);
        wrongFrame.setLayout(null);
        examFrame.setLayout(null);
garder.setLayout(null);
        mainFrame.setVisible(true);
        JMenuBar menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);

        JMenu operationsMenu = new JMenu("设置");
        menuBar.add(operationsMenu);

        JMenuItem addMenuItem = new JMenuItem("增加");
        addMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpData up = new UpData();

            }
        });
        operationsMenu.add(addMenuItem);

        JMenuItem deleteMenuItem = new JMenuItem("删除");
        deleteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 添加删除操作的逻辑
                DeleteUI dl = new DeleteUI();
            }
        });
        operationsMenu.add(deleteMenuItem);

        JMenuItem updateMenuItem = new JMenuItem("修改");
        updateMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 添加修改操作的逻辑
            }
        });
        operationsMenu.add(updateMenuItem);

        JMenuItem searchMenuItem = new JMenuItem("查询");
        searchMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 直接创建 FindUI 对象，而不是隐藏主窗口
                FindUI f = new FindUI();
            }
        });
        operationsMenu.add(searchMenuItem);
    }

    public static void main(String[] args) {
        new ExumStartUI();
    }
}