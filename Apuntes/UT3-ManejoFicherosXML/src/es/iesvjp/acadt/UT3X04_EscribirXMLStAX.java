package es.iesvjp.acadt;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * @author Ana Arribas
 *
 */
public class UT3X04_EscribirXMLStAX {
	
	   public static void main(String[] args) {
		      try {
		         FileWriter fw= new FileWriter("libros.xml");
		         XMLOutputFactory factory = XMLOutputFactory.newInstance();	
		         XMLStreamWriter xMLStreamWriter = factory.createXMLStreamWriter(new FileOutputStream("libros.xml"),"UTF-8");
		                
		       
		         xMLStreamWriter.writeStartDocument();
		         xMLStreamWriter.writeStartElement("libros");
		        
			      xMLStreamWriter.writeStartElement("libro");			
			         xMLStreamWriter.writeAttribute("ISBN", "239-87-9964-088-4");
				         
			      xMLStreamWriter.writeStartElement("titulo");			
			         xMLStreamWriter.writeCharacters("Acceso a Datos");
			      xMLStreamWriter.writeEndElement();
			
			      xMLStreamWriter.writeStartElement("autor");			
			         xMLStreamWriter.writeCharacters("Alicia Ramos");
			      xMLStreamWriter.writeEndElement();
			
			      xMLStreamWriter.writeStartElement("editorial");
			      xMLStreamWriter.writeCharacters("Garceta");
			      xMLStreamWriter.writeEndElement();
			      
			      xMLStreamWriter.writeStartElement("Precio");
			      xMLStreamWriter.writeCharacters("20€");
			      xMLStreamWriter.writeEndElement();
		         
		         xMLStreamWriter.writeEndElement();//cierro libro
		         xMLStreamWriter.writeEndElement();//cierro libros   
		         
		         xMLStreamWriter.writeEndDocument();

		         xMLStreamWriter.flush();
		         xMLStreamWriter.close();

		         

		      } catch (XMLStreamException e) {
		         e.printStackTrace();
		      } catch (IOException e) {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		      }
		   }
		}

