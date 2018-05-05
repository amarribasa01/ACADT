package utilidades;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
 
/**
 * @author Ana Arribas
 */
public class JPAUtility {
	
	//nos declaramos una variable de la Interface EntityManagerFactory
	private static final EntityManagerFactory emFactory;
	static {
	//instanciamos la interface emFactory mediante la clase Persistence, esta última recibe como parámetro 
	//el nombre de la unidad de persistencia que definimos en el archivo persistence.xml
		   emFactory = Persistence.createEntityManagerFactory("EjemploJPA");
	}
	//obtenemos una instancia de EntityManager para ser retornada
	public static EntityManager getEntityManager(){
		return emFactory.createEntityManager();
	}
	public static void close(){
		emFactory.close();
	}
	    
	    
	}


