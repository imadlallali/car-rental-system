package project;

import java.io.Serializable;

public class Driver implements Serializable {
	private String id;
	
	private String name;
	
	public Driver(String id , String name ) {
		this.id = id;
		this.name = name;
	}
	
	public String toString() {
		return"Driver  ID : "+id+"\n\tName : "+name;
	}
	public String getName() {
		return name;
	}

}
