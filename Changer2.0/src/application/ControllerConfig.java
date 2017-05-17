package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

public class ControllerConfig {
	static Logger logger = Logger.getLogger(Main.settings.get("settings", "logName"));
	@FXML
	private TextArea text;
	@FXML
	private TextArea YourTextArea;

	private String nameOfEnv;
	private Servers server;

	public void setNameOfEnvConfig(String nameOfEnv) {

		this.nameOfEnv = nameOfEnv;
		System.out.println(nameOfEnv);

	}

	public void setServer(Servers server) {

		this.server = server;
		show();
	}

	@FXML
	private void initialize() {

	}

	@FXML
	private void onClickRestartButton() throws FileNotFoundException {

		FileWriter fw = null;
		//
		
		//
		try
		{
		    fw = new FileWriter(Main.settings.get("settings", "mainDirectory") + server.getServerName() + "/" + "VportalAlternative.ini",true);
		    BufferedWriter writer = new BufferedWriter(fw);
		   
		    for (String line : YourTextArea.getText().split("\\n")){ 
		    	System.out.println(line);
		    	//fw.write(line);
		    	
		    	
		    	
		    	
		    	writer.write(line);
		    	writer.newLine();
		    	
		    }
		    writer.close();
           
		   // 
		   // fw.write(System.getProperty("line.separator"));
		  //  fw.write(YourTextArea.getText());
		  // YourTextArea.write(fw);
		   // for (String line : YourTextArea.getText().split("\\n")) doStuffWithLine(line);
		}
		catch (IOException ex) 
		    {
		        ex.printStackTrace();
		    } 
		    finally 
		    {
		       if (fw!=null)
		       {  
		           try {
					fw.close();
				} catch (IOException r) {
					// TODO Auto-generated catch block
					r.printStackTrace();
				}
		       }
		    }
		

	}

	public void show() {

		try {

			FileReader reader;

			reader = new FileReader(
					Main.settings.get("settings", "mainDirectory") + server.getServerName() + "/" + "Vportal.ini");

			Scanner s = new Scanner(reader);

			while (s.hasNext()) {
				if (s.hasNextInt()) { // check if next token is an int
					YourTextArea.appendText(s.nextLine() + "\n"); // display the
																	// found
																	// integer
				} else {
					YourTextArea.appendText(s.nextLine() + "\n"); // else read
																	// the next
																	// token
				}
			}
		} catch (FileNotFoundException ex) {
			System.err.println(ex);
		}
		
		
		

	}

	@FXML
	public void onMouseClickedCancelBtn(InputEvent e) {
		
	//	RemoteFiles fileToServ = new RemoteFiles(server);
	//	fileToServ.toPutModfifiedFile();
		
		final Node source = (Node) e.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
		//
	}

}
