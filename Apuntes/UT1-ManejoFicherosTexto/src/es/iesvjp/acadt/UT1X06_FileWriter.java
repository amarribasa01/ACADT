package es.iesvjp.acadt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UT1X06_FileWriter {

	public static void main(String[] args) 
	{
		 try{ 	
				File fich= new File("C:\\AD\\UT1\\escribir-fichero.txt");
				FileWriter fw = new FileWriter (fich,true) ; //crear el flujo de salida 
				String cadena ="Esto es una prueba con FileWriter";
				char[] char_arr = cadena.toCharArray();//convierte un String en array de caracteres 
//				for(int i=0; i<char_arr.length; i++)
//				{
//				  fw.write(char_arr[i]); //se va escribiendo carácter a carácter
//				}
				fw.write(char_arr);
				fw.close();
							
			  }catch (IOException io){System.out.println("Error en la E/S");}
			 }
		
	}
	
	
