package project;
import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.io.IOException;
import java.io.FileNotFoundException;


  
public class CarRentalTest {

	public static void main(String[] args) throws InvalidNameException , InvalidPhoneNumberException, NullPointerException , InputMismatchException{
		
		
		Scanner input = new Scanner(System.in);

		CarRental CR = new CarRental(50);
		
		CR.addCar(new VIP("243" , 12 , "AUDI" , "GREEN" , new Driver("5" , "Hussam") ));
		CR.addCar(new VIP("25" , 15 , "MERCEDES" , "BLACK" , new Driver("15" , "MOhammed") ));
		CR.addCar(new VIP("77" , 15 , "FERRARI" , "WHITE" , new Driver("41" , "Khalid") ));
		CR.addCar(new Economy("572" , 10 , "TOYOTA" , "GREY" ));
		CR.addCar(new Economy("542" , 10 , "BMW" , "RED" ));
		CR.addCar(new Economy("52" , 19 , "PORSCHE" , "RED" ));
		
		System.out.print("\tWelcome to our Car Rental System\n");
		System.out.print("Here are the services we provide:");
		
		int choice = 0;
		while(choice != 8) {
		System.out.println(	"\n> 1. Add a new car to fleet."
				+ "\n> 2. Rent a car."
				+ "\n> 3. Return car."
				+ "\n> 4. Delete car."
				+ "\n> 5. Display cars."
				+ "\n> 6. save data."
				+ "\n> 7. loud data."
				+ "\n> 8. Exit.");
		while (true) {
			try {
		System.out.print("Please choose the service needed : ");
		choice = input.nextInt();
		switch(choice) {
		case 1:
			String name , color , plateNo, model , id , cName, cPhone;
			double pricePerDay;
			int period;
			while(true) {
			try {
			System.out.print("1. Economy\n2. VIP \n> ");
			int carChoice = input.nextInt();
			if(carChoice == 1) {
				System.out.println("Enter the car's plate number: ");
				plateNo = input.next();
				while(true) {
				try {
				System.out.println("Enter the car's price per day: ");
				pricePerDay = input.nextDouble();
				break;
				}catch(InputMismatchException e) {
					System.out.println("The price must be a double number");
					input.nextLine();
				};
				}
				System.out.println("Enter the car's model: ");
				model = input.next();

				System.out.println("Enter the car's color: ");
				color = input.next();
				if (CR.addCar(new Economy(plateNo, pricePerDay, model, color)))
					System.out.println("Car added successfully.");
				else System.out.println("Couldn't add the car.");
			} else 
				if (carChoice == 2){
					System.out.print("Enter the car's plate number: ");
					plateNo = input.next();

					while(true) {
						try {
						System.out.println("Enter the car's price per day: ");
						pricePerDay = input.nextDouble();
						break;
						}catch(InputMismatchException e) {
							System.out.println("The price must be a double number");
							input.nextLine();
						};
					}
					System.out.print("Enter the car's model: ");
					model = input.next();

					System.out.print("Enter the car's color: ");
					color = input.next();

					System.out.print("Enter the driver's national ID: ");
					id = input.next();

					System.out.print("Enter the driver's name: ");
					name = input.nextLine();
					
					if (CR.addCar(new VIP(plateNo, pricePerDay, model, color, new Driver(id, name))))
						System.out.println("Car added successfully.");
					else System.out.println("Couldn't add the car.");
			}	else throw new InvalidChoiceException("Enter either 1 or 2");
			break;
			}catch (InvalidChoiceException e) {
				System.out.println("Enter a valid option");
				input.nextLine();
			}catch (InputMismatchException e2) {
				System.err.println("Input Mismatch : Enter the coherenet type");
				input.nextLine();
			}}
			break;
		case 2:
			int carChoice;
			do {
			System.out.print("1. Economy\n2. VIP \n> ");
			carChoice = input.nextInt();
			}while ( carChoice != 1 && carChoice!=2);
			if (carChoice == 1) {
				System.out.print(CR.displayEconomy());
				System.out.print("Enter the plate number of the car wanted ");
				plateNo = input.next();
				while (CR.searchCar(CR.getCar(plateNo)) == -1){
					System.out.print("This car doesn't exist, verify the entered Plate number.\nEnter again : ");
					plateNo = input.next();
				}
				while(true) {
					try {
						System.out.print("Enter the period by days ");
						period = input.nextInt();
						break;
					}catch(InputMismatchException e) {
						System.out.println("The period must be an integer number");
					}finally {
						input.nextLine();
					}
				}
				System.out.print("Enter your national ID number ");
				id = input.next();
				input.nextLine();
				while(true) {
					try {
						System.out.print("Enter your name ");
						cName = input.nextLine();
						break;
					}catch(InvalidNameException e){
						System.out.println("Name must be more than 3 characters");
						input.nextLine();
					}
				}
				while(true) {
					try {
						System.out.print("Enter your phone number ");
						cPhone = input.nextLine();
						break;
					}catch(InvalidPhoneNumberException e) {
						System.out.println("Phone number must be of length 10");
						input.nextLine();
					}
				}
				CR.rentCar(plateNo , new Customer(id, cName , cPhone) , period);
				break;
				}
			else if (choice == 2){
				System.out.print(CR.displayVIP());
				System.out.print("Enter the plate number of the car wanted ");
				plateNo = input.next();
				while (CR.searchCar(CR.getCar(plateNo)) == -1){
					System.out.print("This car doesn't exist, verify the entered Plate number.\nEnter again : ");
					plateNo = input.next();
				}
				while(true) {
					try {
						System.out.print("Enter the period by days ");
						period = input.nextInt();
						break;
					}catch(InputMismatchException e) {
						System.out.println("The period must be an integer number");
						
					}finally {
						input.nextLine();
					}
				}
				System.out.print("Enter your national ID number ");
				id = input.nextLine();
				while(true) {
					try {
						System.out.print("Enter your name ");
						cName = input.nextLine();
						break;
					}catch(InvalidNameException e){
						System.out.println("Name must be more than 3 characters");
						input.nextLine();
					}
				}
				while(true) {
					try {
						System.out.print("Enter your phone number ");
						cPhone = input.next();
						break;
					}catch(InvalidPhoneNumberException e) {
						System.out.println("Phone number must be of length 10");
						input.nextLine();
					}
				}
				CR.rentCar(plateNo , new Customer(id, cName , cPhone) , period);
			} else {
				System.out.print("Enter a valid choice : ");
			}
			break;
		case 3:
				try {
			System.out.print("Enter the plate number of the car to return ");
			if (CR.returnCar(input.next())) System.out.print("Car returned successfully. Thank you.\n");
			else System.out.print("There is a problem.");
			break;
				}catch (NullPointerException e) {
					System.out.println(e.getMessage());
				}
			break;
		case 4:
			System.out.print("Enter the plate number of the car to delete ");
			if(CR.deleteCar(CR.getCar(input.next())))
				System.out.print("Car deleted successfully. Thank you.\n");
			else
				System.out.print("This car does not exist\n");
			break;
		case 5:
			CR.displayAvailableCars();
			break;
		case 6: 
			System.out.println("Enter file path to sive: ");
			input.nextLine();
			String file = input.nextLine();

		 try {  
			CR.savetofile(file);
			System.out.println("Data sived succcessfully: ");
    
		 }catch(FileNotFoundException e) {  
	           System.out.println("FileNotFoundException ");    
	        }catch(IOException e) {  
		           System.out.println("IOException "); 
	        } 
			break;

		case 7: 
			System.out.println("Enter file path to loud: ");
			input.nextLine();
			String file1 = input.nextLine();

		 try { 
			 CR = CarRental.LoadFromfile(file1);
				System.out.println("Data loaded succcessfully: ");
		 }catch(FileNotFoundException e) {  
	           System.out.println("FileNotFoundException "); 
		        }catch(IOException e) {  
			           System.out.println("IOException "); 
		        }catch(ClassNotFoundException e) {  
			           System.out.println("ClassNotFoundException "); 
		        }   
			break;

		case 8: System.out.print("Thank you! we are waiting for you again.");
			return;
		default : System.out.print("> Please enter a number from the list");
		}
		break;
		}catch(InputMismatchException e) {
			System.out.println("Please enter an integer number ");
			input.nextLine();
		}
		} 
	}
		input.close();
		


}
}
