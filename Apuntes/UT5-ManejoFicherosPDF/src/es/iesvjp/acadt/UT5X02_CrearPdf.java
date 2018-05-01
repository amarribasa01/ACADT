package es.iesvjp.acadt;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.*;

public class UT5X02_CrearPdf {
	
	public static void main (String args[])
	{
	try{
	// Se crea el documento
	Document documento = new Document();
	// Se crea el OutputStream para el fichero donde queremos dejar el pdf.
	FileOutputStream ficheroPdf = new FileOutputStream("src/fichas/fichero.pdf");
			
	// Se asocia el documento al OutputStream y se indica que el espaciado entre
	// lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
	PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
	// Se abre el documento
	
	documento.open();
	
	documento.add(new Paragraph("Esto es el primer párrafo, normalito"));
	
	documento.add(new Paragraph("Este es el segundo párrafo y le hemos cambiado la fuente",
			FontFactory.getFont("arial", // fuente
			22, // tamaño
			Font.ITALIC, // estilo
			BaseColor.GREEN))); // color
	
	//Añadimos una imagen
	Image foto = Image.getInstance("src/archivos/java.png");
	foto.scaleToFit(100, 100);
	foto.setAlignment(Image.ALIGN_MIDDLE);
	documento.add(foto);
	

	//Añadimos una tabla de 12 celdas
	PdfPTable tabla = new PdfPTable(4);
	for (int i = 0; i < 12; i++)
	{
	tabla.addCell("celda " + i);
	}
	documento.add(tabla);

	//cerramos el documento
	documento.close();
	
	}catch (DocumentException de){System.out.println(de.getMessage());
	}catch (FileNotFoundException fnfe){System.out.println(fnfe.getMessage());
	}catch (IOException ioe){System.out.println(ioe.getMessage());}
	}
	
	}
