package UpUI;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class FindUI {
    private JFrame frame;
    private JTextField numTextField;
    private JTextArea resultTextArea;

    public FindUI() {
        frame = new JFrame("查找题目");
        JLabel numLabel = new JLabel("题号:");
        numTextField = new JTextField(10);
        JButton findButton = new JButton("查询");
        resultTextArea = new JTextArea(10, 30);

        JPanel inputPanel = new JPanel();
        inputPanel.add(numLabel);
        inputPanel.add(numTextField);
        inputPanel.add(findButton);

        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        findButton.addActionListener(e -> {
            try {
                String driver = "com.mysql.cj.jdbc.Driver";
                String url = "jdbc:mysql://127.0.0.1:3306/c1";
                String user = "root";
                String password = "123456";

                Connection conn = DriverManager.getConnection(url, user, password);

                String sql = "SELECT * FROM 题库 WHERE num=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, numTextField.getText());

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String questionType = rs.getString("题型"); // 获取题型
                    System.out.println("获取到的题型: " + questionType);
                    if (questionType.equals("2")||questionType.equals("1")) {
                        resultTextArea.setText("题号: " + rs.getString("num") + "\n"
                                + "题目内容: " + rs.getString("Titles") + "\n"
                                + "答案: " + rs.getString("danan") + "\n" + "A" + rs.getString("A") + "      " + "B" + rs.getString("B") + "\n" +
                                "C" + rs.getString("C") + "       " + "D" + rs.getString("D"));
                    }
                    else {
                        resultTextArea.setText("题号: " + rs.getString("num") + "\n"
                                + "题目内容: " + rs.getString("Titles") + "\n"
                                + "答案: " + rs.getString("danan") + "\n" + "A" + rs.getString("A") + "      " + "B" + rs.getString("B") + "\n");
                    }
                } else {
                    resultTextArea.setText("未找到匹配的题目");
                }

                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException ex) {
                resultTextArea.setText("查询题目失败: " + ex.getMessage());
            }
        });

        // 计算窗口居中位置
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;

        frame.setLocation(x, y);

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FindUI::new);
    }
}