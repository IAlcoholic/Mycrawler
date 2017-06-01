package db;

import model.jsmodel;

import java.sql.*;

/**
 * Created by Administrator on 2017/5/23.
 */
public class jdbc {
    private static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/mydata";
        String username = "root";
        String password = "123456";
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver"); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public  int insert(jsmodel jsmodel) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into jianshu_data (js_name,js_title,js_content) values(?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, jsmodel.getJs_name());
            pstmt.setString(2, jsmodel.getJs_title());
            pstmt.setString(3, jsmodel.getJs_content());
             pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public   int update(jsmodel jsmodel) {
        Connection conn = getConn();
        int i = 0;
        String sql = "update jsmodel set Age='" + jsmodel.getJs_name() + "' where Name='" + jsmodel.getJs_id() + "'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            System.out.println("resutl: " + i);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public Integer getAll() {
        Connection conn = getConn();
        String sql = "select * from jianshu_data";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            System.out.println("============================");
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                    if ((i == 2) && (rs.getString(i).length() < 8)) {
                        System.out.print("\t");
                    }
                }
                System.out.println("");
            }
            System.out.println("============================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
