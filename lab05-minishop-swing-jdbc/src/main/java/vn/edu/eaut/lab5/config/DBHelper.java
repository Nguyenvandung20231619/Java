package vn.edu.eaut.lab5.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/minishop_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Đặt mật khẩu MySQL của bạn ở đây

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("-> Kết nối CSDL minishop_db thành công!");
            return conn;
        } catch (ClassNotFoundException e) {
            System.err.println("-> Kết nối thất bại: Không tìm thấy MySQL Driver!");
            e.printStackTrace();
            throw new SQLException("Không tìm thấy MySQL Driver", e);
        } catch (SQLException e) {
            System.err.println("-> Kết nối CSDL thất bại! Lỗi: " + e.getMessage());
            throw e;
        }
    }
}