package com.learnJDBC;
import java.sql.Connection;
//import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCPreparedStatement {

	public static void main(String[] args) {
		Connection con=null;
		//Statement stmt=null;
		ResultSet rs=null;
		String URL="jdbc:mysql://localhost:3306/JDBCMySQL";
		String Username="root";
		String Password="root";
		String Query="select * from students where name = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded!");
		}catch(ClassNotFoundException e) {
			System.out.println("Driver Loaded failed");
			System.out.println("In catch "+e.getMessage());
		}
		try {
			con=DriverManager.getConnection(URL,Username,Password);
			//Statement stmt=con.createStatement();
			PreparedStatement preparedStatement=con.prepareStatement(Query);
			preparedStatement.setString(1,"Maan");
			rs=preparedStatement.executeQuery();
			while(rs.next()) {
				System.out.println("_________________________");
				int id=rs.getInt("id");
				String name=rs.getString("name");
				String dept=rs.getString("dept");
				System.out.println("Student ID :"+id);
				System.out.println("Student Name :"+name);
				System.out.println("Student Department :"+dept);
				System.out.println("____________________________");
			}
		}catch(SQLException e) {
		System.out.println("In catch "+e.getMessage());	
		}
		finally
		{
			if(con!=null)
			{
			try {
				 rs.close();
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
