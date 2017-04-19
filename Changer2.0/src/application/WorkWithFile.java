package application;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class WorkWithFile {
	private String mainFolder = "C:\\Starter";
	private String settingsFileLocation = mainFolder + "\\settings.ini";
	private String nameOfserv;
	static Logger logger = Logger.getLogger("MyLog");

	public WorkWithFile() {

	}

	public WorkWithFile(String nameOfServ) {
		this.nameOfserv = nameOfServ;

	}

	File theDir = new File("C:\\Starter");

	public void deletingOfFolders() {

		for (File file : theDir.listFiles()) {
			if (file.isDirectory()) {

				for (File fileToDeleteTxt : file.listFiles()) {
					fileToDeleteTxt.delete();

				}

				file.delete();
			}

		}

	}

	public String creatingPathForSettings() throws IOException {
		// logger.info("Creating path for settings is started");

		File settingsFile = new File(settingsFileLocation);

		if (!settingsFile.exists()) {

			settingsFile.createNewFile();
			PrintWriter writer = new PrintWriter(settingsFileLocation, "UTF-8");
			writer.println("[settings]");
			writer.println("logPath = D:/MyLogFile");
			writer.println("logName = MyLog");
			writer.println("iniForEnv = D:/list.ini");
			writer.println("[server]");
			writer.println("user = default");
			writer.println("password = default");
			writer.close();

			logger.info(settingsFileLocation + " created");

		} else {

			logger.info(settingsFileLocation + " is exist");

		}
		return settingsFileLocation;

	}

	public void creatingPathSourseFile() {
		logger.info("Creating sourse path is started");

		File serverResoursesDirectory = new File(mainFolder + "\\" + nameOfserv);

		if (!serverResoursesDirectory.exists()) {

			serverResoursesDirectory.mkdir();
			logger.info(mainFolder + "\\" + nameOfserv + "Created");

		} else {
			logger.info(mainFolder + "\\" + nameOfserv + " is exist");

		}
		
		
		
		

	}

	public void creatingWorkingMainPath() {
		logger.info("Creating main path is started");

		File starterDirectory = new File(mainFolder);

		if (!starterDirectory.exists()) {

			starterDirectory.mkdir();
			logger.info("DIR " + mainFolder + " created");

		} else {

			logger.info(mainFolder + " is exist");

		}

	}

}
