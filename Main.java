ckage AirlineManagementSystem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException {

		Database database = new Database();
		PassengersController passengersController = new PassengersController(database);
		Scanner s = new Scanner(System.in);

		int choice = 0;
		do {
			System.out.println(" Welcome to Airline Management System");
			System.out.println("Press 1 -> Add new passenger");
			System.out.println("Press 2 -> Edit Passenger");
			System.out.println("Press 3 -> List of Passengers");
			System.out.println("Press 4 -> Delete Passengers");
			System.out.println("Press 5 -> Quit the Airline System.");
			System.out.println("Enter your choice...");

			try {
				choice = s.nextInt();
				switch (choice) {
				case 1:
					passengersController.addNewPassenger();
					break;

				case 2:

					System.out.println("Enter the passenger id : ");

					int id = s.nextInt();

					System.out.println("Current Details");
					Passenger passenger = database.getPassengerById(id);
					passengersController.editPassenger(passenger);
					break;
					
				case 3:
					
					System.out.println("Passengers List are,");
					ArrayList<Passenger> passengerList=new ArrayList<Passenger>();
					passengerList.addAll(database.getAllPassengers());
					for(Passenger p: passengerList) {
						System.out.println(p.toString());
					}
					break;
					
				case 4:
					
					System.out.println("Enter the Passenger Id : ");
					int pid=s.nextInt();
					passengersController.deletePassengerById(pid);
					break;
					
				case 5:

					System.out.println("4 - Quit");
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

		} while (choice != 6);

		s.close();

	}

}
