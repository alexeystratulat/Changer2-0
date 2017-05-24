package application;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class RestartingWindowForReboot {
	private Task copyWorker;
	@FXML
	private ProgressBar progressBar;
	//
	
	String nameOfEnv;
	
	
	
	
	public void setNameOfEnv(String nameOfEnv) {
		this.nameOfEnv =nameOfEnv;
		System.out.println("nameOfEnv was sent to restart window "+nameOfEnv);
		// TODO Auto-generated method stub
		
	}

	@FXML
	private void initialize() {

		progressBar.setProgress(0);
		copyWorker = createWorker();

		progressBar.progressProperty().unbind();
		progressBar.progressProperty().bind(copyWorker.progressProperty());

		new Thread(copyWorker).start();

	}

	public Task createWorker() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				 //Platform.exit();
				
				for (int i = 0; i <= 100; i++) {
					Thread.sleep(50);
					updateMessage("100 milliseconds");
					updateProgress(i + 1.0, 100);

					System.out.println(progressBar.getProgress());
					if (progressBar.getProgress()==1.0){
						
						 
						    Platform.exit();
						
					}
				}
				return true;
			}
		};
	}


}
