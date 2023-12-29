package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.sql.Date;

public class DBDemo {
    public static void main(String[] args) {

        Queries queries = Queries.getInstance();
        // queries.createTable();
        // queries.createEmployee(new Employee(1, "Smith", 100000, "male",
        // "2018-10-21"));
        // queries.createEmployee(new Employee(2, "John", 200000, "male",
        // "2019-12-21"));
        // queries.createEmployee(new Employee(3, "Alice", 300000, "female",
        // "2020-11-20"));
        // queries.createEmployee(new Employee(4, "Teresa", 250000, "female",
        // "2019-04-10"));
        // queries.createEmployee(new Employee(5, "Smith", 450000, "male",
        // "2018-04-11"));
        // queries.updateSalary(4, 300000);
        // queries.readEmployee();
        // List<Employee> emp = queries.getEmployeeByName("Smith");
        // System.out.println(emp.toString());

        // Date startDate = Date.valueOf("2019-01-01");
        // Date endDate = Date.valueOf("2022-12-31");
        // List<Employee> empInDateRange = queries.getEmployeesbyJoiningDate(startDate,
        // endDate);

        // for (Employee employee : empInDateRange) {
        // System.out.println(employee);
        // }

        queries.getGenderStatistics();

    }
}