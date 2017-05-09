package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;

import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ControllerConfig {
	@FXML
	private TextArea text;
	@FXML
	private TextArea YourTextArea;

	@FXML
	private void initialize() {
		init2();
	}

	@FXML
	private void onClickRestartButton() throws FileNotFoundException {

		System.out.println("azazaz");

	}

	public void init2() {

		try {

			// YourTextArea.clear();

			FileReader reader;

			reader = new FileReader("C:/Starter/SERV01/Vportal.ini");
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

}
