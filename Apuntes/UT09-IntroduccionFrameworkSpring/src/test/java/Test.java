import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import es.iesvjp.acadt.Conductor;

public class Test {
	
	public static void main(String[] args) {
		//cargamos el contexto de la aplicación
		ApplicationContext appContext= new ClassPathXmlApplicationContext("contexto-bean.xml");
		//a través del contexto obtenemos los beans
		Conductor conductor= (Conductor) appContext.getBean("conductor");
		System.out.println(conductor.toString());
				
			
		}


}
