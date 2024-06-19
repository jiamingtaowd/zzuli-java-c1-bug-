package UpUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryGradeUI {
    private JFrame frame;
    private JTable table;

    public HistoryGradeUI() throws SQLException {
        frame = new JFrame("历史成绩");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(400, 100, 800, 640);

        // 创建一个 JTable 对象来显示历史成绩
        table = new JTable();

        // 创建一个 JScrollPane 对象来包含 JTable
        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(scrollPane);

        // 从数据库中获取历史成绩并显示在 JTable 中
        List<HistoryGrade> grades = getHistoryGradesFromDatabase();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("序号");
        model.addColumn("分数");

        for (HistoryGrade grade : grades) {
            model.addRow(new Object[]{grade.getId(), grade.getScore()});
        }

        table.setModel(model);

        frame.setVisible(true);
    }

    private List<HistoryGrade> getHistoryGradesFromDatabase() throws SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/c1";
        String user = "root";
        String password = "123456";

        Connection conn = DriverManager.getConnection(url, user, password);

        String sql = "SELECT * FROM 历史成绩";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<HistoryGrade> grades = new ArrayList<>();

        while (rs.next()) {
            HistoryGrade grade = new HistoryGrade();
            grade.setId(rs.getInt("sum"));
            grade.setScore(rs.getInt("garder"));
            grades.add(grade);
        }

        rs.close();
        ps.close();
        conn.close();

        return grades;
    }
}

class HistoryGrade {
    private int id;
    private int score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}