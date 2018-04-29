package es.iesvjp.acadt;

import java.io.File;
import java.util.Scanner;

/**
 * @author Ana Arribas
 *
 */
public class UT1X02_FicherosDirectorioPar {

	public static void main(String[] args) {
		String dir;
		dir = PedirCadena("Introduzca la ruta absoluta del Directorio a consultar");
		MostrarFicherosDirectorio(dir);

	}

	public static void MostrarFicherosDirectorio(String directorio) {
		File f = new File(directorio);
		System.out.println("Archivos en el directorio " + directorio);

		String[] archivos = f.list();
		for (int i = 0; i < archivos.length; i++) {
			System.out.println(archivos[i]);
		}

	}

	public static String PedirCadena(String cadena) {
		Scanner teclado = new Scanner(System.in);
		System.out.println(cadena);
		return teclado.nextLine();

	}

}
