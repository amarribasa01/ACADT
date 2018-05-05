package es.iesvjp.acadt;
import java.io.File;

/**
 * @author Ana Arribas
 *
 */
public class UT1X03_InfoFichero {
	
	 public static void main (String args[])
		{
			System.out.println("Información sobre el fichero Acceso a datos.txt: ");
			System.out.println();
			File fich= new File("acceso-datos.txt");
			if(fich.isFile()) //el fichero existe y es fichero normal
				
			{ 
				System.out.println("Nombre del fichero: "+ fich.getName());
				System.out.println("Ruta relativa: "+ fich.getPath());
				System.out.println("Ruta absoluta: "+ fich.getAbsolutePath());
				System.out.println("Se puede escribir: " + fich.canRead());
				System.out.println("Se puede leer: "+ fich.canWrite());
				System.out.println("Tamaño: "+ fich.length());
				System.out.println("Es un fichero: "+ fich.isFile());
				System.out.println("Es un directorio: "+ fich.isDirectory());
					
			}
		}

	}
