package application;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class WorkWithFile {
	private String mainFolder = "C:\\Starter";
	private String settingsFileLocation = mainFolder + "\\settings.ini";
	static Logger logger = Logger.getLogger("MyLog");
	// static Logger logger = Logger.getLogger(Main.settings.get("settings",
	// "logName"));

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
			writer.println("logPath = D:/MyLogFile.log");
			writer.println("logName = MyLog");
			writer.println("iniForEnv = D:/list.ini");
			writer.close();

			logger.info(settingsFileLocation + " created");

		} else {

			logger.info(settingsFileLocation + " is exist");

		}
		return settingsFileLocation;

	}

	public void creatingPathSourseFile() {
		logger.info("Creating sourse path is started");
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
