package UpUI;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ExamFrameUI {
    private JFrame frame;
    private List<Titles> titlesList;
    private int wrongCount;
    private int totalScores;
    private List<String> daan;
    private JCheckBox optionACheckBox;
    private JCheckBox optionBCheckBox;
    private JCheckBox optionCCheckBox;
    private JCheckBox optionDCheckBox;
    private JLabel timeLabel;
    private int remainingTimeInSeconds;
    private Timer timer;

    public ExamFrameUI() {
        daan = new ArrayList<>();
        frame = new JFrame("选择题练习");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(400, 100, 800, 640);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        titlesList = new ArrayList<>();
        wrongCount = 0;
        totalScores = 0;
        remainingTimeInSeconds = 45 * 60;
    }

    public void displayQuestionsFromDatabase() throws ClassNotFoundException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/c1";
        String user = "root";
        String password = "123456";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Class.forName(driver);

            String sql = "SELECT * FROM 题库";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Titles titles = new Titles();
                titles.setNum(rs.getInt("num"));
                titles.setQuestionTypes(rs.getString("题型"));
                titles.setTitles(rs.getString("Titles"));
                titles.setA(rs.getString("A"));
                titles.setB(rs.getString("B"));
                titles.setC(rs.getString("C"));
                titles.setD(rs.getString("D"));
                titles.setDaan(rs.getString("danan"));
                titles.setScores(rs.getString("Scores"));
                titles.setJiexi(rs.getString("解析"));
                titlesList.add(titles);
            }
            rs.close();
            ps.close();

            // 创建一个总的面板用于放置所有题目
            JPanel allQuestionsPanel = new JPanel();

            allQuestionsPanel.setLayout(new BoxLayout(allQuestionsPanel, BoxLayout.Y_AXIS));

            for (Titles titles : titlesList) {
                int j = 1;
                j++;
                String ti;
                boolean t;
                if (titles.getQuestionTypes().equals("1") || titles.getQuestionTypes().equals("2")) {
                    ti = "选择题";
                    t = true;
                } else {
                    ti = "判断题";
                    t = false;
                }
                JLabel questionLabel = new JLabel(titles.getNum() + "[" + ti + "]" + titles.getTitles());
                optionACheckBox = new JCheckBox("A. " + titles.getA());
                optionBCheckBox = new JCheckBox("B. " + titles.getB());
                optionCCheckBox = new JCheckBox("C. " + titles.getC());
                optionDCheckBox = new JCheckBox("D. " + titles.getD());

                JPanel questionPanel = new JPanel();
                questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
                questionPanel.add(questionLabel);
                if (t) {
                    questionPanel.add(optionACheckBox);
                    questionPanel.add(optionBCheckBox);
                    questionPanel.add(optionCCheckBox);
                    questionPanel.add(optionDCheckBox);
                } else {
                    questionPanel.add(optionACheckBox);
                    questionPanel.add(optionBCheckBox);
                }
                allQuestionsPanel.add(questionPanel);
            }

            // 创建滚动面板并添加总题目面板
            JScrollPane scrollPane = new JScrollPane(allQuestionsPanel);
            frame.add(scrollPane);

            timeLabel = new JLabel();
            updateTimeLabel();
            JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            timePanel.add(timeLabel);
            frame.add(timePanel, BorderLayout.NORTH);

            JButton submitButton = new JButton("提交答案");
            submitButton.addActionListener(e -> {
                List<String> userAnswers = new ArrayList<>();
                if (optionACheckBox.isSelected()) userAnswers.add("A");
                if (optionBCheckBox.isSelected()) userAnswers.add("B");
                if (optionCCheckBox.isSelected()) userAnswers.add("C");
                if (optionDCheckBox.isSelected()) userAnswers.add("D");

                if (userAnswers.size() == titlesList.size()) {
                    totalScores = 0;
                    wrongCount = 0;

                    for (int i = 0; i < titlesList.size(); i++) {
                        Titles currentTitles = titlesList.get(i);
                        String currentAnswer = userAnswers.get(i);

                        if (!currentAnswer.equals(currentTitles.getDaan())) {
                            wrongCount++;
                            String sql2 = "select num from wrong where num=" + currentTitles.getNum();
                            PreparedStatement psql2;
                            try {
                                psql2 = conn.prepareStatement(sql2);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            ResultSet resultSet = null;
                            try {
                                resultSet = psql2.executeQuery();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            try {
                                if (resultSet.next()) {
                                    // 将错题的题号保存到数据库
                                    try (PreparedStatement insertStmt = conn.prepareStatement(
                                            "INSERT INTO wrong (num) VALUES (?)")) {
                                        insertStmt.setInt(1, currentTitles.getNum());
                                        insertStmt.executeUpdate();
                                    } catch (SQLException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                } else {
                                    totalScores += Integer.parseInt(currentTitles.getScores());
                                }
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }

                    JOptionPane.showMessageDialog(frame, "答题结果：\n" +
                                    "总分：" + totalScores + "\n" +
                                    "错题数量：" + wrongCount,
                            "答题结果", JOptionPane.PLAIN_MESSAGE);
                }
            });

            frame.add(submitButton);

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    remainingTimeInSeconds--;
                    updateTimeLabel();

                    if (remainingTimeInSeconds == 0) {
                        timer.cancel();
                        JOptionPane.showMessageDialog(frame, "时间到，自动提交试卷");
                        submitButton.doClick();
                        frame.dispose();
                    }
                }
            }, 0, 1000);

            frame.setVisible(true);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void updateTimeLabel() {
        int minutes = remainingTimeInSeconds / 60;
        int seconds = remainingTimeInSeconds % 60;
        timeLabel.setText("剩余时间：" + String.format("%02d:%02d", minutes, seconds));
    }

    public static void main(String[] args) throws ClassNotFoundException {
        ExamFrameUI examFrameUI = new ExamFrameUI();
        examFrameUI.displayQuestionsFromDatabase();
    }
}