package fileShare;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.io.StringWriter;
import java.util.ArrayList;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class AccessHandler {

	public static boolean addValue(Document doc, String field, String value)
			throws Exception {
		// check if element exists before adding it
		Element root = doc.getDocumentElement();
		boolean exists = false;
		NodeList childNodes = root.getElementsByTagName(field);

		for (int i = 0; i < childNodes.getLength(); i++) {
			NodeList subChildNodes = childNodes.item(i).getChildNodes();
			for (int j = 0; j < subChildNodes.getLength(); j++) {
				try {
					if (subChildNodes.item(j).getTextContent().equals(value)) {
						exists = true;
					}
				} catch (Exception e) {
				}
			}
		}
		if (!exists) {
			// create element to be added
			Element newEl = doc.createElement(field);
			// store value
			newEl.setTextContent(value);
			// get parent node
			NodeList parentEl = doc.getElementsByTagName("HOST");
			// get the last parent
			parentEl.item(parentEl.getLength() - 1).appendChild(newEl);
			exists = true;
		} else
			exists = false;
		// exists represents function success in adding the value now
		return exists;
	}

	public static void changeValue(Document doc, String field, String value)
			throws Exception {

		Element root = doc.getDocumentElement();

		NodeList childNodes = root.getElementsByTagName(field);

		for (int i = 0; i < childNodes.getLength(); i++) {
			NodeList subChildNodes = childNodes.item(i).getChildNodes();
			for (int j = 0; j < subChildNodes.getLength(); j++) {
				try {

					subChildNodes.item(j).setTextContent(value);

				} catch (Exception e) {
				}
			}
		}
	}

	public static boolean checkIP(String sip) {
		String[] parts = sip.split("\\.");
		int i = 0;
		if (parts.length != 4) {
			return false;
		}

		// cater for special cases
		if (sip.equals("127.0.0.1") || sip.equals("10.0.0.1"))
			return true;
		for (String s : parts) {
			i = Integer.parseInt(s);

			if (i < 0 || i > 255) {
				System.out.println(i);
				return false;
			}
		}
		// check for invalid addresses containing 0 or 255 then different number
		boolean zero_on_left = false;
		// check numbers from left to right
		// if a number is found to the right of zero or 255 ip is invalid
		for (i = 0; i < parts.length; i++) {
			if (Integer.parseInt(parts[i]) == 0
					|| Integer.parseInt(parts[i]) == 255) {
				zero_on_left = true;
			}
			if (Integer.parseInt(parts[i]) != 0
					&& Integer.parseInt(parts[i]) != 255) {
				if (zero_on_left)
					return false;

			}
		}

		return true;
	}

	public static void removeValue(Document doc, String field, String value)
			throws Exception {

		Element root = doc.getDocumentElement();
		NodeList childNodes = root.getElementsByTagName(field);
		// find the index of value
		for (int i = 0; i < childNodes.getLength(); i++) {
			NodeList subChildNodes = childNodes.item(i).getChildNodes();
			for (int j = 0; j < subChildNodes.getLength(); j++) {
				try {
					if (subChildNodes.item(j).getTextContent().equals(value)) {
						Node x = childNodes.item(i);
						x.getParentNode().removeChild(x);
					}
				} catch (Exception e) {
				}
			}
		}

	}

	public static boolean save(File file, Document doc) throws Exception {

		TransformerFactory factory1 = TransformerFactory.newInstance();
		Transformer transformer = factory1.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		DOMSource source = new DOMSource(doc);
		transformer.transform(source, result);
		String s = writer.toString();

		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		bufferedWriter.write(s);

		bufferedWriter.flush();
		bufferedWriter.close();
		return true;
	}

	public static boolean XMLAdd(String field, String Value) throws Exception {
		// open file
		File file = new File(
				"C:\\Users\\Somoud\\Documents\\My Dropbox\\MScWAD\\WSE-Folder\\Assignment\\WebContent\\WEB-INF\\lib\\file.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		dbf.setCoalescing(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		// parse file
		Document doc = db.parse(file);
		// change value
		boolean s = addValue(doc, field, Value);
		// savefile
		if (s)
			save(file, doc);
		return s;

	}

	public static boolean XMLModify(String field, String value)
			throws Exception {
		// open file
		File file = new File(
				"C:\\Users\\Somoud\\Documents\\My Dropbox\\MScWAD\\WSE-Folder\\Assignment\\WebContent\\WEB-INF\\lib\\file.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		dbf.setCoalescing(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		// parse file
		Document doc = db.parse(file);
		// change value
		changeValue(doc, field, value);
		// savefile
		boolean s = save(file, doc);
		return s;
	}

	public static boolean XMLRemove(String field, String value)
			throws Exception {
		// open file
		File file = new File(
				"C:\\Users\\Somoud\\Documents\\My Dropbox\\MScWAD\\WSE-Folder\\Assignment\\WebContent\\WEB-INF\\lib\\file.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		dbf.setCoalescing(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		// parse file
		Document doc = db.parse(file);
		// change value
		removeValue(doc, field, value);
		// savefile
		boolean s = save(file, doc);
		return s;
	}

	public static ArrayList<String> XMLRreader() {
		ArrayList<String> acl = new ArrayList<String>();
		try {

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			// normalise text representation
			Document doc = docBuilder
					.parse(new File(
							"C:\\Users\\Somoud\\Documents\\My Dropbox\\MScWAD\\WSE-Folder\\Assignment\\WebContent\\WEB-INF\\lib\\file.xml"));
			doc.getDocumentElement().normalize();

			NodeList HostList = doc.getElementsByTagName("IP");
			int totalHost = ((NodeList) HostList).getLength();
			for (int s = 0; s < totalHost; s++) {
				Node ipNode = HostList.item(s);
				if (ipNode.getNodeType() == Node.ELEMENT_NODE) {

					Element ipElement = (Element) ipNode; // ——-

					NodeList textFNList = ipElement.getChildNodes();

					acl.add(((Node) textFNList.item(0)).getNodeValue().trim());
				}// end of if clause
			}// end of for loop with s var
			NodeList MaxList = doc.getElementsByTagName("MAXSIZE");
			int MaxHost = ((NodeList) MaxList).getLength();
			for (int s = 0; s < MaxHost; s++) {
				Node ipNode = MaxList.item(s);
				if (ipNode.getNodeType() == Node.ELEMENT_NODE) {

					Element ipElement = (Element) ipNode; // ——-

					NodeList textFNList = ipElement.getChildNodes();
					acl.add(((Node) textFNList.item(0)).getNodeValue().trim());
				}// end of if clause
			}// end of for loop with s var

		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line "
					+ err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println("" + err.getMessage());
		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return acl;
	}

}
