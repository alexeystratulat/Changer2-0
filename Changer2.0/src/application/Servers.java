package application;

import java.awt.Checkbox;

public class Servers {
	
	
	
	
	private String ipAdress;
	private String name;
	private String password;
	//
	private String connectionStatus;
	private String promptStatus;
	private Checkbox check1;
	
	public Servers(String ipAdress, String name, String password) {
		this.ipAdress = ipAdress;
		this.name = name;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Servers [ipAdress= " + ipAdress + ", name= " + name + ", password= " + password + "] ";
	}
	
	

}
