package es.iesvjp.acadt;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 * @author Ana Arribas
 *
 */
public class ManejadorEventos extends DefaultHandler {
	public ManejadorEventos() {
		super();
	}

	public void startDocument() {
		System.out.println("Comienzo del Documento XML");
	}

	public void endDocument() {
		System.out.println("Final del Documento XML");
	}

	/**
	 * @param uri: dirección del namespace
	 * @param nombrelocal:nombre local (sin prefijo): element
	 * @param nombreC: nombre completo (con prefijo): xs:element
	 */
	public void startElement(String uri, String nombrelocal, String nombreC, Attributes atts) {
		System.out.println("\tPrincipio Elemento: " + nombrelocal);
		//si tuviese un atributo llamado dni
		//System.out.println(atts.getValue("dni"));
	}

	public void endElement(String uri, String nombrelocal, String nombreC) {
		System.out.println("\tFin Elemento: " + nombrelocal);
	}

	public void characters(char[] ch, int inicio, int longitud) throws SAXException {
		//el array ch contiene todo el texto XML, tenemos que coger
		//los caracteres que comienzan en inicio hasta la longitud que recibimos
		String car = new String(ch,inicio,longitud);
		car = car.replaceAll("[\t\n]", "");// quitar saltos de linea
		if(car.trim().length()>0) System.out.println("\tCaracteres : " + car);
	}

}
