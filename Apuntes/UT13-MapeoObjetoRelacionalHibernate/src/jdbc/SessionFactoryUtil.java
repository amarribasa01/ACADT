package jdbc;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil {
	 private static SessionFactory sessionFactory;
	    
	    public static SessionFactory getSessionFactory() {
	    	
	    try{
	        if (sessionFactory == null) {
	            // cargamos la configuración
	            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
	               
	            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
	            applySettings(configuration.getProperties());
	           //nos creamos una sesión con la configuración establecida
	            sessionFactory = configuration.buildSessionFactory(builder.build());

	                    
	        }
	        
	    } catch (HibernateException he) {  System.err.println("Error creando Session: " + he);}
	         
	        return sessionFactory;
	    }
	    
	   
	}