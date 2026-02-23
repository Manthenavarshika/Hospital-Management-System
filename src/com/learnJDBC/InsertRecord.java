package com.learnJDBC;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;


public class InsertRecord {

	public static void main(String[] args) {
		Connection con=null;
		Statement stmt=null;
		//ResultSet rs=null;
		String URL="jdbc:mysql://localhost:3306/JDBCMySQL";
		String Username="root";
		String Password="root";
		try {
			Class.forName("com.mysql.jdbc.driver");
		}catch(ClassNotFoundException e) {
			System.out.println("In catch "+e.getMessage());
		}
		try {
			con=DriverManager.getConnection(URL,Username,Password);
			stmt=con.createStatement();
			stmt.execute("insert into Students values (3,'Sangeetha','IT')");
			stmt.execute("insert into students values (4,'Saina Nehwal','ECE')");
			stmt.execute("insert into students values (5,'Sania Mirza','EEE')");
			stmt.execute("insert into students values (6,'Sindhu','Mech')");
			System.out.println("Successfully inserted");
			stmt.close();
			con.close();
		}catch(SQLException e) {
			System.out.println("In catch"+e.getMessage());
		}
		 finally
			{
				if(con!=null)
				{
				try {
					//rs.close();
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
