package application;

import java.util.Arrays;
import java.util.logging.Logger;

import com.sun.javafx.binding.Logging;

import javafx.fxml.FXML;

public class Controller1 {
	static Logger logger = Logger.getLogger(Main.settings.get("settings", "logName"));
	private String nameOfEnv;
	Parser parseForIp;
	
	
	
	
	





	public void setNameOfEnv(String nameOfEnv) {
		this.nameOfEnv = nameOfEnv;
		 parseForIp = new Parser(nameOfEnv);
	}


	public Controller1() {
		// TODO Auto-generated constructor stub

		logger.info(nameOfEnv);
	
	
	
	

}
	
	
	@FXML
	private void onClick() {
		
		
		
		System.out.println(Arrays.toString(parseForIp.parserIniForIP()));
		System.out.println(parseForIp.parserIniForIP()[2]);
		
	
		
	
	}
	
	
	
	
	
	
}