package es.iesvjp.acadt;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Ana Arribas
 *
 */
public class UT3X01_EscribirXMLDOM {

	private static final String sfichero = "C:\\AD\\UT3\\fichero-personas.dat";

	public static void main(String[] args) throws IOException {
		CrearPersonasXML();
	}

	public static void CrearPersonasXML() {

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			DOMImplementation implementation = builder.getDOMImplementation();

			Document document = implementation.createDocument(null, "Personas", null);
			document.setXmlVersion("1.0");

			ArrayList<Persona> personas = GetPersonasFichero();
			Iterator it = personas.iterator();

			while (it.hasNext()) {
				Persona persona = (Persona) it.next();
				Element nodo = document.createElement("persona"); // nodo persona
				document.getDocumentElement().appendChild(nodo);
				//dentro del nodo persona nos creamos sus nodos hijos/elementos
				CrearElemento("nombre", persona.getNombre(), nodo, document);
				CrearElemento("edad", Integer.toString(persona.getEdad()), nodo, document);

			}

			Source source = new DOMSource(document);
			Result result = new StreamResult(new java.io.File("personas.xml"));

			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);

		} catch (ParserConfigurationException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

  /**
 * @return devuelve una ArrayList con todas las personas que hay en el fichero
 * 
 */
public static ArrayList<Persona> GetPersonasFichero() {

		ArrayList<Persona> personas = new ArrayList<Persona>();
		Persona persona;
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		File fichero = new File(sfichero);
		try {
			fis = new FileInputStream(fichero);

			ois = new ObjectInputStream(fis);

			persona = (Persona) ois.readObject();
			while (persona != null) {
				personas.add(persona);
				persona = (Persona) ois.readObject();

			}
			ois.close();

		} catch (IOException ioe) {
			System.out.println("Fin de fichero" + ioe.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error al leer los objetos " + cnfe.getMessage());

		}

		return personas;
	}

	static void CrearElemento(String elemento, String valor, Element nodo, Document document) {
		Element elem = document.createElement(elemento); // creamos nodo Element
		Text text = document.createTextNode(valor); // creamos nodo Text
		elem.appendChild(text); // añadimos el valor
		nodo.appendChild(elem); // añadimos el elemento hijo a la raiz

	}

}
