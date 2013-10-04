import java.io.File;
import java.util.Iterator;

import javax.naming.spi.DirectoryManager;


public class Main {

	public static void main(String[] args) {
		String rootLocation = Main.class.getClassLoader().getResource(".").getPath();
		String xmlsFolder = rootLocation + "../Xmls/";
		String schemasFolder = rootLocation + "../Schemas/";
		String dtdsFolder = rootLocation + "../Dtds/";
		File[] schemaFiles = new File(schemasFolder).listFiles();
		System.out.println("Validating using schema");
		
		//Schema validation
		for (File f : schemaFiles) {
			String[] nameComponents = f.getName().split("\\.");
		    String xmlName = nameComponents[0] + "_" + nameComponents[1];
		    boolean isXmlValid = XmlValidator.validateXMLSchema(f, new File(xmlsFolder + xmlName + ".xml"));
		    System.out.println(xmlName + " xml validation result is " + isXmlValid);
		}
		
		//Dtd validation
		File[] dtdFiles = new File(dtdsFolder).listFiles();
		System.out.println("Validating using dtds");
		for (File f : dtdFiles) {
			String[] nameComponents = f.getName().split("\\.");
		    String xmlName = nameComponents[0] + "_" + nameComponents[1];
		    boolean isXmlValid = XmlValidator.validateXMLDtd(f, new File(xmlsFolder + xmlName + ".xml"));
		    System.out.println(xmlName + " xml validation result is " + isXmlValid);
		}
	}
}
