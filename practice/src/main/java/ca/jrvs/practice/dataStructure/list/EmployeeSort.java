package ca.jrvs.practice.dataStructure.list;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeSort {

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Mao", 75, 100000));
        employees.add(new Employee(2, "Xi", 69, 9999999));
        employees.add(new Employee(3, "Trump", 74, 500000));

        // using Comparable<T>
        Collections.sort(employees);
        employees.forEach(System.out::println);
        System.out.println("\n");

        // using Comparator<T>
        employees.sort(Employee.salaryComparator);
        employees.forEach(System.out::println);
    }

}
