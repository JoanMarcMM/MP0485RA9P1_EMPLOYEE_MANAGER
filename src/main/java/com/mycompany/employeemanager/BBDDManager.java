/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employeemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jooan
 */
public class BBDDManager {
    
    //Set variables for database conection and object Connection
    
    private static String connectionInfo = "jdbc:mysql://localhost:3306/";
    private static String database = "employeedatabase";
    private static String connectionInfoQuery = connectionInfo+database;
    private static String user = "root";
    private static String password = "";
    
    private Connection con;
    
    
    //Method to start program
    public void StartBBDD() {

        try {
            //Set connection to database with previous variables and set it into con.
            con = DriverManager.getConnection(connectionInfo, user, password);

            try {
                //Call the methods to create database and employeesTable
                createDDBB();

                createEmployeesTable();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    //Method to create database
    public void createDDBB() {
        
        //Create string with query and object Statement
        String query = "CREATE DATABASE IF NOT EXISTS " + database + ";";
        
        Statement stmt = null;
        
        //Create statement with connection, and execute query
        try {
            con = DriverManager.getConnection(connectionInfo, user, password);
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            
            

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createEmployeesTable() {
        
        //Create query and object statement
        String query = " CREATE TABLE IF NOT EXISTS EMPLOYEES ("
                + "ID INT PRIMARY KEY AUTO_INCREMENT,"
                + "NAME VARCHAR(100) NOT NULL,"
                + "AGE INT NOT NULL,"
                + "DEPARTMENT VARCHAR(100) NOT NULL,"
                + "SALARY DECIMAL(10,2) NOT NULL) ;";

        Statement stmt = null;
        
        //Execute query
        try {
            con = DriverManager.getConnection(connectionInfoQuery, user, password);
            
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            
            

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(BBDDManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    //Method to add employee, needs and Employee object
    public void addEmployee(Employee employee) throws SQLException {
        
        createEmployeesTable();
        
        //Create insert query with emloyee values and statement object
        String query = "INSERT INTO EMPLOYEES (name,age,department,salary) VALUES ('" + employee.getName() + "'," + employee.getAge() + ",'" + employee.getDepartment() + "'," + employee.getSalary() + ");";

        Statement stmt = null;
        //Exectue query
        try {

            stmt = con.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();

        }finally{
            stmt.close();
        }

    }
    //Method to get employee needs int and returns employee
    public Employee getEmployee(int id) throws SQLException {
        //Create query and statement and resultset objects.
        String query = "SELECT * FROM EMPLOYEES WHERE ID =" + id + ";";

        Statement stmt = null;
        ResultSet rs = null;
        
        //Create employee
        Employee employee;
        //Execute query and save result on rs, enter the result with next and 
        //use result to create employee
        stmt = con.createStatement();
        rs = stmt.executeQuery(query);
        rs.next();
        employee = new Employee(rs.getInt("ID"), rs.getString("NAME"), rs.getInt("AGE"), rs.getString("DEPARTMENT"), rs.getDouble("SALARY"));
        
        //Return the employee
        return employee;

    }
    
    //Method to update employee , needs emloyee object
    public void updateEmployee(Employee employee) throws SQLException {
        //Create query with employee info and statement object
        String query = "UPDATE EMPLOYEES SET NAME = '" + employee.getName() + "',AGE = " + employee.getAge() + ",DEPARTMENT = '" + employee.getDepartment() + "', SALARY = " + employee.getSalary() + " WHERE ID = " + employee.getId() + ";";

        Statement stmt = null;
        //Execute query
        try {
            
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }

    //Method to find emplyee needs int and returns boolean
    public boolean findEmployee(int id) throws SQLException {
        //Create query with int id, statement and resultset objects, and boolean to control if found
        String query = "SELECT * FROM EMPLOYEES WHERE ID = " + id + ";";

        boolean found = false;

        Statement stmt = null;
        ResultSet rs = null;
        
        //Execute query, if next in resultset means theres employee with id, therefore found true
        try {

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                found = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
        //Return found,
        return found;
    }
    
    
    //Method to remove employee needs int
    public void removeEmployee(int id) throws SQLException {
        //Create query and statement object
        String query = "DELETE FROM EMPLOYEES WHERE ID = " + id + ";";

        Statement stmt = null;
        
        //Execute query
        try {

            stmt = con.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }
    
    //Method to getallemployees needs arraylist employee
    public void getAllEmployees(ArrayList<Employee> employees) throws SQLException {
        //Create query to select all employees and statement and resut set objcets
        String query = "SELECT * FROM EMPLOYEES;";

        Statement stmt = null;
        ResultSet rs = null;
        //Execute query
        try {

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            //While next create new employee and add it into array with info
            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("department"),
                        rs.getDouble("salary")
                );
                employees.add(employee);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }

}
