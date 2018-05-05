package es.iesvjp.acadt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * @author Ana Arribas
 *
 */
public class UT2X01_FileInput_OutputStream {

	private static File fich = new File("C:\\AD\\UT1\\fichero-bytes.dat");
	
	public static void main(String[] args) {
		
		EscribirFicheroBytes();
		LeerFicheroBytes();
	}

	public static void EscribirFicheroBytes() {

		try {
			// crea flujo de salida hacia el fichero
			FileOutputStream fos = new FileOutputStream(fich);
			for (int i = 1; i < 100; i++)
				fos.write(i); // escribe datos en el flujo de salida

			fos.close(); // cerrar stream de salida

		} catch (IOException ioe) {
			System.out.println("Error en las operaciones de E/S: " + ioe.getMessage());
		}
	}

	public static void LeerFicheroBytes() {
		int i;

		try {

			// crea flujo de entrada
			FileInputStream fis = new FileInputStream(fich);

			// visualizar los datos del fichero
			while ((i = fis.read()) != -1) // lee datos del flujo de entrada
				System.out.println(i);

			fis.close(); // cerrar stream de entrada
			
		} catch (IOException ioe) {
			System.out.println("Error en las operaciones de E/S: " + ioe.getMessage());
		}
	}

}
