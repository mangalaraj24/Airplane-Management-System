package AirlineManagementSystem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class PassengersController {

	private Database database;
	private Passenger passenger;

	public PassengersController(Database database) {
		this.database = database;
	}

	public void addNewPassenger() throws SQLException {
		Scanner s = new Scanner(System.in);
		try {
			System.out.println("Enter the Id : ");
			int id = s.nextInt();
			System.out.println("Enter First Name : ");
			String firstName = s.next();
			System.out.println("Enter Last Name : ");
			String lastName = s.next();
			System.out.println("Enter Mobile Number : ");
			String mobileNo = s.next();
			System.out.println("Enter email id : ");
			String email = s.next();

			if (id <= 0) {
				throw new IllegalArgumentException("Id should not be negative");
			}
			if (firstName.isEmpty() || lastName.isEmpty() || mobileNo.isEmpty() || email.isEmpty()) {
				throw new IllegalArgumentException("Enter all fields");
			}
			if (!email.contains("@")) {
				throw new IllegalArgumentException("Enter a valid mail id");
			}

			Passenger passenger = new Passenger();
			passenger.setId(id);
			passenger.setFirstName(firstName);
			passenger.setLastName(lastName);
			passenger.setMobileNo(mobileNo);
			passenger.setEmail(email);

			database.addPassenger(passenger);
			System.out.println("Passenger added successfully!");
		} catch (SQLException e) {
			System.err.println("Database error: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.err.println("Input error: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("An unexpected error occurred: " + e.getMessage());
		} finally {
			s.close();
		}

	}

	public void editPassenger(Passenger passenger) throws SQLException {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter new First Name: ");
		String firstName = s.nextLine();
		passenger.setFirstName(firstName);

		System.out.println("Enter new Last Name: ");
		String lastName = s.nextLine();
		passenger.setLastName(lastName);

		System.out.println("Enter new Mobile Number: ");
		String mobileNo = s.nextLine();
		passenger.setMobileNo(mobileNo);

		System.out.println("Enter new Email: ");
		String email = s.nextLine();
		passenger.setEmail(email);

		database.updatePassenger(passenger);

	}

	public void deletePassengerById(int pid) throws SQLException {
		Scanner s=new Scanner(System.in);
		Passenger passenger = database.getPassengerById(pid);
		System.out.println("Below are the details of a passenger you wish to delete : " + passenger);
		System.out.println("Are you sure want to delete? (Yes/No)");
		String choice=s.next();
		if(choice.equalsIgnoreCase("Yes")) {
		database.deletePassenger(pid);
		}
		else {
			System.out.println("Deletion Cancelled");
		}
	}

}

