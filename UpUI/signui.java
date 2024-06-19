package UpUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class signui extends JFrame {


    public signui() {
        init();
    }

    //登录界面初始化
    public void init() {
        JFrame frame = new JFrame("登录");
        frame.setLayout(null);

        JLabel nameStr = new JLabel("账号:");
        nameStr.setBounds(250, 200, 100, 25);
        frame.add(nameStr);

        JLabel passwordStr = new JLabel("密码:");
        passwordStr.setBounds(250, 250, 100, 25);
        frame.add(passwordStr);

        JTextField userID = new JTextField();
        userID.setBounds(300, 200, 150, 25);
        frame.add(userID);

        JPasswordField password = new JPasswordField();
        password.setBounds(300, 250, 150, 25);
        frame.add(password);

        JButton buttonlogin = new JButton("登录");
        buttonlogin.setBounds(275, 300, 70, 25);
        frame.add(buttonlogin);

        JButton buttonregister = new JButton("注册");
        buttonregister.setBounds(375, 300, 70, 25);
        frame.add(buttonregister);

        frame.setBounds(400, 100, 800, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //为登录按钮添加监听器
        buttonlogin.addActionListener(e -> {
            String ID = userID.getText();
            String passwd = new String(password.getPassword());

            //创建一个Admin用户，把输入框中的用户名密码和提出来
            examer er = new examer();
            er.setId(ID);
            er.setPassword(passwd);

            //登录
            Login login = new Login();
            login.setAdmin(er);

            if (login.JudgeAdmin() == 0) {
                //弹出账号或密码错误的窗口
                JOptionPane.showMessageDialog(null, "账号或密码错误", "账号或密码错误", JOptionPane.WARNING_MESSAGE);
                //清除密码框中的信息
                password.setText("");
                //清除账号框中的信息
                userID.setText("");

                //System.out.println("登陆失败");
            } else {
                //弹出登录成功的窗口
                JOptionPane.showMessageDialog(null, "登陆成功", "登陆成功", JOptionPane.NO_OPTION);
                //点击确定后会跳转到主窗口
                frame.setVisible(false);
ExumStartUI ui=new ExumStartUI();

            }

        });
       buttonregister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upsignUI ar= new upsignUI();
            }
        });

    }
}






