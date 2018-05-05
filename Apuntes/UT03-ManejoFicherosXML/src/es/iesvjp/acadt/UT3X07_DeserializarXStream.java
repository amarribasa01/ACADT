package es.iesvjp.acadt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ListIterator;

import com.thoughtworks.xstream.XStream;
/**
 * @author Ana Arribas
 *
 */
public class UT3X07_DeserializarXStream {

	public static void main(String[] args) {
		LeerXML();
	}

	public static void LeerXML() {
		XStream xstream = new XStream();
		xstream.alias("Persona", Persona.class);
		xstream.addImplicitCollection(ListaPersonas.class, "lista");

		try {
			ListaPersonas personas;
			// recuperamos el objeto ListaPersonas
			personas = (ListaPersonas) xstream.fromXML(new FileInputStream("C:\\AD\\UT3\\Personas.xml"));

			System.out.println("Número de Personas: " + personas.getListaPersonas().size());

			List<Persona> listaPersonas = personas.getListaPersonas();

			// iterador de la lista para recorrer cada persona
			ListIterator<Persona> iterador = listaPersonas.listIterator();

			while (iterador.hasNext()) {
				Persona p = (Persona) iterador.next(); // Obtengo la persona
				System.out.println("Nombre: " + p.getNombre() + ", edad: " + p.getEdad());
			}
			System.out.println("Fin de listado 	");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
