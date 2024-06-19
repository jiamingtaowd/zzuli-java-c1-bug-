package UpUI;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteUI {
    private JFrame jFrame;
    private JLabel num;
    private JTextField jt;

    public DeleteUI(){
        jFrame = new JFrame("删除题目");
        jFrame.setBounds(400, 100,800,640);
        jFrame.setLayout(null);
        num = new JLabel("题号:");
        num.setBounds(150,150,150,100);
        jt = new JTextField();
        jt.setBounds(400,150,250,50);

        JButton deleteButton = new JButton("删除题目");
        deleteButton.setBounds(250,300,300,50);
        deleteButton.addActionListener(e -> {
            try {
                String driver = "com.mysql.cj.jdbc.Driver";
                String url = "jdbc:mysql://127.0.0.1:3306/c1";
                String user = "root";
                String password = "123456";

                Connection conn = DriverManager.getConnection(url, user, password);

                String sql = "DELETE FROM 题库 WHERE num=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, jt.getText());

                int rowsDeleted = ps.executeUpdate();
                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(jFrame, "题目删除成功");
                } else {
                    JOptionPane.showMessageDialog(jFrame, "未找到匹配的题目");
                }

                ps.close();
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(jFrame, "删除题目失败: " + ex.getMessage());
            }
        });

        jFrame.add(num);
        jFrame.add(jt);
        jFrame.add(deleteButton);

        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DeleteUI::new);
    }
}