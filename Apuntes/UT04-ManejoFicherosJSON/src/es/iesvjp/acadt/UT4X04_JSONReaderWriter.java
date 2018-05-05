package es.iesvjp.acadt;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class UT4X04_JSONReaderWriter {

	public static void main(String[] args) {

		ArrayList<Empleado> empleados = new ArrayList();
		Empleado emp1 = new Empleado("Pepe", "Lopez", 20, Arrays.asList("Gerente", "Jefe de zona"));
		Empleado emp2 = new Empleado("Maria", "Gutierrez", 30, Arrays.asList("Jefa de RR.HH"));
		empleados.add(emp1);
		empleados.add(emp2);

		//JSONWriterFile(empleados);
		JSONReaderFile();

	}

	public static void JSONReaderFile() {
		InputStream fis;
		Gson gson = new Gson();

		try {

			fis = new FileInputStream("C:\\AD\\UT2\\empleados.json");
			JsonReader reader = new JsonReader(new InputStreamReader(fis, "UTF-8"));

			// Leemos el fichero en stream
			reader.beginArray();
			while (reader.hasNext()) {
				
				// Leemos la información utilizando el modelo Empleado
				Empleado emp = gson.fromJson(reader, Empleado.class);

				System.out.println(emp.toString());

			}

			reader.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void JSONWriterFile(ArrayList<Empleado> empleados) {
		JsonWriter writer;

		try {
			writer = new JsonWriter(new FileWriter("C:\\AD\\UT2\\workers.json"));

			Iterator<Empleado> itEmp = empleados.iterator();

			writer.beginArray();// array de empleados
          
			while (itEmp.hasNext()) {
				Empleado emp = itEmp.next();

				writer.beginObject(); // objeto empleado
				writer.name("-nombre").value(emp.getNombre());
				writer.name("apellidos").value(emp.getApellidos());
				writer.name("edad").value(emp.getEdad());
				writer.name("puestos");

				writer.beginArray();// array de puestos
				List<String> puestos = emp.getPuestos();
				for (int i = 0; i < puestos.size(); i++) {
					writer.value(puestos.get(i));
				}

				writer.endArray();// fin array de puestos
				writer.endObject(); // fin objeto empleado
			}

			writer.endArray();// fin array empleados
			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
