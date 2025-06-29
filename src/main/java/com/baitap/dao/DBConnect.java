package com.baitap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/learn_javaweb";
            String user = "root";
            String password = "";
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        if(getConnection() != null) {
            System.out.println("Kết nối thành công");
        } else {
            System.out.println("Kết nối KHÔNG thành công");
        }
    }
}
