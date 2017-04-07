package application;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;



//


public class Main extends Application {
	
	private static String logPath =  "D:/MyLogFile.log";
	private static String logName =  "MyLog";

	static Logger logger = Logger.getLogger("logName");
	static FileHandler fh;

	public static void logging() throws SecurityException, IOException {

		fh = new FileHandler(logPath);
		logger.addHandler(fh);
		SimpleFormatter formatter = new SimpleFormatter();
		fh.setFormatter(formatter);

	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		Pane root = (Pane) FXMLLoader.load(getClass().getResource("frame.fxml"));
		Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());

		primaryStage.setScene(scene);
		primaryStage.setTitle("First Window");
		primaryStage.show();
	}

	public static void main(String[] args) throws SecurityException, IOException {
		logging();
		logger.info("App is started");

		launch(args);

	}
}
