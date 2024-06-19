package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД
    static final String DB_URL = "jdbc:mysql://localhost:3306/mydbtest";
    static final String USER = "root";
    static final String PWD = "Shifr-800";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}




