package es.iesvjp.acadt;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
/**
 * @author Ana Arribas
 *
 */
public class UT3X05_LeerXMLStAX {

	public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
		List<Libro> ListLibros = new ArrayList<>();
		Libro lib = null;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(new FileReader("libros.xml"));

		while (reader.hasNext()) {
			int event = reader.next();
			if (event == XMLStreamConstants.START_ELEMENT) {
				String nodo = reader.getLocalName();
				switch (nodo) {
				case "libro": {
					lib = new Libro();
					lib.setISBN(reader.getAttributeValue(0));
				}
					break;
				case "titulo":
					lib.setTitulo(reader.getElementText());
					break;
				case "autor":
					lib.setAutor(reader.getElementText());
					break;
				case "editorial":
					lib.setEditorial(reader.getElementText());
					break;
				}
			}
			if ((event == XMLStreamConstants.END_ELEMENT) && (reader.getLocalName() == "libro")) {
				ListLibros.add(lib);
			}

		} // fin while

		for (Libro libro : ListLibros)
			System.out.println(libro);

	}// fin main
}
