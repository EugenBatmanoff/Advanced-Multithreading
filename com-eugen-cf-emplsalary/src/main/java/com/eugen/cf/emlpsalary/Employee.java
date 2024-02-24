package com.eugen.cf.emlpsalary;

public class Employee {
     private String id;
    private double salary;

    public Employee(String id, double salary) {
        this.id = id;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
