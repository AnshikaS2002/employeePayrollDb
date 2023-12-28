package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Queries {
    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/payroll_service";
        String user = "root";
        String password = "Ganesh1*%9";
        return DriverManager.getConnection(url, user, password);
    }

    public void createTable() {
        String sqlQuery = "create table employees(id int primary key,name varchar(50),salary int, gender varchar(10))";
        try (Connection connection = getConnection();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlQuery);
            System.out.println("Table created Successfully");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void createEmployee(Employee employee) {
        String sqlQuery = "insert into employees(id,name,salary,gender) values (?,?,?,?)";

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setInt(3, employee.getSalary());
            preparedStatement.setString(4, employee.getGender());

            preparedStatement.executeUpdate();
            System.out.println("Employee added successfully");

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void readEmployee() {
        List<Employee> employeeList = new ArrayList<>();
        String sqlQuery = "select * from employees";

        try (Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int salary = resultSet.getInt("salary");
                String gender = resultSet.getString("gender");
                Employee employee = new Employee(id, name, salary, gender);
                employeeList.add(employee);
                System.out.println("ID: " + id + ",Name: " + name + ",Salary: " + salary + ",Gender:" + gender);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
