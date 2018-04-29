package es.iesvjp.acadt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UT1X07_BufferedReader {

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\AD\\UT1\\acceso-datos.txt"));
			String linea;
			while ((linea = br.readLine()) != null) // lee una línea del fichero
				System.out.println(linea);
			
			br.close();
		} catch (IOException io) {System.out.println("Error en la E/S: " + io.getMessage());

		}
	}
}
