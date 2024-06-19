package UpUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WrongQuestionsPractice extends JFrame {
    private List<Titles> titles = new ArrayList<>();

    public WrongQuestionsPractice() {
        setTitle("错题练习");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel questionsPanel = new JPanel();
        questionsPanel.setLayout(new BoxLayout(questionsPanel, BoxLayout.Y_AXIS));

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/c1", "root", "123456");
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM wrong");
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                PreparedStatement ps2 = connection.prepareStatement("SELECT * FROM 题库 WHERE num = ?");
                ps2.setInt(1, resultSet.getInt("num"));
                ResultSet rs = ps2.executeQuery();

                if (rs.next()) {
                    Titles title1 = new Titles();
                    title1.setNum(rs.getInt("num"));
                    title1.setQuestionTypes(rs.getString("题型"));
                    title1.setTitles(rs.getString("Titles"));
                    title1.setA(rs.getString("A"));
                    title1.setB(rs.getString("B"));
                    title1.setC(rs.getString("C"));
                    title1.setD(rs.getString("D"));
                    title1.setDaan(rs.getString("danan"));
                    title1.setScores(rs.getString("Scores"));
                    title1.setJiexi(rs.getString("解析"));
                    titles.add(title1);

                    WrongQuestionPanel panel = new WrongQuestionPanel("题目：" + title1.getTitles(), title1.getDaan(), title1);
                    questionsPanel.add(panel);
                }
            }

            JScrollPane scrollPane = new JScrollPane(questionsPanel);
            add(scrollPane);
            setBounds(400, 100,800,640);
            setLocationRelativeTo(null);
            setVisible(true);

            resultSet.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WrongQuestionsPractice();
        });
    }
}

class WrongQuestionPanel extends JPanel {
    private JRadioButton optionARbtn, optionBRbtn, optionCRbtn, optionDRbtn;
    private String correctAnswer;
    private JLabel resultLabel;

    public WrongQuestionPanel(String question, String correctAnswer, Titles title) {
        this.correctAnswer = correctAnswer;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // 设置组件间间距

        add(new JLabel(question), gbc);
        gbc.gridy++;

        addMultipleChoiceOptions(title, gbc);
        addSubmitButton();
        addResultLabel();
    }

    private void addMultipleChoiceOptions(Titles title, GridBagConstraints gbc) {
        optionARbtn = new JRadioButton("A. " + title.getA());
        gbc.anchor = GridBagConstraints.WEST;
        add(optionARbtn, gbc);

        gbc.gridy++;
        optionBRbtn = new JRadioButton("B. " + title.getB());
        add(optionBRbtn, gbc);

        gbc.gridy++;
        optionCRbtn = new JRadioButton("C. " + title.getC());
        add(optionCRbtn, gbc);

        gbc.gridy++;
        optionDRbtn = new JRadioButton("D. " + title.getD());
        add(optionDRbtn, gbc);
    }

    private void addSubmitButton() {
        JButton submitButton = new JButton("提交");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy++;  // 将提交按钮放置在当前组件的下方
        gbc.weightx=1;
        gbc.weighty=1;
        gbc.fill = GridBagConstraints.HORIZONTAL;  // 确保提交按钮填充整个水平空间
        add(submitButton, gbc);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (optionARbtn.isSelected() && correctAnswer.equals("A") ||
                        optionBRbtn.isSelected() && correctAnswer.equals("B") ||
                        optionCRbtn.isSelected() && correctAnswer.equals("C") ||
                        optionDRbtn.isSelected() && correctAnswer.equals("D")) {
                    resultLabel.setText("回答正确，答案是：" + correctAnswer);
                } else {
                    resultLabel.setText("回答错误，正确答案是：" + correctAnswer);
                }
            }
        });
    }

    private void addResultLabel() {
        resultLabel = new JLabel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1; // 设置为1以将结果标签放在右侧
        gbc.gridy = 2; // 将结果标签放在第3行
        gbc.gridwidth = 2; // 设置跨2列
        gbc.gridheight = 2; // 设置跨2行
        gbc.anchor = GridBagConstraints.SOUTHEAST; // 设置右下角对齐
        add(resultLabel, gbc);
    }
}


