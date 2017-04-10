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
	static Logger logger = Logger.getLogger(Main.settings.get("settings", "logName"));

	private String pathForIni = Main.settings.get("settings", "iniForEnv");

	ObservableList<String> listOfEnvForChoiceBox = FXCollections.observableArrayList(forEnvParserIni());

	private String choisenEnvName = "default";

	public String getChoisenEnvName() {

		return choisenEnvName;
	}

	public Controller() {

		// TODO Auto-generated constructor stub
	}

	@FXML
	public void nextButtonClicked() {

		choisenEnvName = listForChoiceBox.getValue();
		// System.out.println(listForChoiceBox.getValue());
		logger.info("NEXT> button is typed    " + choisenEnvName);

		//Parent root;
		try {
			
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("frame1.fxml"));
			fxmlLoader.load();
			
			Controller1 d1 = fxmlLoader.getController();
			d1.setNameOfEnv(listForChoiceBox.getValue());
			Parent p = fxmlLoader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene (p));
			//Scene scene = new Scene(fxmlLoader.load());
			//stage.setTitle("New Window2");
			 
			//stage.setScene(scene);
			stage.show();
			// Hide this current window (if this is what you want)

		} catch (IOException e) {
			e.printStackTrace();
		}

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

	public String choise() {

		return listForChoiceBox.getValue();
	}

}
