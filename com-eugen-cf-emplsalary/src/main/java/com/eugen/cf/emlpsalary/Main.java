package com.eugen.cf.emlpsalary;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Hello world!
 *
 */
public class Main {
    public static void main(String[] args) {
        EmployeeService service = new EmployeeService();

        CompletableFuture<List<Employee>> employeesFuture = service.hiredEmployees();

        CompletableFuture<Void> allSalariesFilled = employeesFuture.thenCompose(employees -> {

            return CompletableFuture.allOf(employees.stream()
                    .map(employee -> service.getSalary(employee.getId())
                            .thenAccept(employee::setSalary)).toArray(CompletableFuture[]::new));
        });

        allSalariesFilled.thenAccept(v -> {
            List<Employee> employees = employeesFuture.join();
            employees.forEach(employee -> System.out.println("Employee ID: " + employee.getId() + ", Salary: " + employee.getSalary()));
        });
    }
}