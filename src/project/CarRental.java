package project;
import java.util.Random;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.*;  
import java.io.Serializable;  
import java.io.IOException;
import java.io.FileInputStream;

public class CarRental implements Serializable{
	private Car carList[];
	private int numOfCars;
	
	public CarRental(int size) {
		carList = new Car[size];
		numOfCars = 0;
	}
	
	public boolean exists(Car c) throws NullPointerException{
		if (c == null) throw new NullPointerException("Car doesn't exist");
		for (int i=0 ; i<numOfCars ; i++) {
			if ( c.getPlateNo().equalsIgnoreCase(carList[i].getPlateNo()) && c.getModel().equalsIgnoreCase(carList[i].getModel()) ) return true;
		}
		return false;
	}
	
	public Car getCar(String plateNo) {
		for (int i=0 ; i<numOfCars ; i++) {
			if ( carList[i].getPlateNo().equalsIgnoreCase(plateNo))return carList[i];
		}
		return null;
	}
	
	public boolean addCar(Car c) throws NullPointerException{
		if ( (numOfCars >= carList.length) || ( exists(c) ) )
			return false;
		carList[numOfCars++] = c;
		return true;
	}
	
	public int searchCar(Car c) {
		for (int i = 0 ; i< numOfCars ; i++) {
			if ( carList[i] == c ) { 
				return i;
			}
		}
		return -1;
	}
	
	public boolean deleteCar(Car c) {
		if(searchCar(c) != -1) {
			carList[searchCar(c)] = carList[numOfCars-1];
			carList[numOfCars-1] = null;
			
			numOfCars--;
			return true;
		}
		return false;
	}
	
	public void rentCar(String plateNo , Customer c , int numOfDays) {
		if (getCar(plateNo) != null)
			if (getCar(plateNo).isAvailable()) 
			{
				getCar(plateNo).available = false;
				getCar(plateNo).setCustomer(c);
				System.out.print(getCar(plateNo).getModel()+" has successfully been rented to "+getCar(plateNo).getCustomer().getName());
				getCar(plateNo).printBill(numOfDays);
				 }
			 else System.out.print("Sorry ! This car is currently unavailable.\n");
		else System.out.print("There is no car corresponding to the plate number provided.\n");
		}
			 
	
	
	public boolean returnCar(String plateNo){
		if (exists(getCar(plateNo))) {
			if (!getCar(plateNo).isAvailable()) {
		getCar(plateNo).setAvailable(true);
		getCar(plateNo).setCustomer(null);
		return true;}}
		return false;
	}
	
	
	public VIP[] searchAvailableVIP() {
		int count = 0;
		for (int i = 0 ; i < numOfCars ; i++) {
			if (carList[i] instanceof VIP) {
				count++;
			}}
		int j =0;
		VIP AvailableVIP[] = new VIP[count];
		for (int i = 0 ; i < numOfCars ; i++) {
			if (carList[i] instanceof VIP) {
				AvailableVIP[j++] = (VIP)carList[i];
			}
		}
		return AvailableVIP;
	}
	
	public Economy[] searchAvailableEconomy() {
		int count = 0;
		for (int i = 0 ; i < numOfCars ; i++) {
			if (carList[i] instanceof Economy) {
				count++;
			}}
		int j=0;
		Economy AvailableEconomy[] = new Economy[count];
		for (int i = 0 ; i < numOfCars ; i++) {
			if (carList[i] instanceof Economy) {
				AvailableEconomy[j++] = (Economy)carList[i];
			}
		}
		return AvailableEconomy;
	}
		
	
	public String displayEconomy() {
		String econ = "";
		for( int i = 0 ; i<searchAvailableEconomy().length ; i++ ) {
			econ += searchAvailableEconomy()[i]+"\n";
		}
		return econ;
	}
	
	public String displayVIP() {
		String vip = "";
		for( int i = 0 ; i<searchAvailableVIP().length ; i++ ) {
			vip += searchAvailableVIP()[i]+"\n";
		}
		return vip;
	}
	
	public void displayAvailableCars() {
		System.out.println("ECONOMY CARS ("+searchAvailableEconomy().length+")\n----------");
		System.out.println(displayEconomy());
		System.out.println("VIP CARS("+searchAvailableVIP().length+")\n----------");
		System.out.println(displayVIP());
	}
	public String toString() {
		String cars = "";
		for (int i = 0 ; i < numOfCars ; i++) {
			cars= cars +" "+carList[i];
		}
		return cars;
	}
	public void savetofile(String file) throws FileNotFoundException, IOException{  
	    File f=new File(file);;  
	    FileOutputStream s1=new FileOutputStream(f);  
	    ObjectOutputStream s2= new ObjectOutputStream(s1);  
	    
	    s2.writeObject(this);  
	    s2.close();
	    s1.close();
	      
	     
	} 
	public static CarRental LoadFromfile(String file) throws FileNotFoundException, IOException, ClassNotFoundException{  
	    File f=new File(file);  
	    FileInputStream s3= new FileInputStream(f);  
	    ObjectInputStream s4=new ObjectInputStream(s3); 
	    CarRental temp = (CarRental) s4.readObject();
	    s4.close();
	    s3.close();
	    return temp;
}}
