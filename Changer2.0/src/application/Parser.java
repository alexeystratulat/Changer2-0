package application;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {
	static Logger logger = Logger.getLogger("MyLog");
	private static String pathForXml;
	private static int lengthMass = 20;

	public Parser(String pathForXml) {
		this.pathForXml = pathForXml;

	}

	public static String[] parserForEnv() {
		
		logger.info("Parser for ENV is started");

		String[] mass = new String[lengthMass];
		String[] massToBeSent = null;
		int counter = 0;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(pathForXml);
			NodeList envList = doc.getElementsByTagName("env");

			for (int i = 0; i < envList.getLength(); i++) {
				Node p = envList.item(i);
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
							if (compare.equals("name")) {
								mass[counter] = name.getTextContent();

								counter++;
							}
						}

					}

				}

			}

			massToBeSent = new String[counter];
			for (int i = 0; i < counter; i++) {
				massToBeSent[i] = mass[i];

			}

			

		} catch (ParserConfigurationException e) {
			logger.warning("ParserConfigurationException");
			e.printStackTrace();
		} catch (SAXException e) {
			logger.warning("SAXException");
			e.printStackTrace();
		} catch (IOException e) {
			logger.warning("IOException");
			e.printStackTrace();
		}

		logger.info("Array with Environment: " + Arrays.toString(massToBeSent) + " is sent");
		return massToBeSent;

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

	public void creatingPathForSettings() {
		logger.info("Creating path for settings is started");

	}

	public void creatingPathSourseFile() {
		logger.info("Creating sourse path is started");
	}

	public void creatingWorkingMainPath() {
		logger.info("Creating main path is started");

		File theDir = new File("C:\\Starter");

		if (!theDir.exists()) {
			logger.info("creating directory");
			

			theDir.mkdir();
			logger.info("DIR created");

			

		}

	}

}
