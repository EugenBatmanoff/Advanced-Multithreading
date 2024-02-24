package com.eugen.cf.emlpsalary;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmployeeService {
    private final List<Employee> employees = Arrays.asList(
            new Employee("1", 1000.0),
            new Employee("2", 2000.0),
            new Employee("3", 3000.0)
    );

    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    public CompletableFuture<List<Employee>> hiredEmployees() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000); // Simulate delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return employees;
        }, executor);
    }

    public CompletableFuture<Double> getSalary(String hiredEmployeeId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000); // Simulate delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return employees.stream()
                    .filter(employee -> employee.getId().equals(hiredEmployeeId))
                    .findFirst()
                    .map(Employee::getSalary)
                    .orElse(null);
        }, executor);
    }
}