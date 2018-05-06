package basededatos_spring_jdbc;

import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import es.iesvjp.acadt.dao.JdbcEstudianteDao;
import es.iesvjp.acadt.model.Estudiante;

public class EstudianteTest {

	public static void main(String[] args) {
		
		ApplicationContext context = 
	             new ClassPathXmlApplicationContext("beans.xml");
		
		 JdbcEstudianteDao JdbcEstudiante =(JdbcEstudianteDao)context.getBean("JdbcEstudianteDao");
		 
		 Estudiante estudiante = new Estudiante(40,"pepe", 21);
		 Estudiante estudiante2 = new Estudiante("maria", 35);
		 estudiante2.setId(40);
	      
	      System.out.println("------Alta de registros--------" );
	     //JdbcEstudiante.insertEstudiante(estudiante);
	    
	     
	      
	     //JdbcEstudiante.updateEstudiante(estudiante2);

//	      System.out.println("------Listado de registros--------" );
//	      List<Estudiante> students = JdbcEstudiante.getAll();
//	      for (Estudiante record : students) {
//	         System.out.print("ID : " + record.getId() );
//	         System.out.print(", Nombre : " + record.getNombre() );
//	         System.out.println(", Edad : " + record.getEdad() );
//	      }
	try{     
	      System.out.println("------Listado de registros--------" );
	      Estudiante student = JdbcEstudiante.findById(40);
	     
	         System.out.print("ID : " + student.getId() );
	         System.out.print(", Nombre : " + student.getNombre() );
	         System.out.println(", Edad : " + student.getEdad() );
	      
	}catch(EmptyResultDataAccessException ex)
	{System.out.println("No se ha encontrado ningún registro");
	}
	
		
	}
	

}
