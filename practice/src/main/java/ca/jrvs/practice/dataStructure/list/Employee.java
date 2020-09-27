package ca.jrvs.practice.dataStructure.list;

import java.util.Comparator;

public class Employee implements Comparable<Employee> {

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
    public int compareTo(Employee o) {
        int compareAge = ((Employee)o).getAge();
        return this.age - compareAge;
    }

    public static Comparator<Employee> salaryComparator = new Comparator<Employee>() {

        @Override
        public int compare(Employee o1, Employee o2) {
            long salary1 = o1.getSalary();
            long salary2 = o2.getSalary();

            return (int) (salary1 - salary2);
        }
    };

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }
}