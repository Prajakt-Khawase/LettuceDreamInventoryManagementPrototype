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
 *  File Description: Servlet to Select User from Database
 *  
 * Author: Prajakt Uttamrao Khawase.
 * 
 * Date: 10/20/2019
 */

package com.lettucedreams.usermanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectUserServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
	IOException {
				
		// Fetching the parameter with id = uid and converting the String value into int.
		
		int userid = Integer.parseInt(request.getParameter("uid"));

		// Setting the mime type so that browser should know which kind of responce will it receive.

		response.setContentType("text/html");

		// Creating the Printwriter object by calling the getWriter(); factory method.
		// out object can be used to write streams to responce object.
		PrintWriter out = response.getWriter();
		try {
			// Fetching the connection which is established in DBConnection class.

			Connection con = DBConnection.getConnection();

			// Sending Query to Database and stored compile Query stored in pstmt object.

			PreparedStatement pstmt = con.prepareStatement("select  *  from  userinfo  where  userid=?");

			// Bind values to corrosponding placeholders(?)

			pstmt.setInt(1, userid); // set userid.

			// Use executeQuery() method To Execute select SQL command. Return type of
			// executeUpdate is ResultSet.

			ResultSet rs = pstmt.executeQuery();

			// If next element is present after cursor

			if (rs.next()) {
				out.println("Userid  : " + rs.getInt(1));
				out.println("Username : " + rs.getString(2));
				out.println("gender : " + rs.getString(3));
				out.println("country : " + rs.getString(4));
			}

			else {
				out.println("userid  does not exist in database");
			}

			// Close ResultSet object.

			rs.close();

			// Close PreparedStatment Object.

			pstmt.close();

			// Close the connection Object.

			con.close();

		}

		// Handle Exception if the user with same primary key is already present in
		// database.

		catch (Exception e) {
			out.println("sorry, problem with Database");
		}

		// Write to the responce object Go to index page.
		out.println("<a  href=index.html><button>goto index page</button></a>");

		// Close The printwriter object.
		out.close();
	}
}
