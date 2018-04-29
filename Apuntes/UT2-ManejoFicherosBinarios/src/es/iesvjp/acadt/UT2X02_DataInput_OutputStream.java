package es.iesvjp.acadt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * @author Ana Arribas
 *
 */
public class UT2X02_DataInput_OutputStream {
	
	static File fich= new File("C:\\AD\\UT1\\fichero-datos.dat");
	
	public static void main(String[] args) {
		try {
			
			EscribirFichDatos();
			MostrarFichDatos();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void EscribirFichDatos()
	{
	
	 try{
			
		FileOutputStream fos = new FileOutputStream(fich);
		DataOutputStream dos= new DataOutputStream(fos);
		String nombre[] = {"Ana","Luis Miguel","Alicia","Pedro", "Manuel", "Andrés" , "Julio", "Antonio","Maria Jesús"};
		int edades[] = {14,15,13,15,16,12,16,14,13};
		for (int i=0;i<edades.length; i++){
		dos.writeUTF(nombre[i]); //inserta nombre 
	    dos.writeInt(edades[i]); //inserta edad
	   }
		 dos.close(); //cerrar stream
		  
	 }catch(IOException ioe){System.out.println("Error en las operaciones de E/S: " + ioe.getMessage());}

	}
	public static void MostrarFichDatos() throws IOException
	{
	DataInputStream dis=null;
	 try{
		
		FileInputStream fis = new FileInputStream(fich);
		dis= new DataInputStream(fis);

		while(true){
			System.out.println(dis.readUTF()+ " Edad: "+ dis.readInt() );
		    }			    	    
		 }catch(EOFException e){
	         //... Nos saltara este mensaje
	         System.out.println("Fin del fichero");
	        
	     }catch(IOException e){
	         System.out.println("Error E/S"); 
	     }finally {dis.close();//cerramos flujo }
			
	}

	}

}
