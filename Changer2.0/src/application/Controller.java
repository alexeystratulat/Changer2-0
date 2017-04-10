package application;

import java.io.IOException;
import java.util.Arrays;
import java.util.Observable;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.ini4j.Ini;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

public class Controller {
	static Logger logger = Logger.getLogger(Main.settings.get("settings", "logName"));

		private String pathForIni = Main.settings.get("settings", "iniForEnv");

	
	ObservableList<String> listOfEnvForChoiceBox = FXCollections.observableArrayList(forEnvParserIni());

	public Controller() {

		// TODO Auto-generated constructor stub
	}

	@FXML
	public void nextButtonClicked() {
		logger.info("NEXT> button is typed");

	}

	@FXML
	public void onMouseClickedCancelBtn(InputEvent e) {
		final Node source = (Node) e.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();

	}

	@FXML
	private ChoiceBox<String> listForChoiceBox;

	@FXML
	private void initialize() {
		listForChoiceBox.setItems(listOfEnvForChoiceBox);

	}

	
	
	public String[] forEnvParserIni() {

		Parser listOfEnv = new Parser(pathForIni);

		return listOfEnv.parserForEnvIni();

	}

}
