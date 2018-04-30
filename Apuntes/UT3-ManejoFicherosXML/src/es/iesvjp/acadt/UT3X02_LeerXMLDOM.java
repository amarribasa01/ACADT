package es.iesvjp.acadt;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 * @author Ana Arribas
 *
 */
public class UT3X02_LeerXMLDOM {

	public static void main(String[] args) {
		LeerXML();
	}

	public static void LeerXML() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("personas.xml"));
			document.getDocumentElement().normalize();
			
			System.out.println("Elemento raiz: " + document.getDocumentElement().getNodeName());
			// crea una lista con todos los nodos persona
			NodeList personas = document.getElementsByTagName("persona");
			// recorrer la lista
			for (int i = 0; i < personas.getLength(); i++) {
				Node persona = personas.item(i); // obtener un nodo
				Element elemento = (Element) persona;
				System.out.println("Nombre: " + getNodo("nombre", elemento));
				System.out.println("Edad: " + getNodo("edad", elemento));

			}

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException saxe) {
			saxe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	private static String getNodo(String etiqueta, Element elem) {
		//solamente existe un elemento con dicha etiqueta
		Node nodo = elem.getElementsByTagName(etiqueta).item(0).getFirstChild();
		return nodo.getTextContent();
		
	}

}
