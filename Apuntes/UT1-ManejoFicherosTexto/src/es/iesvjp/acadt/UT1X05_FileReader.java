package es.iesvjp.acadt;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UT1X05_FileReader {

	public static void main(String[] args) {
		
		try{ 	
			File fich= new File("C:\\AD\\UT1\\acceso-datos.txt");
		if(fich.isFile()) //es un fichero
			{
				FileReader fr= new FileReader(fich);
				char[] arr= new char[100];
				while ((fr.read(arr))!=-1)
	                   {//leemos el fichero carácter a carácter hasta que lleguemos al
	                    //final del fichero(-1)
					System.out.println(arr);
					
				}
				fr.close();//cerramos el fichero
				
			}
		
		   }catch (IOException io){System.out.println("Error en la E/S");}
		}

		
	}


