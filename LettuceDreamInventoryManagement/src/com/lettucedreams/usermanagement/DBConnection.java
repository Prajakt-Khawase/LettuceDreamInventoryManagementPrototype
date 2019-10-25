/*
 * Project: Lettuce Dream Inventory Management
 * 
 * Description: The project deals with developing a Web-Application 
 * which can automate most of the inventory and order managment  
 * task for Lettuce Dream Co. which is a nonprofit organization. 
 * Currently the organization is dealing with various records and lots of 
 * information which is being tracked using spreadsheets. 
 * The development of this system will help the organization maintain their Order,
 * Product and Employee information.
 *  
 *  File Description: DAO Class to connect with Database.
 *  
 * Author: Prajakt Uttamrao Khawase.
 * 
 * Date: 10/19/2019
 */


package com.lettucedreams.usermanagement;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection getConnection() {
		// Declare and Initialize the Connection Object.
		Connection con = null;
	     String url = "jdbc:oracle:thin:@localhost:1521/orclpdb";
	     String userName = "hr";
	     String password = "hr";
		try {
			// Loading the Driver Class.
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Opening the connection with database.
			con = DriverManager.getConnection(url, userName, password );
		}

		// Catch if exception occures
		catch (Exception e) {
			System.out.println(e);
		}

		// Return the connection object to caller.
		return con;
	}

}
