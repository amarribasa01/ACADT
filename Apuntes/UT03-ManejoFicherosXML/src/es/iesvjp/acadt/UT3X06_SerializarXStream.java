package es.iesvjp.acadt;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class UT3X06_SerializarXStream {
	/**
	 * @author Ana Arribas
	 *
	 */
	public static void main(String[] args) {
		CrearFicheroXML();
	}
	
	public static void CrearFicheroXML()
	{
	File fichero = new File("C:\\AD\\UT3\\fichero-personas.dat");
	ListaPersonas listaper=null;
	try
	 {
	FileInputStream filein = new FileInputStream (fichero);//flujo de entrada
		//conecta el flujo de bytes al flujo de datos 
		ObjectInputStream dataIS = new ObjectInputStream (filein);
		System.out.println("Comienza el proceso de creación del fichero a XML ...");

		//Creamos un objeto Lista de Personas 
		listaper = new ListaPersonas();
		 try {
		   while (true) { //lectura del fichero
		   Persona persona= (Persona) dataIS.readObject(); //leer una Persona 
		   listaper.add(persona); //añadir persona a la lista
				}
			}catch (EOFException eoe) {
				dataIS.close(); //cerrar stream de entrada
			}catch (ClassNotFoundException cnfe) {cnfe.printStackTrace();}

			
		XStream xstream = new XStream(new DomDriver("UTF-8"));
		//cambiar de nombre a las etiquetas XML
	   xstream.alias("Persona", Persona.class);
		
		//cambiar de nombre a las etiquetas XML
		xstream.alias("Personas", ListaPersonas.class);
		
		//quitar etiqueta lista (atributo de la clase ListaPersonas) 
		xstream.addImplicitCollection(ListaPersonas.class, "lista"); //Insertar los objetos en el XML
		xstream.toXML(listaper, new FileOutputStream("C:\\AD\\UT3\\Personas.xml"));
		System.out.println("Creado fichero XML....");
	}catch (FileNotFoundException fnfe){fnfe.printStackTrace();
		} catch (IOException ioe){ioe.printStackTrace();}
			
	}

}
