package com.codelity;

import java.util.Comparator;
import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        TreeSet<Employee> treeSet = new TreeSet<>(new EmpIdComparator());
        treeSet.add(new Employee(123));
        treeSet.add(new Employee(1));

        System.out.println(treeSet);
    }

}


class Employee {
    int id;

    public Employee(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                '}';
    }
}

class EmpIdComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return Integer.compare(o1.getId(), o2.getId());
    }
}