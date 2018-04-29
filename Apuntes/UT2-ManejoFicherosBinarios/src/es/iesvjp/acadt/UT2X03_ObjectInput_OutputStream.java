package es.iesvjp.acadt;

import java.io.*;
import java.util.*;

/**
 * @author Ana Arribas
 *
 */
public class UT2X03_ObjectInput_OutputStream {

	private static final String sfichero = "C:\\AD\\UT1\\fichero-personas.dat";

	public static void main(String[] args) {
		int opc = 0;
		try {
			Scanner sc = new Scanner(System.in);
			while (opc < 3) {
				System.out.println("1-Escribir en el fichero");
				System.out.println("2-Leer en el fichero");
				System.out.println("3-Salir");
				System.out.println("Introduzca una opción");
				opc = sc.nextInt();

				switch (opc) {
				case 1:
					escribirPersona();
					break;
				case 2:
					leerPersona();
					break;
				}

			}
			sc.close();

		} catch (IOException ioe) {
			System.out.println("Error en la operación de E/S: " + ioe.getMessage());
		}
	}

	public static void escribirPersona() {
		String nombres[] = { "Ana", "Luis Miguel", "Alicia", "Pedro", "Manuel", "Andrés", "Julio", "Antonio",
				"Maria Jesús" };
		int edades[] = { 14, 15, 13, 15, 16, 12, 16, 14, 13 };
		Persona persona;
		FileOutputStream fos = null;
		ObjectOutputStream ous = null;

		try {
			File fich = new File(sfichero);
			if (fich.exists()) {// el fichero existe, ponemos el segundo
								// parámetro a true para poder añadir
				fos = new FileOutputStream(fich, true);
				// no añadimos cabecera
				ous = new MiObjectOutputStream(fos);
			} else {
				fos = new FileOutputStream(fich);
				ous = new ObjectOutputStream(fos);

			}
			for (int i = 0; i < edades.length; i++) { // recorro los arrays
				persona = new Persona(nombres[i], edades[i]);
				ous.writeObject(persona);
			}

			ous.close();
		} catch (IOException ioe) {
			System.out.println("Error al escribir el objeto persona: " + ioe.getMessage());
		}
	}

	public static void leerPersona() throws IOException {
		Persona persona = null;
		ObjectInputStream ois = null;
		try {
			File fich = new File(sfichero);
			FileInputStream fis = new FileInputStream(fich);// crea el flujo de
															// entrada
			// conecta el flujo de bytes al flujo de objetos
			ois = new ObjectInputStream(fis);
			while (true) {
				persona = (Persona) ois.readObject();
				System.out.println("Nombre: " + persona.getNombre() + " edad: " + persona.getEdad());
			}

		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error en la lectura del objeto: " + cnfe.getMessage());
		} catch (IOException ioe) {
			
		} finally {
			ois.close();
		}

	}
}
