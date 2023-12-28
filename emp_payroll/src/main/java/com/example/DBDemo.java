package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/payroll_service";
        String user = "root";
        String password = "Ganesh1*%9";
        Connection connection;

        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successfull!!" + connection);
        } catch (SQLException s) {
            System.out.println("sql exception");
        }
    }
}