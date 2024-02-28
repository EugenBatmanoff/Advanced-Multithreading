package com.eugen.cf.emlpsalary;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class Main {
    public static void main(String[] args) {
        EmployeeService service = new EmployeeService();

        CompletableFuture<List<Employee>> employeesFuture = service.hiredEmployees();
        employeesFuture.thenApplyAsync(employees ->
                employees.stream()
                        .map(employee -> employee.toString())
                        .collect(Collectors.toList())
        ).thenAccept(employeeStrings -> {
            employeeStrings.forEach(System.out::println);
        }).join();

    }
}