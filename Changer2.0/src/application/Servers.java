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
	
	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConnectionStatus() {
		return connectionStatus;
	}

	public void setConnectionStatus(String connectionStatus) {
		this.connectionStatus = connectionStatus;
	}

	public String getPromptStatus() {
		return promptStatus;
	}

	public void setPromptStatus(String promptStatus) {
		this.promptStatus = promptStatus;
	}

	public Checkbox getCheck1() {
		return check1;
	}

	public void setCheck1(Checkbox check1) {
		this.check1 = check1;
	}

	
	
	public Servers(String ipAdress, String name, String password) {
		this.ipAdress = ipAdress;
		this.name = name;
		this.password = password;
	}

	@Override
	public String toString() {
		return"\n"+"Servers [ipAdress= " + ipAdress + ", name= " + name + ", password= " + password + "] ";
	}
	
	

}
