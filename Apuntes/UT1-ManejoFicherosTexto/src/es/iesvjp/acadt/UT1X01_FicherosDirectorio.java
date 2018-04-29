package es.iesvjp.acadt;

import java.io.File;


public class UT1X01_FicherosDirectorio {

	public static void main(String[] args) 
    {
		System.out.println("Ficheros en el directorio actual:");
		File dir = new File (".");
		String[] archivos = dir.list();
		for (int i = 0; i < archivos.length; i++) 
			{
				System.out.println(archivos[i]);
				
			}
	}


}
