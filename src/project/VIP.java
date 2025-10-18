package project;

public class VIP extends Car{
	private Driver driver;
	
	public VIP(String plateNo , double price , String model , String color , Driver driver) {
		super( plateNo , price , model ,  color);
		this.driver = driver;
	}
	
	public void printBill( int days ) {
		System.out.println( "\nCar price : "+ days*pricePerDay+"\nDriver fees : "+days*pricePerDay*0.4+"\nTotal price : "+pricePerDay*days*1.4+"$");
	}
	
	public String toString() {
		String status;
		if (available) status = "Available"; else status = "Unavailable";
		return "Car plate number : "+plateNo+"\nModel : "+model+" \nColor : "+color+" \nDriver : "+driver.getName()+" \nPrice Per Day : "+pricePerDay+" $\nStatus : "+status+"\n-----------------------\n";
}
}
