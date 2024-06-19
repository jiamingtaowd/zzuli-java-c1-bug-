package UpUI;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class UpData {
    private JFrame frame;
    private JTextField numTextField;
    private JTextField typeTextField;
    private JTextField titleTextField;
    private JTextField scoreTextField;
    private JTextField aTextField;
    private JTextField bTextField;
    private JTextField cTextField;
    private JTextField dTextField;
    private JTextField analysisTextField;
    private JTextField answerTextField;

    public UpData() {
        frame = new JFrame("更改题库");
        frame.setBounds(400, 100,800,640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 2));


        JLabel typeLabel = new JLabel("题型:");
        typeTextField = new JTextField();
        frame.add(typeLabel);
        frame.add(typeTextField);

        JLabel answerLabel = new JLabel("答案:");
        answerTextField = new JTextField();
        frame.add(answerLabel);
        frame.add(answerTextField);

        JLabel scoreLabel = new JLabel("分值:");
        scoreTextField = new JTextField();
        frame.add(scoreLabel);
        frame.add(scoreTextField);

        JLabel aLabel = new JLabel("选项A:");
        aTextField = new JTextField();
        frame.add(aLabel);
        frame.add(aTextField);

        JLabel bLabel = new JLabel("选项B:");
        bTextField = new JTextField();
        frame.add(bLabel);
        frame.add(bTextField);

        JLabel cLabel = new JLabel("选项C:");
        cTextField = new JTextField();
        frame.add(cLabel);
        frame.add(cTextField);

        JLabel dLabel = new JLabel("选项D:");
        dTextField = new JTextField();
        frame.add(dLabel);
        frame.add(dTextField);

        JLabel analysisLabel = new JLabel("解析:");
        analysisTextField = new JTextField();
        frame.add(analysisLabel);
        frame.add(analysisTextField);


        JLabel titleLabel = new JLabel("题目:");
        titleTextField = new JTextField();
        frame.add(titleLabel);
        frame.add(titleTextField);

        JLabel numLabel = new JLabel("题号:");
        numTextField = new JTextField();
        frame.add(numLabel);
        frame.add(numTextField);

        JButton updateButton = new JButton("更新题目");
        updateButton.addActionListener(e -> {
            try {
                String driver = "com.mysql.cj.jdbc.Driver";
                String url = "jdbc:mysql://127.0.0.1:3306/c1";
                String user = "root";
                String password = "123456";
                Class.forName(driver);
//先删除要改的原题
                Connection conn = DriverManager.getConnection(url, user, password);
                String sql1 = "DELETE FROM 题库 WHERE num=?";
                PreparedStatement ps1=conn.prepareStatement(sql1);
                ps1.setString(1,numTextField.getText());
                //在加入原题
                String sql = "UPDATE 题库 SET  题型= ?,danan=?, Scores=?, A=?, B=?, C=?, D=?, 解析=?,Titles=? WHERE num=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, typeTextField.getText());
                ps.setString(2, answerTextField.getText());
                ps.setInt(3, Integer.parseInt(scoreTextField.getText()));
                ps.setString(4, aTextField.getText());
                ps.setString(5, bTextField.getText());
                ps.setString(6, cTextField.getText());
                ps.setString(7,dTextField.getText());
                ps.setString(8, analysisTextField.getText());
                ps.setString(9, titleTextField.getText());
                ps.setString(10, numTextField.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(frame, "题目更新成功！");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "题目更新失败：" + ex.getMessage());
            }
        });
        frame.add(updateButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UpData());
    }
}
