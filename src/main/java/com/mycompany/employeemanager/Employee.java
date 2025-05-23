/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employeemanager;

/**
 *
 * @author jooan
 */
public class Employee {
    //Attributes
    private int id;
    private String name ;
    private int age;
    private String department;
    private double salary;
    
    //Constructors

    public Employee(int id, String name, int age, String department, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }
    
    public Employee(String name, int age, String department, double salary) {
   
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }
    
    
    
    public int getId() {
        return id;
    }

    //Getters Setter
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    
}
