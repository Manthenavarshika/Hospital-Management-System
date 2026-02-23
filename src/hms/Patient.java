package hms;

import java.sql.*;
import java.util.Scanner;

public class Patient {

	
		private Connection connection;
		private Scanner scanner;
		public Patient(Connection connection,Scanner scanner) {
			this.connection=connection;
			this.scanner=scanner;
		}
		//Method to add patients
		public void addPatient()
		{
			//1.collecting information from user
			System.out.println("Enter Patient's Name");
			String name=scanner.next();
			System.out.println("Enter Patient's Age");
			int age=scanner.nextInt();
		    System.out.println("Enter Patients's Gender");
		    String gender=scanner.next();
		    try {
		    	//2.sending information to the database in the form of insert
		    	String Query="Insert into patients(name,age,gender) values (?,?,?)";
		    	PreparedStatement preparedStatement=connection.prepareStatement(Query);
		    	preparedStatement.setString(1,name);//for inserting values to database
		    	preparedStatement.setInt(2,age);
		    	preparedStatement.setString(3,gender);
		    	//executeUpdate method gives integer if row is successfully inserted......
		    	int affectedRows=preparedStatement.executeUpdate();
		    	//3.checking whether successfully inserted or not
		    	if(affectedRows>0) {
		    		System.out.println("Patient Added Successfully");
		    	}
		    	else {
		    		System.out.print("Failed to add Patient");
		    	}
		    }catch(SQLException e) {
		    	e.printStackTrace();
		    }
		}
		//Method to viewPatients
		public void viewPatients() {
			//1.seeing what is in the database
			String query="select * from patients";
			try {
				//2.storing in resultset
				//3.using prepared statement and result set we are getting data from database
				PreparedStatement preparedStatement=connection.prepareStatement(query);
				ResultSet resultset=preparedStatement.executeQuery();//whenever we want to retrieve from database we use ResultSet
				System.out.println("Patients: ");
				System.out.println("+-------------+--------------+------+--------+");
				System.out.println("| Patient ID  | Name         | Age  | Gender |");
				System.out.println("+-------------+--------------+------+--------+");
				while(resultset.next()) {//loop runs until resultset has rows
					//4.printing on console
					int id=resultset.getInt("id");
					String name=resultset.getString("name");
					int age=resultset.getInt("age");
					String gender=resultset.getString("gender");
					
					System.out.printf("|%-13s|%-14s|%-6s|%-8s|\n",id,name,age,gender);//spacing trying to get look and feel
					System.out.println("+-------------+--------------+------+--------+");
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		public boolean getPatientByID(int id) {
			String query="select * from patients where id=?";
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(query);
				preparedStatement.setInt(1,id);
				ResultSet resultset=preparedStatement.executeQuery();
				if(resultset.next()) {//resultset will check whether  next person is available
					return true;
				}else {
					return false;
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return false;
		}

	}

