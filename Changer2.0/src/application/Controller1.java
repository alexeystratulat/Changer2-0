package application;

import java.awt.Checkbox;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class Controller1 {
	static Logger logger = Logger.getLogger(Main.settings.get("settings", "logName"));
	private String nameOfEnv;
	Parser parseForIp;
	@FXML
	private Text top;
	@FXML
	private Label labelIpAdress0;
	@FXML
	private Label labelNameOfServer0;
	@FXML
	private Label labelConnectionStatus0;
	@FXML
	private Label labelPromptStatus0;
	@FXML
	private Label labelRunning0;
	//@FXML
	//private Checkbox checkBoxAll;
	//@FXML
	//private Checkbox checkBox0;
	
	
	
	Connecting checkConnection; 
	
	
	

	public void setNameOfEnv(String nameOfEnv) {
		this.nameOfEnv = nameOfEnv;
		parseForIp = new Parser(nameOfEnv);
		top.setText(nameOfEnv);
		labelIpAdress0.setText((parseForIp.parserIniForIP()).get(0).getIpAdress());		
		checkConnection = new Connecting((parseForIp.parserIniForIP()).get(0).getIpAdress(),Main.settings.get("server", "user"),Main.settings.get("server", "password"));
		

	}

	public Controller1() {
		// TODO Auto-generated constructor stub

		logger.info(nameOfEnv);

	}

	@FXML
	private void onClick() {

		System.out.println((parseForIp.parserIniForIP()).get(0).getIpAdress());
		// System.out.println(parseForIp.parserIniForIP()[2]);
		checkConnection.connect();
	}

}