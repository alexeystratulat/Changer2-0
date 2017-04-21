package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Editor {

	static Logger logger = Logger.getLogger(Main.settings.get("settings", "logName"));
	private PrintWriter writer = null;
	private FileReader reader = null;
	private Servers server;
	private Scanner read;
	private String vportalDirectory;
	private String vportal;
	private String vportalStandart;
	private String vportalAutomation;

	public Editor(Servers server, String vportalDirectory) {
		this.server = server;
		this.vportalDirectory = vportalDirectory;
		vportal = vportalDirectory + "/Vportal.ini";
		vportalStandart = vportalDirectory + "/Vportal_st.ini";
		vportalAutomation = vportalDirectory + "/Vportal_au.ini";

	}

	//
	public int makingVportalSt() {
		try {

			reader = new FileReader(vportal);
			read = new Scanner(reader);
			String line;

			writer = new PrintWriter(vportalStandart);

			while ((line = read.nextLine()) != null) {

				line = line.replaceAll(Main.settings.get("prompts", "NAutomated"),
						Main.settings.get("prompts", "NManual"));
				line = line.replaceAll(Main.settings.get("prompts", "BAutomated"),
						"http://" + server.getIpAdress() + Main.settings.get("prompts", "BManual"));
				line = line.replaceAll(Main.settings.get("prompts", "MAutomated"),
						Main.settings.get("prompts", "MManual"));

				writer.println(line);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			
			writer.close();

			try {
				reader.close();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}

		}
		read.close();
		writer.close();
		

		return 1;

	}

	public int makingVportalAutomation() {
		try {

			reader = new FileReader(vportalStandart);

			read = new Scanner(reader);
			String line;

			writer = new PrintWriter(vportalAutomation);

			while ((line = read.nextLine()) != null) {

				line = line.replaceAll(Main.settings.get("prompts", "NManual"),
						Main.settings.get("prompts", "NAutomated"));
				line = line.replaceAll("http://" + server.getIpAdress() + Main.settings.get("prompts", "BManual"),
						Main.settings.get("prompts", "BAutomated"));
				line = line.replaceAll(Main.settings.get("prompts", "MManual"),
						Main.settings.get("prompts", "MAutomated"));
				writer.println(line);

				

			}

			writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			
			writer.close();
			read.close();
			try {
				reader.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		return 1;

	}

}
