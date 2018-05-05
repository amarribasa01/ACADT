package es.iesvjp.acadt;


import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class UT4X02_JavaToJSONPretty {

	public static void main(String[] args) {

		ObjectToJSON();

	}
	
	public static void ArrayToJSON()
	{
		ArrayList<Empleado> empleados= new ArrayList();
		Empleado emp1 = new Empleado("Pepe", "Lopez", 20, Arrays.asList("Gerente", "Jefe de zona"));
		Empleado emp2 = new Empleado("Maria", "Gutierrez", 30, Arrays.asList("Jefa de RR.HH"));
		empleados.add(emp1);
		empleados.add(emp2);
		
		Gson prettyGson= new GsonBuilder().setPrettyPrinting().create();
		
		System.out.println(prettyGson.toJson(empleados));
			
	}
	
	public static void ObjectToJSON()
	{
		Empleado emp1 = new Empleado("Pepe", "Lopez", 20, Arrays.asList("Gerente", "Jefe de zona"));
		//si queremos mostrar el nombre del objeto en el JSON
		SerializeEmpleado s_empleado= new SerializeEmpleado(emp1);
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();

		System.out.println(prettyGson.toJson(s_empleado));
		
	}
	
  

}
