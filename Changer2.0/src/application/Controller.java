package application;

import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

public class Controller {

	private String pathForXml = "D:/list.xml";
	ObservableList<String> listOfEnvForChoiceBox;
	
	
	
	public Controller() {
		System.out.println("constr");
		System.out.println(forEnvParser());
	 listOfEnvForChoiceBox = FXCollections.observableArrayList(forEnvParser());
		
		// TODO Auto-generated constructor stub
	}

	@FXML
	public void nextButtonClicked() {
		System.out.println("NEXT");
		
		
		
		
		
		
		
		
		

	}
	
	
	

	@FXML
	public void onMouseClickedCancelBtn(InputEvent e) {
		final Node source = (Node) e.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();

	}
	
	
	
	@FXML
	private ChoiceBox listForChoiceBox;
	
	
	@FXML
	private void initialize(){
		listForChoiceBox.setItems(listOfEnvForChoiceBox);
		//listOfEnvForChoiceBox.setValue("value");
		
		
		
		
	}
	
	
	public String[] forEnvParser(){
		
		ParseForEnv listOfEnv = new ParseForEnv(pathForXml);
		
		
		
		
		return listOfEnv.parserForEnv();
		
		
	}
	
	
	
	
	
}
