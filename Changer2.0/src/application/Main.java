package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		Pane root =(Pane) FXMLLoader.load(getClass().getResource("frame.fxml"));
		Scene scene = new Scene(root,primaryStage.getWidth(), primaryStage.getHeight());
		
		
		
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("First Window");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
