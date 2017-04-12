package application;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {
	private String mainFolder = "C:\\Starter";
	private String settingsFileLocation = mainFolder + "\\settings.ini";

	static Logger logger = Logger.getLogger("MyLog");
	private static String serversIp;

	public Parser(String serversIp) {
		this.serversIp = serversIp;

	}

	public Parser() {

	}

	public static String[] parserForEnvIni() {

		logger.info("Parser for ENV INI is started");

		try {
			Ini list = new Ini(new File(Main.settings.get("settings", "iniForEnv")));

			String[] massToBeSent = new String[list.keySet().size()];

			int counter = 0;
			for (String sectionName : list.keySet()) {

				massToBeSent[counter] = sectionName.toString();
				counter++;

			}

			return massToBeSent;

		} catch (InvalidFileFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static ArrayList<Servers> parserIniForIP() {
		ArrayList<Servers> list = new ArrayList<Servers>();
		//

		Ini listOfIP;
		try {
			logger.info(Main.settings.get("settings", "iniForEnv"));
			listOfIP = new Ini(new File(Main.settings.get("settings", "iniForEnv")));
			// creating arrayList with name of servers, Ip 
			
			Section section = listOfIP.get(serversIp);

			for (String optionKey : section.keySet()) {

				
				list.add(new Servers(section.get(optionKey), Main.settings.get("server", "user"),
						Main.settings.get("server", "password")));

				
			}
			
			
			
			//System.out.println(list);
			

			return list;

		} catch (InvalidFileFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public void parserForSetting() {
		logger.info("Parser setting is started");

	}

	public String creatingPathForSettings() throws IOException {
		logger.info("Creating path for settings is started");

		File settingsFile = new File(settingsFileLocation);

		if (!settingsFile.exists()) {

			settingsFile.createNewFile();
			PrintWriter writer = new PrintWriter(settingsFileLocation, "UTF-8");
			writer.println("[settings]");
			writer.println("logPath = D:/MyLogFile.log");
			writer.println("logName = MyLog");
			writer.println("iniForEnv = D:/list.ini");
			writer.println("[Second]");
			writer.println("second = 2");
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
