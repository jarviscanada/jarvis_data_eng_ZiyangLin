package ca.jrvs.practice.dataStructure.map;

import java.lang.reflect.Array;
import java.util.*;

public class Employee {

    private int id;
    private String name;
    private int age;
    private long salary;

    public Employee() {
    }

    public Employee(int id, String name, int age, long salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                age == employee.age &&
                salary == employee.salary &&
                Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, salary);
    }

    public static void main(String[] args) {
        Map<Employee, List<String>> empStrMap = new HashMap<>();

        // Amy
        Employee amy = new Employee(1,"Amy", 25, 45000);
        List<String> amyPrevComp = Arrays.asList("TD", "RBC", "CIBC");
        empStrMap.put(amy, amyPrevComp);

        // Bob
        Employee bob = new Employee(2,"Bob", 23, 40000);
        List<String> bobPrevComp = Arrays.asList("A&W", "Loblaws");
        empStrMap.put(bob, bobPrevComp);

        System.out.println("Bob hashcode: " + bob.hashCode());
        System.out.println("Bob value: " + empStrMap.get(bob).toString());
    }
}
