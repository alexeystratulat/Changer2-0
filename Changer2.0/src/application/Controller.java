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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Controller {

	WorkWithFile forFile;
	static Logger logger = Logger.getLogger(Main.settings.get("settings", "logName"));

	private String pathForIni = Main.settings.get("settings", "iniForEnv");

	ObservableList<String> listOfEnvForChoiceBox = FXCollections.observableArrayList(forEnvParserIni());

	public Controller() {

		// TODO Auto-generated constructor stub
	}

	@FXML
	public void nextButtonClicked() {
		Parser parseForIp = new Parser(listForChoiceBox.getValue());
		logger.info("\n=======\nNEXT> button is typed \n" + "ENV: " + listForChoiceBox.getValue() + "\nsize of choised env:  "
				+ Integer.toString(parseForIp.parserIniForIP().size())+"\n=======");

		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			if (parseForIp.parserIniForIP().size() == 1) {
				fxmlLoader.setLocation(getClass().getResource("frame1.fxml"));
			} else {
				System.out.println("start frame for a few");
				fxmlLoader.setLocation(getClass().getResource("frameTest.fxml"));
			}
			fxmlLoader.load();

			Controller1 d1 = fxmlLoader.getController();
			d1.setNameOfEnv(listForChoiceBox.getValue());
			Parent p = fxmlLoader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(p));
			stage.setResizable(false);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
		//
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
		logger.info("\n\n===================    Program is started    =====================\n\n ");
		listForChoiceBox.setItems(listOfEnvForChoiceBox);

		forFile = new WorkWithFile();
		forFile.deletingOfFolders();

	}

	public String[] forEnvParserIni() {

		Parser listOfEnv = new Parser();

		return listOfEnv.parserForEnvIni();

	}

}
