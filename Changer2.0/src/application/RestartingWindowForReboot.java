package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RestartingWindowForReboot {
	private Task copyWorker;
	@FXML
	private ProgressBar progressBar;
	//
	@FXML
	private Button button;

	WorkWithFile forFile = new WorkWithFile();

	String nameOfEnv;

	public void setNameOfEnv(String nameOfEnv) {
		this.nameOfEnv = nameOfEnv;
		System.out.println("nameOfEnv was sent to restart window " + nameOfEnv);
		// TODO Auto-generated method stub
	}

	@FXML
	private void initialize() {

		progressBar.setProgress(0);
		copyWorker = createWorker();
		progressBar.progressProperty().unbind();
		progressBar.progressProperty().bind(copyWorker.progressProperty());
		new Thread(copyWorker).start();
		button.setDisable(true);

	}

	public Task createWorker() {
		return new Task() {
			@Override
			protected Object call() throws Exception {			

				for (int i = 0; i <= 100; i++) {
					Thread.sleep(100);
					updateMessage("100 milliseconds");
					updateProgress(i + 1.0, 100);

					System.out.println(progressBar.getProgress());
					if (progressBar.getProgress() == 0.5) {
						forFile.deletingOfFolders();
					}
					if (progressBar.getProgress() == 1.0) {
						button.setDisable(false);
					}
				}

				return true;
			}
		};

	}

	public void openNextWindow() throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("frame1.fxml"));
		fxmlLoader.load();
		Controller1 d1 = fxmlLoader.getController();
		d1.setNameOfEnv(nameOfEnv);
		Parent p = fxmlLoader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(p));
		stage.setResizable(false);
		stage.show();

	}

	@FXML
	private void closeCurrentWindow(InputEvent e) {
		final Node source = (Node) e.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

}
