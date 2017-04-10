package application;

import java.util.logging.Logger;

import com.sun.javafx.binding.Logging;

import javafx.fxml.FXML;

public class Controller1 {
	static Logger logger = Logger.getLogger(Main.settings.get("settings", "logName"));
	private String nameOfEnv;
	
	
	
	
	
	





	public void setNameOfEnv(String nameOfEnv) {
		this.nameOfEnv = nameOfEnv;
	}


	public Controller1() {
		// TODO Auto-generated constructor stub

		logger.info(nameOfEnv);
	
	
	
	

}
	
	
	@FXML
	private void onClick() {
		System.out.println(nameOfEnv);
		
	
		
	
	}
	
	
	
	
	
	
}