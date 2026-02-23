package hms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class HospitalManagementSystem {

	
	private static final String url="jdbc:mysql://localhost:3306/hospital";
	private static final String username="root";
	private static final String password="root";
	public static void main(String[] args) {
       try {
    	   Class.forName("com.mysql.cj.jdbc.Driver");
       }catch(ClassNotFoundException e) {
    	   e.printStackTrace();
       }
       Scanner scanner=new Scanner(System.in);
       try {
    	   //object reference
    	   Connection connection=DriverManager.getConnection(url,username,password);
    	   Patient patient=new Patient(connection,scanner);//object reference of patient
    	   Doctor doctor=new Doctor(connection);//object references of doctor
    	   while(true) {
    		   System.out.println("Hospital Management System");
    		   System.out.println("1.Add Patient");
    		   System.out.println("2.View Patient");
    		   System.out.println("3.View Doctor");
    		   System.out.println("4.Book Appointment");
    		   System.out.println("5.Exit");
    		   System.out.println("Please Enter Your Choice");
    		   int choice=scanner.nextInt();
    		   switch(choice) {
    		   case 1:
    			   //Add Patient
    			   patient.addPatient();
    			   System.out.println();
    			   break;
    		   case 2:
    			   //view Patient
    			   patient.viewPatients();
    			   System.out.println();
    			   break;
    		   case 3:
    			   //view Doctor
    			   doctor.viewDoctors();
    			   System.out.println();
    			   break;
    		   case 4:
    			   //Book Appointment
    			   bookAppointment(patient,doctor,connection,scanner);
    			   System.out.println();
    			   break;
    		   case 5:
    			   System.out.println("THANKYOU FOR USING HOSPITAL MANAGEMENT SYSTEM");
    			   return;
    			 default:
    				 System.out.println("Please Enter Valid Input");
    			     break;
    		   }
    	   }
       }catch(SQLException e) {
    	   e.printStackTrace();
       }
	}
       public static void bookAppointment(Patient patient,Doctor doctor,Connection connection,Scanner scanner) {
    	   System.out.println("Please Enter Patient ID:");//input from the user id to book appointment
    	   int patientID=scanner.nextInt();
    	   System.out.println("Please Enter Doctor id");
    	   int doctorID=scanner.nextInt();
    	   System.out.println("Please Enter Appointment Date(YYYY-MM-DD): ");
    	   String appointmentDate=scanner.next();
    	   if(patient.getPatientByID(patientID) &&doctor.getDoctorByID(doctorID)) {//if we have both patientID and doctorId enter into loop
    		   if(checkDoctorAvailability(doctorID,appointmentDate,connection)) {//check whether doctor is available or not
    			   String appointmentQuery="INSERT INTO appointments(patients_id,doctor_id,appointment_date) values(?,?,?)";
    			   try {
    				   PreparedStatement preparedStatement=connection.prepareStatement(appointmentQuery);//inserting into appointment table
    				   preparedStatement.setInt(1,patientID);
    				   preparedStatement.setInt(2,doctorID);
    				   preparedStatement.setString(3,appointmentDate);
    				   int rowsAffected=preparedStatement.executeUpdate();
    				   if(rowsAffected>0) {
    					   System.out.println("Appointment Booked");//if available successfully appointment is booked
    				   }else {
    					   System.out.println("Appointment could not be booked");
    				   }
    			   }catch(SQLException e) {
    				   e.printStackTrace();
    			   }
    		   }else {
    			   System.out.println("Doctor not availables on this date!");
    		   }
    	   }else {
    		   System.out.println("Either doctor or patient doesn't exist");
    	   }
       }
       public static boolean checkDoctorAvailability(int doctorID,String appointmentDate,Connection connection) {
    	   //count(*): getting the rows which matches the particular criteria
    	   String query="SELECT COUNT(*) FROM appointments WHERE doctor_id=? AND appointment_date=?";
    	   try {
    		   PreparedStatement preparedStatement=connection.prepareStatement(query);
    		   preparedStatement.setInt(1, doctorID);
    		   preparedStatement.setString(2, appointmentDate);
    		   ResultSet  resultset=preparedStatement.executeQuery();
    		   if(resultset.next()) {
    			   int count=resultset.getInt(1);
    			   if(count==0) {
    				   return true;
    			   }else {
    				   return false;
    			   }
    		   }
    	   }catch(SQLException e) {
    			   e.printStackTrace();
    		   }
    		   return false;
    	   
       }
	}


