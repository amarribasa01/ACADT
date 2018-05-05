package es.iesvjp.acadt;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.AcroFields.FieldPosition;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.Image;
import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;

/**
 * @author Ana Arribas
 *
 */
public class UT5X01_RellenarPdf {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		rellenar_pdf();
	}

	public static void rellenar_pdf() {
		try {
			// para leer el formulario base
			PdfReader reader = new PdfReader("src/archivos/ficha-alumno.pdf");
			// para rellenar los campos del formulario
			PdfStamper stamper;

			String fecha = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			// nombraremos cada fichero con el formato ficha-alumno-fecha para poder generar
			// nuevos y no machacarlos
			String nombfichero = "ficha-alumno-" + fecha + ".pdf";
			// Instanciamos el objeto stamper pasándole el formulario base y el nuevo que
			// nos vamos a crear
			stamper = new PdfStamper(reader, new FileOutputStream("src/fichas/"+nombfichero));

			// obtenemos todos los campos del formulario
			AcroFields form = stamper.getAcroFields();

			// rellenamos a mano los campos (normalmente se obtendrá de un fichero o BD)
			form.setField("NOMBRE", "Antonio");
			form.setField("APELLIDOS", "Pérez Gómez");
			form.setField("DIRECCION", "Avda. Extremadura, 24");
			form.setField("TELEFONO", "927456754");
			form.setField("EMAIL", "antoniopg@gmail.com");
			form.setField("OBSERVACIONES", "Tiene pendiente Programación de 1º DAM");

			// la foto se trata como un campo especial
			Image foto = Image.getInstance("src/archivos/alumno.jpg");
			// obtenemos la posición del campo foto y con ello nos creamos un rectángulo
			FieldPosition posicion = form.getFieldPositions("FOTO").get(0);
			Rectangle rect = posicion.position;
			// escalamos la foto al tamaño del rectángulo
			foto.scaleAbsolute(rect.getWidth(), rect.getHeight());
			foto.setAbsolutePosition(rect.getLeft(), rect.getBottom());
			// añadimos la foto
			PdfContentByte overContent = stamper.getOverContent(posicion.page);
			overContent.addImage(foto);

			// cerramos el stamper y el reader
			stamper.close();
			reader.close();

		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		} catch (DocumentException de) {
			System.out.println(de.getMessage());
		}
	}

}
