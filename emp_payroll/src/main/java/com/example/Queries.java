package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Date;
import java.util.List;

import com.mysql.cj.xdevapi.PreparableStatement;

import java.util.HashMap;

public class Queries {
    private static Queries instance;
    private Map<String, PreparedStatement> preparedStatements;

    private Queries() {
        preparedStatements = new HashMap<>();
    }

    public static Queries getInstance() {
        if (instance == null) {
            instance = new Queries();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/payroll_service";
        String user = "root";
        String password = "Ganesh1*%9";
        return DriverManager.getConnection(url, user, password);
    }

    public void createTable() {
        String sqlQuery = "create table employees(id int primary key,name varchar(50),salary int, gender varchar(10), joining_date date)";
        try (Connection connection = getConnection();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlQuery);
            System.out.println("Table created Successfully");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void createEmployee(Employee employee) {
        String sqlQuery = "insert into employees(id,name,salary,gender,joining_date) values (?,?,?,?,?)";

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setInt(3, employee.getSalary());
            preparedStatement.setString(4, employee.getGender());
            preparedStatement.setString(5, employee.getJoining_date());

            preparedStatement.executeUpdate();
            System.out.println("Employee added successfully");

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void updateSalary(int id, int salary) {
        String sqlQuery = "update employees set salary=? where id=?";
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, salary);
            preparedStatement.setInt(2, id);
            int rowCount = preparedStatement.executeUpdate();
            if (rowCount > 0) {
                System.out.println("Salary updated for Employee ID : " + id);
            } else {
                System.out.println("Unable to update Salary for Employee ID : " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
                String joining_date = resultSet.getString("joining_date");
                Employee employee = new Employee(id, name, salary, gender, joining_date);
                employeeList.add(employee);
                System.out.println("ID: " + id + ",Name: " + name + ",Salary: " + salary + ",Gender: " + gender
                        + ",Joining Date: " + joining_date);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public List<Employee> getEmployeeByName(String name) {
        List<Employee> employeeList = new ArrayList<>();
        String sqlQuery = "select * from employees where name = ?";

        try {
            if (!preparedStatements.containsKey(sqlQuery)) {
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatements.put(sqlQuery, preparedStatement);

            }

            PreparedStatement preparedStatement = preparedStatements.get(sqlQuery);
            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int salary = resultSet.getInt("salary");
                    String gender = resultSet.getString("gender");
                    String joining_date = resultSet.getString("joining_date");
                    Employee employee = new Employee(id, name, salary, gender, joining_date);
                    employeeList.add(employee);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return employeeList;
    }

    public List<Employee> getEmployeesbyJoiningDate(Date startDate, Date endDate) {
        List<Employee> employeeList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM employees WHERE joining_date BETWEEN ? AND ?";

        try {
            if (!preparedStatements.containsKey(sqlQuery)) {
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatements.put(sqlQuery, preparedStatement);
            }

            PreparedStatement preparedStatement = preparedStatements.get(sqlQuery);
            preparedStatement.setDate(1, startDate);
            preparedStatement.setDate(2, endDate);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int salary = resultSet.getInt("salary");
                    String gender = resultSet.getString("gender");
                    String jd = resultSet.getString("joining_date");
                    Employee e = new Employee(id, name, salary, gender, jd);
                    employeeList.add(e);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return employeeList;
    }

    public void getGenderStatistics() {
        String sqlQuery = "SELECT gender, COUNT(*) AS count,"
                + "SUM(salary) AS totalSalary, AVG(salary) AS avgSalary,"
                + "MIN(salary) AS minSalary, MAX(salary) AS maxSalary "
                + "FROM employees GROUP BY gender";

        try (Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery)) {

            while (resultSet.next()) {
                String gender = resultSet.getString("gender");
                int count = resultSet.getInt("count");
                int totalSalary = resultSet.getInt("totalSalary");
                double avgSalary = resultSet.getDouble("avgSalary");
                int minSalary = resultSet.getInt("minSalary");
                int maxSalary = resultSet.getInt("maxSalary");

                System.out.println("Gender: " + gender);
                System.out.println("Number of Employees: " + count);
                System.out.println("Total Salary: " + totalSalary);
                System.out.println("Average Salary: " + avgSalary);
                System.out.println("Min Salary: " + minSalary);
                System.out.println("Max Salary: " + maxSalary);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}
