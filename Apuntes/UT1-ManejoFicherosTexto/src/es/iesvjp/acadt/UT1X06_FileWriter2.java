package es.iesvjp.acadt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UT1X06_FileWriter2 {

	public static void main(String[] args) {
		try {
			File fich = new File("C:\\AD\\UT1\\escribir-provincias.txt");
			FileWriter fw = new FileWriter(fich); // crear el flujo de salida
			String prov[] = { "Albacete", "Avila", "Badajoz", "Cáceres", "Huelva", "Jaén", "Madrid", "Segovia", "Soria",
					"Toledo", "Valladolid", "Zamora" };

			for (int i = 0; i < prov.length; i++)	fw.write(prov[i]);
				
			fw.close();

		} catch (IOException io) {
			System.out.println("Error en la E/S");
		}

	}

}
