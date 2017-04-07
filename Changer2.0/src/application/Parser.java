package application;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {
	private static String pathForXml;
	private static int lengthMass = 20;

	public Parser(String pathForXml) {
		this.pathForXml = pathForXml;

	}

	public static String[] parserForEnv() {

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

								System.out
										.print("env " + id + ": " + name.getTagName() + " = " + name.getTextContent());
								System.out.println("            " + counter);
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

			return massToBeSent;

		} catch (ParserConfigurationException e) {
			System.out.println("111111111111111111111");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			System.out.println("22222222222");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("333333333333");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return massToBeSent;

	}

	public static String[] parserForIP() {

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

	}

	public void creatingPathForSetting() {

	}

	public void creatingPathSourseFile() {

	}

}
