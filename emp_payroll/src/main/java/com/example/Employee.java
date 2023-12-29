package com.example;

public class Employee {
    private int id;
    private String name;
    private int salary;
    private String gender;
    private String joining_date;

    public Employee(int id, String name, int salary, String gender, String joining_date) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.gender = gender;
        this.joining_date = joining_date;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + salary +
                ", gender='" + gender + '\'' +
                ", joining_date='" + joining_date + '\'' +
                '}';
    }

    public String getJoining_date() {
        return joining_date;
    }

    public void setJoining_date(String joining_date) {
        this.joining_date = joining_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}