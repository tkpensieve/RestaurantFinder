import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class XmlValidator {
	public static boolean validateXMLSchema(File xsdFile, File xmlFile){    
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFile));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        return true;
    }

	public static boolean validateXMLDtd(File dtdFile, File xmlFile){
		try{
			  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			  factory.setValidating(true);
			  DocumentBuilder builder = factory.newDocumentBuilder();
			  builder.setErrorHandler(new org.xml.sax.ErrorHandler() {
				  //Ignore the fatal errors
				  public void fatalError(SAXParseException exception)
						  throws SAXException { }
				  //Validation errors 
				  public void error(SAXParseException e)
						  throws SAXParseException {
					  System.out.println("Error at " +e.getLineNumber() + " line.");
					  System.out.println(e.getMessage());
					  System.exit(0);
			  }
			  //Show warnings
			  public void warning(SAXParseException err)
					  throws SAXParseException{
				  System.out.println(err.getMessage());
				  System.exit(0);
			  }
			  });
			  Document xmlDocument = builder.parse(xmlFile);
//			  DOMSource source = new DOMSource(xmlDocument);
//			  StreamResult result = new StreamResult(System.out);
//			  TransformerFactory tf = TransformerFactory.newInstance();
//			  Transformer transformer = tf.newTransformer();
//			  transformer.setOutputProperty(
//			  OutputKeys.DOCTYPE_SYSTEM, dtdFile.getName());
//			  transformer.transform(source, result);
			  return true;
			  }
			  catch (Exception e) {
				  System.out.println(e.getMessage());
				  return false;
			  }
	}
} 