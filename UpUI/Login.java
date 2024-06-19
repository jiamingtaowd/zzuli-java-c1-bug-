package UpUI;

import java.sql.*;


public class Login {

    examer er;

    void setAdmin(examer er) {
        this.er=er;
        //System.out.println(this.admin.getPassword()+"   " + this.admin.getID());
    }
    /*
     * JudgeAdmin()方法
     * 判断Admin的ID和密码是否正确，如果正确，显示登录成功
     * 如果错误，弹出一个窗口，显示账号或密码错误
     */
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/c1";//?是要操作的数据库对像
    private String user = "root";
    private String password = "123456";



    public boolean login(examer er) throws SQLException, ClassNotFoundException {
        String sql="select * from user where id=? and password=?";

        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, er.getId());//给统配符号？赋值
        ps.setString(2, er.getPassword());
        ResultSet rs = ps.executeQuery();
        int ans = 0;
        if(rs.next()) {
            ans = 1;
        }
        rs.close();
        ps.close();
        conn.close();
        if(ans == 1) {
            return true;
        }
        else return false;
    }
    int JudgeAdmin() {

        try {
            if(login(this.er)) {
                System.out.println("登录成功");
                return 1;
            }else {
                return 0;
            }
        }catch(Exception e) {
            //e.printStackTrace();
            //System.out.println("!!!!!!!!!");
        }
        return 0;

    }
}