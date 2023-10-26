//created By Noopur

package com.ecommerce.conenction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConnectionDatabase {

	Connection con;
	public Connection getConnection() throws ClassNotFoundException, SQLException 
	{	
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/ecommerceminiproject";
	String username="root";
	String password="Arti@1404";
	con=DriverManager.getConnection(url,username,password);
//		
//
	return con;
	}
}
