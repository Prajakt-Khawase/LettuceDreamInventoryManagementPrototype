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
 *  File Description: Servlet to Delete User from Database
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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Fetching the parameter with id = uid and converting the String value into
		// int.
		
		int userid = Integer.parseInt(request.getParameter("uid"));

		// Setting the mime type so that browser should know which kind of responce will
		// it receive.
		response.setContentType("text/html");

		// Creating the Printwriter object by calling the getWriter(); factory method.
		// out object can be used to write streams to responce object.
		PrintWriter out = response.getWriter();
		try {
			// Fetching the connection which is established in DBConnection class.
			Connection con = DBConnection.getConnection();

			// Sending Query to Database and stored compile Query stored in pstmt object.
			PreparedStatement pstmt = con.prepareStatement("delete  from  userinfo   where  userid=?");

			// Bind values to corrosponding placeholders(?)
			pstmt.setInt(1, userid); // set userid.

			// Use executeUpdate() method To Execute Non-select SQL command. Return type of
			// executeUpdate is int.
			int i = pstmt.executeUpdate();

			// Print how many rows updated / deleted
			out.println(i + " row  deleted.");

			// Close the preparedStatment object to avoid memory lickage.
			pstmt.close();

			// Close connection object so that database should not get overloaded with
			// multiple opened connections
			con.close();
		} catch (Exception e) {
			out.println("sorry, problem in deleting user");
		}

		// Write to the responce object Go to index page.
		out.println("<a  href=index.html><button>goto index page</button></a>");

		// Close The printwriter object.
		out.close();
	}

}
