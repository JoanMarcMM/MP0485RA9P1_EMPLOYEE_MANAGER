/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.employeemanager;

import java.sql.SQLException;

/**
 *
 * @author jooan
 */
public class EmployeeManager {
//Create BBDDmanager object, call for start bbdd method, create frem and send db object with it
    public static void main(String[] args) throws SQLException {
        
        BBDDManager db = new BBDDManager();
        
        db.StartBBDD();
        
        Menu frame = new Menu(db);
        frame.setVisible(true);
    }
}
