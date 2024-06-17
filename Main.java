package AirlineManagementSystem;

import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException {

		Database database = new Database();
		PassengersController passengersController = new PassengersController(database);
		Scanner s = new Scanner(System.in);

		int choice = 0;
		do {
			System.out.println("Welcome to Airline Management System");
			System.out.println("1 -> Add new passenger");
			System.out.println("2 -> Edit Passenger");
			System.out.println("3 -> Quit the Airline System.");
			System.out.println("Enter your choice...");

			try {
				choice = s.nextInt();
				switch (choice) {
				case 1:
					PassengersController.addNewPassenger(database);
					break;

				case 2:

					System.out.println("Enter the passenger id : ");

					int id = s.nextInt();

					System.out.println("Current Details");
					Passenger passenger = database.getPassengerById(id);
					PassengersController.editPassenger(passenger);
					break;
				case 3:

					System.out.println("3 - Quit");
					break;

				default:
					System.out.println("Invalid choice. Please enter a valid option.");
				}
			} catch (NoSuchElementException e) {
				System.err.println("Error reading input: " + e.getMessage());
				s.nextLine(); 
			} catch (SQLException e) {
				System.err.println("Database error: " + e.getMessage());
			} catch (Exception e) {
				System.err.println("An unexpected error occurred: " + e.getMessage());
			}

		} while (choice != 3);

		s.close();

	}

}
