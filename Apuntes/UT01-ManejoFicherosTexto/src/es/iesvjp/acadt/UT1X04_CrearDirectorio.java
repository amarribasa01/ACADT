package es.iesvjp.acadt;

import java.io.*;

/**
 * @author Ana Arribas
 *
 */
public class UT1X04_CrearDirectorio {

	public static void main(String args[]) {
		File dir = new File("new-dir"); // directorio que creo a partir del
										// actual
		File fich1 = new File(dir, "fichero1.txt");
		File fich2 = new File(dir, "fichero2.txt");
		dir.mkdir();// creamos directorio
		try {
			if (fich1.createNewFile())
				System.out.println("Fichero 1 creado correctamente...");
			else
				System.out.println("No se ha podido crear el fichero 1...");
			if (fich2.createNewFile())
				System.out.println("Fichero 2 creado correctamente...");
			else
				System.out.println("No se ha podido crear el fichero 2...");

		} catch (IOException ioe) {System.out.println(ioe.getMessage());
			
		}

		// renombramos el fichero 1
		fich1.renameTo(new File(dir, "fichero1-nuevo"));

		if (fich2.delete())
			System.out.println("Fichero borrado...");
		else
			System.out.println("No se ha podido borrar el fichero...");

	}

}
