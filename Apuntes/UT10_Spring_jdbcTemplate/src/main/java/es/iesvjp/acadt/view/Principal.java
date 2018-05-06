package es.iesvjp.acadt.view;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import es.iesvjp.acadt.dao.JdbcEstudianteDao;
import es.iesvjp.acadt.model.Estudiante;

public class Principal {
	
	
public static void main(String[] args) {
		
  ApplicationContext context = new ClassPathXmlApplicationContext ("contexto-beans.xml");
		
   JdbcEstudianteDao JdbcEstudiante =(JdbcEstudianteDao) context.getBean("JdbcEstudianteDao");
		 
   Estudiante estudiante = new Estudiante("Roberto",18);//insertar un nuevo estudiante
   Estudiante estudiante2 = new Estudiante(1,"Sergio", 30);//actualizar un estudiante ya existente cuyo Id es 1
	      
   System.out.println("------Alta de registros--------" );   
   JdbcEstudiante.insertEstudiante(estudiante);
	      
   System.out.println("------Actualización de registros--------" );   
   JdbcEstudiante.updateEstudiante(estudiante2);
   
   System.out.println("------Listado de registros--------" );
   List<Estudiante> students = JdbcEstudiante.getAll();
	  for (Estudiante record : students) {
	      System.out.print("ID : " + record.getId() );
	      System.out.print(", Nombre : " + record.getNombre() );
	      System.out.println(", Edad : " + record.getEdad() );
	      }
	}

}

