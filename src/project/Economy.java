package project;

public class Economy extends Car{
	
	public Economy(String plateNo , double price , String model , String color) {
		super(plateNo ,price ,model ,color);
	}
	
	public void printBill( int days ) {
		System.out.println("\nTotal price : "+ days*pricePerDay+" $");
	}
}
