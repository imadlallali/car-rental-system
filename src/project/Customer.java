package project;

public class Customer {
	private String id;
	private String name;
	private String phone;
	
	public Customer(String id , String name , String phone) throws InvalidNameException{
		if (name.length() <= 3) throw new InvalidNameException("Name length must be Stringer than 3");
		if (phone.length() != 10) throw new InvalidPhoneNumberException("Phone number length must be 10");
		this.id = id;
		this.name = name;
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String toString() {
		return "Customer: ID = "+id+" , name : "+name+" , phone number : "+phone;
	}
}
