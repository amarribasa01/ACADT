package es.iesvjp.acadt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UT1X08_BufferedWriter {

	public static void main(String[] args) {
		try{ 	
			BufferedWriter bw = new BufferedWriter (new FileWriter("C:\\AD\\UT1\\fich-lineas.txt")); 
			//escribimos 10 filas en un fichero
			for (int i=0; i<=10; i++)
			{	bw.write("Fila numero: "+i); //escribe una línea 
				bw.newLine(); //escribe un salto de línea
			
			}
			bw.close() ;
									
		}catch (IOException io){System.out.println("Error en la E/S: "+ io.getMessage());}

	}

}
