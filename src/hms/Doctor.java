package hms;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {

	
	private Connection connection;
	public Doctor(Connection connection) {
		this.connection=connection;
	}
	public void viewDoctors() {
		String query="select * from doctors";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			ResultSet resultset=preparedStatement.executeQuery();
			System.out.println("Doctors ");
			System.out.println("+-----------+-----------+---------------+");
			System.out.println("| Doctor ID | name      | Specilization |");
			System.out.println("+-----------+-----------+---------------+");
			while(resultset.next()) {
				int id=resultset.getInt("id");
				String name=resultset.getString("name");
				String Specilization=resultset.getString("Specilization");
				
				System.out.printf("|%-11s|%-11s|%-15s|\n",id,name,Specilization);
				System.out.println("+-----------+-----------+---------------+");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean getDoctorByID(int id) {
		String query="select * from doctors where id=?";
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


