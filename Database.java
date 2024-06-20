package AirlineManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {

	private static final String url = "jdbc:mysql://localhost/airline_management_system";
	private static final String user = "root";
	private static final String passwd = "13579";
	private Connection connection;

	public Database() throws SQLException {
		connection = DriverManager.getConnection(url, user, passwd);

	}

	public void addPassenger(Passenger p) throws SQLException {
		String insert = "Insert into passengers values(?,?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(insert);
		statement.setInt(1, p.getId());
		statement.setString(2, p.getFirstName());
		statement.setString(3, p.getLastName());
		statement.setString(4, p.getMobileNo());
		statement.setString(5, p.getEmail());
		int number = statement.executeUpdate();
		System.out.println("Number of rows affected:" + number);
	}

	public ArrayList<Passenger> getAllPassengers() throws SQLException {
		String get = "Select * from passengers ;";
		Statement statement = connection.createStatement();
		ResultSet rst = statement.executeQuery(get);
		ArrayList<Passenger> passengers = new ArrayList<Passenger>();
		while (rst.next()) {
			Passenger p = new Passenger();
			p.setId(Integer.parseInt(rst.getString("id")));
			p.setFirstName(rst.getString("firstName"));
			p.setLastName(rst.getString("lastName"));
			p.setMobileNo(rst.getString("mobileNo"));
			p.setEmail(rst.getString("email"));
			passengers.add(p);
		}
		return passengers;
	}

	public Passenger getPassengerById(int id) throws SQLException {
		Passenger passenger = null;
		String get = "select * from passengers where id=?;";
		PreparedStatement statement = connection.prepareStatement(get);
		statement.setInt(1, id);
		ResultSet rst = statement.executeQuery();
		if (rst.next()) {
			passenger = new Passenger();
			passenger.setId(rst.getInt("id"));
			passenger.setFirstName(rst.getString("firstName"));
			passenger.setLastName(rst.getString("lastName"));
			passenger.setMobileNo(rst.getString("mobileNo"));
			passenger.setEmail(rst.getString("email"));
		}

		return passenger;

	}

	public void updatePassenger(Passenger passenger) throws SQLException {
		String update = "update passengers SET firstName = ?, lastName = ?, mobileNo = ?, email = ? WHERE id = ?";

		try (PreparedStatement statement = connection.prepareStatement(update)) {
			statement.setString(1, passenger.getFirstName());
			statement.setString(2, passenger.getLastName());
			statement.setString(3, passenger.getMobileNo());
			statement.setString(4, passenger.getEmail());
			statement.setInt(5, passenger.getId());

			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Passenger with ID " + passenger.getId() + " updated successfully.");
			} else {
				System.out.println("No passenger found with ID " + passenger.getId() + ".");
			}
		}
	}
	
	public void deletePassenger(int pid) throws SQLException {
		String delete="delete from passengers where id=?;";
		PreparedStatement statement=connection.prepareStatement(delete);
		statement.setInt(1, pid);
		int result=statement.executeUpdate();
		if (result > 0) {
			System.out.println("Passenger with ID " + pid + " deleted successfully.");
		} else {
			System.out.println("No passenger found with ID " + pid + ".");
		}
	}
//	 public void close() throws SQLException {
//	        if (connection != null && !connection.isClosed()) {
//	            connection.close();
//	        }
//	    }
}
