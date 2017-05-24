package application;

import java.awt.Checkbox;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.rmi.CORBA.Util;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller1 {
	static Logger logger = Logger.getLogger(Main.settings.get("settings", "logName"));
	private String nameOfEnv;
	Parser parseForIp;
	@FXML
	private Text top;
	@FXML
	private Label labelIpAdress0;
	@FXML
	private Label labelNameOfServer0;
	@FXML
	private Label labelConnectionStatus0;
	@FXML
	private Label labelPromptStatus0;
	@FXML
	private CheckBox checkBoxAll;
	@FXML
	private CheckBox checkBox0;
	@FXML
	private ToggleButton toggleButtonTAM;
	@FXML
	private Button restartButton;

	Connecting checkConnection;
	WorkWithFile creatingPath;
	RemoteFiles getVportal;
	RemoteFiles putVportal;
	ComparingFiles compare;

	ArrayList<Servers> serversList = new ArrayList<Servers>();

	public void setNameOfEnv(String nameOfEnv) {

		this.nameOfEnv = nameOfEnv;
		top.setText(nameOfEnv);
		// to get arraylist of servers
		parseForIp = new Parser(nameOfEnv);
		serversList = parseForIp.parserIniForIP();
		//
		creatingPath = new WorkWithFile(serversList.get(0).getServerName().toString());
		creatingPath.creatingPathSourseFile(); //

		initializationStatusesOfServ0();

	}

	public void initializationStatusesOfServ0() {

		Runnable task = new Runnable() {
			public void run() {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {

						creatingPath.deletingSourseFilesInPath();

						getVportal = new RemoteFiles(serversList.get(0));
						getVportal.toGetFile();// getting files and making
												// alternate Vportals

						//
						checkConnection = new Connecting(serversList.get(0));
						compare = new ComparingFiles(serversList.get(0));

						labelIpAdress0.setText(serversList.get(0).getIpAdress());
						labelNameOfServer0.setText(serversList.get(0).getServerName());
						labelConnectionStatus0.setText(checkConnection.connect());
						//
						labelPromptStatus0.setText(compare.compareFiles());

						if (labelPromptStatus0.getText().contains("manual")
								|| labelPromptStatus0.getText().contains("automated")) {

							labelPromptStatus0.setDisable(false);

						}
						//
						toggleButtonTAM.setText(checkConnection.statusTam());

						if (toggleButtonTAM.getText().contains("running")) {

							toggleButtonTAM.setSelected(true);
							restartButton.setDisable(false);

						} else {
							toggleButtonTAM.setSelected(false);
							toggleButtonTAM.getText().contains("stopped");
							restartButton.setDisable(false);
						}
						//
					}
				});

			}
		};
		Thread backgroundThread = new Thread(task);
		backgroundThread.setDaemon(true);
		backgroundThread.start();

	}

	public Controller1() {

		// logger.info(nameOfEnv);
		logger.info("\n\n===================    Starting of main window    =====================\n\n ");

	}

	@FXML
	private void onClick() {
		// System.out.println(serversList.toString());

		initializationStatusesOfServ0();
	}

	@FXML
	private void onClickBackButton() {

		try {

			FXMLLoader fxmlLoader = new FXMLLoader();

			fxmlLoader.setLocation(getClass().getResource("frame.fxml"));

			fxmlLoader.load();

			Parent p = fxmlLoader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(p));
			stage.setResizable(false);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void onMouseClickedCancelBtn(InputEvent e) {
		final Node source = (Node) e.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
		//
	}

	@FXML
	private void onClickCheckboxAll() {

		if (checkBoxAll.isSelected()) {
			System.out.println("SELECTED");
			checkBox0.setSelected(true);
		} else {
			System.out.println("NOT SELECTED");
			checkBox0.setSelected(false);
		}

	}

	@FXML
	private void onClickChangePrompts0() {

		Runnable task = new Runnable() {
			public void run() {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {

						putVportal = new RemoteFiles(serversList.get(0), labelPromptStatus0.getText());
						putVportal.toPutFile();

						initializationStatusesOfServ0();
						System.out.println("CLICKED");

					}
				});

			}
		};
		Thread backgroundThread = new Thread(task);
		backgroundThread.setDaemon(true);
		backgroundThread.start();

	}

	@FXML
	private void onClickCheckbox0() {

		if (checkBox0.isSelected()) {
			System.out.println("SELECTED");
			checkBoxAll.setSelected(true);
		} else {
			System.out.println("NOT SELECTED");
			checkBoxAll.setSelected(false);
		}

	}

	@FXML
	private void onClickToggleButtonTamStatus0() {

		System.out.println("onClickToggleButtonTamStatus0");

		Runnable task = new Runnable() {
			public void run() {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {

						checkConnection = new Connecting(serversList.get(0), toggleButtonTAM.getText().toString());
						toggleButtonTAM.setText("waiting...");
						checkConnection.restartingTam();
						initializationStatusesOfServ0();
					}
				});

			}
		};
		Thread backgroundThread = new Thread(task);
		backgroundThread.setDaemon(true);
		backgroundThread.start();

	}

	@FXML
	private void onClickRestartButton() throws IOException {

		System.out.println("onClickRestartButton");

		if (checkBox0.isSelected()) {
			System.out.println("selected");
			RemoteFiles servToReboot0 = new RemoteFiles(serversList.get(0));
			servToReboot0.restartingServer();
			//
			FXMLLoader loader = new FXMLLoader(getClass().getResource("RestartingWindow.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            RestartingWindowForReboot d1 = loader.getController();
            d1.setNameOfEnv(nameOfEnv);
            Parent p = loader.getRoot();
            Stage stage = new Stage();
			stage.setScene(new Scene(p));
			stage.setResizable(false);
			stage.show();
			
			
			
			
			
			
			
			//
			
		}

	}

	@FXML
	private void toShowConfigWindow() {

		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("frameConfig.fxml"));
			System.out.println("start frame for a few");
			fxmlLoader.load();
			ControllerConfig d1 = fxmlLoader.getController();
			d1.setNameOfEnvConfig(nameOfEnv);
			d1.setServer(serversList.get(0));
			Parent p = fxmlLoader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(p));
			stage.setResizable(false);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
		
	

}