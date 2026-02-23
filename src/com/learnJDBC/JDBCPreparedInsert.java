package com.learnJDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JDBCPreparedInsert {

	public static void main(String[] args) {
		Connection con=null;
		//ResultSet rs=null;
		String URL="jdbc:mysql://localhost:3306/JDBCMySQL";
		String Username="root";
		String Password="root";
		String Query="insert into students(Id,name,dept) values(?,?,?)";
 try {
	 Class.forName("com.mysql.cj.jdbc.Driver");
	 System.out.println("Driver loaded");
 }catch(ClassNotFoundException e) {
	 System.out.println("Drivers not loaded");
	 System.out.println("In catch "+e.getMessage());
 }
 try {
	 con=DriverManager.getConnection(URL,Username,Password);
	 PreparedStatement preparedStatement=con.prepareStatement(Query);
	 preparedStatement.setInt(1, 9);
	 preparedStatement.setString(2, "Abhinav Bindra");
	 preparedStatement.setString(3,"Biotechnology");
	 preparedStatement.execute();
	 con.close();
	 System.out.println("Inserted Successfully");
 }catch(SQLException e) {
	 System.out.println("In catch "+e.getMessage());
 }
	 finally
		{
			if(con!=null)
			{
			try {
				 //rs.close();
				//stmt.close();
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
