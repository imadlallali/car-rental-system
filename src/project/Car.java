package project;

import java.io.Serializable;

public abstract class Car implements Serializable {
	protected String plateNo;
	protected double pricePerDay;
	protected String model;
	protected String color;
	protected boolean available;
	protected Customer customer;
	
	public Car(String plateNo , double price , String model , String color) {
		this.plateNo = plateNo;
		this.pricePerDay = price;
		this.model = model;
		this.color = color;
		this.available = true;
	}
	
	public void setCustomer(Customer c) {
		customer = c;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public abstract void printBill(int days);
	
	public String toString() {
		String status;
		if (available) status = "Available"; else status = "Unavailable";
		return "Car plate number : "+plateNo+"\nModel : "+model+" \nColor : "+color+" \nPrice Per Day : "+pricePerDay+" $\nStatus : "+status+"\n-----------------------\n";
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public String getModel() {
		return model;
	}
	
}
