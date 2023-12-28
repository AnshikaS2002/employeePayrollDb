package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBDemo {
    public static void main(String[] args) {

        Queries queries = new Queries();
        // queries.createTable();
        // queries.createEmployee(new Employee(1, "Smith", 100000, "male"));
        // queries.createEmployee(new Employee(2, "John", 200000, "male"));
        // queries.createEmployee(new Employee(3, "Alice", 300000, "female"));
        queries.readEmployee();
    }
}