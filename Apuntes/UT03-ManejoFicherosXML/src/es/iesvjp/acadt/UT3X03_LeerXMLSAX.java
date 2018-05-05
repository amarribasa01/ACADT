package es.iesvjp.acadt;

import java.io.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * @author Ana Arribas
 *
 */
public class UT3X03_LeerXMLSAX {

	public static void main(String[] args) 	throws FileNotFoundException, IOException, SAXException
	{
		XMLReader procesadorXML = XMLReaderFactory.createXMLReader(); 
		ManejadorEventos manejador= new ManejadorEventos(); 
		procesadorXML.setContentHandler(manejador);
		InputSource fileXML = new InputSource("personas1.xml"); 
		procesadorXML.parse(fileXML);
	}
}

