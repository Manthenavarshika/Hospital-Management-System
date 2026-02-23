package com.learnJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class codeOpt {

	public static void main(String[] args) {
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
	String URL="jdbc:mysql://localhost:3306/JDBCMySQL";
	String Username="root";
	String Password="root";
	String query="select * from students";
	try {
		Class.forName("com.mysql.jdbc.driver");
		
	}catch(ClassNotFoundException e) {
		System.out.println("In catch "+e.getMessage());
	}
	try {
		con=DriverManager.getConnection(URL,Username,Password);
		stmt=con.createStatement();
		 rs=stmt.executeQuery(query);
		
		 while(rs.next()) {
		    	System.out.println("_______________________");
		    	int id=rs.getInt("id");
		    	String name=rs.getString("name");
		    	String dept=rs.getString("dept");
		    	System.out.println("Student ID :"+id);
		    	System.out.println("Student name:"+name);
		    	System.out.println("Student Department:"+dept);
		    	System.out.println("_______________________");
		    	
		    }
		    rs.close();
		    stmt.close();
		    con.close();
	}catch(SQLException e) {
		System.out.println("In catch"+e.getMessage());
	}
	finally
	{
		if(con!=null) {
		try {
			rs.close();
			stmt.close();
			con.close();
			System.out.println("Terminated Succesfully");
		}
		catch(Exception e) {
		System.out.println("Oops some serious issues");
	}
		
	}
	}

}
}
