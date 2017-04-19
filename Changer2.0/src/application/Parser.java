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

		logger.info("Parser for resourses file is started: " + Main.settings.get("settings", "iniForEnv"));

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
			logger.info(
					"Class used resourse file for creating arraylist: " + Main.settings.get("settings", "iniForEnv"));
			listOfIP = new Ini(new File(Main.settings.get("settings", "iniForEnv")));
			// creating arrayList with name of servers, Ip

			Section section = listOfIP.get(serversIp);

			for (String optionKey : section.keySet()) {

				// System.out.println(optionKey.toString());
				// System.out.println(section.toString());

				list.add(new Servers(optionKey.toString(), section.get(optionKey), Main.settings.get("server", "user"),
						Main.settings.get("server", "password")));

			}

			// System.out.println(list);
			logger.info(list.toString());

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

}
