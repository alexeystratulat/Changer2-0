package application;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.ini4j.Ini;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

//

public class Main extends Application {

	public static Ini getSettings() {
		return settings;
	}

	static Parser pars;
	static Ini settings;

	static Logger logger;
	static FileHandler fh;

	public static void logging() throws SecurityException, IOException {

		fh = new FileHandler(settings.get("settings", "logPath"));
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
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) throws SecurityException, IOException {
		pars = new Parser();
		pars.creatingWorkingMainPath();
	//	System.out.println(settings.getAll)
		settings = new Ini(new File(pars.creatingPathForSettings()));
		logger = Logger.getLogger(settings.get("settings", "logName"));
		
		logging();
		//

		//
		
		System.out.println("dsfdsfdsfdsfdsfdsfdsfdsfdsfds");
		logger.info("App is started");

		launch(args);

	}
}
