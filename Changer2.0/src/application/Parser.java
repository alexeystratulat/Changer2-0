package application;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {
	private String mainFolder = "C:\\Starter";
	private String settingsFileLocation = mainFolder + "\\settings.ini";

	static Logger logger = Logger.getLogger("MyLog");
	private static String pathForXml;
	private static int lengthMass = 20;

	public Parser(String pathForXml) {
		this.pathForXml = pathForXml;

	}

	public Parser() {

	}

	public static String[] parserForEnvIni() {

		logger.info("Parser for ENV INI is started");

		try {
			Ini list = new Ini(new File(pathForXml));

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

	public static String[] parserForIP() {
		logger.info("Parser for IP is started");
		String[] mass = new String[lengthMass];
		String[] massToBeSent = null;
		int counter = 0;
		int envToShow = 1; // It should be a variable to receive from another
							// class

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(pathForXml);
			NodeList envList = doc.getElementsByTagName("env");

			Node p = envList.item(envToShow);
			if (p.getNodeType() == Node.ELEMENT_NODE) {

				Element env = (Element) p;
				String id = env.getAttribute("id");
				NodeList nameList = env.getChildNodes();
				for (int j = 0; j < nameList.getLength(); j++) {

					String compare;
					Node n = nameList.item(j);
					if (n.getNodeType() == Node.ELEMENT_NODE) {
						Element name = (Element) n;

						compare = name.getTagName();
						if (compare.equals("ip")) {
							mass[counter] = name.getTextContent();

							System.out.print("env " + id + ": " + name.getTagName() + " = " + name.getTextContent());
							System.out.println("            " + counter);
							counter++;
						}
					}

				}

			}

			massToBeSent = new String[counter];
			for (int i = 0; i < counter; i++) {
				massToBeSent[i] = mass[i];

			}

			// return massToBeSent;

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return massToBeSent;

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
			writer.println("xmlForEnv = D:/list.xml");
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