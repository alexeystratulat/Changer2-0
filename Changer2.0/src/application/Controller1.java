package application;

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
	private Label label1;
	

	public void setNameOfEnv(String nameOfEnv) {
		this.nameOfEnv = nameOfEnv;
		parseForIp = new Parser(nameOfEnv);
		top.setText(nameOfEnv);
		label1.setText((parseForIp.parserIniForIP()).get(0).getIpAdress());

	}

	public Controller1() {
		// TODO Auto-generated constructor stub

		logger.info(nameOfEnv);

	}

	@FXML
	private void onClick() {

		System.out.println((parseForIp.parserIniForIP()).get(0).getIpAdress());
		// System.out.println(parseForIp.parserIniForIP()[2]);

	}

}