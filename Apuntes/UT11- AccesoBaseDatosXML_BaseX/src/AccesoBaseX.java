import javax.xml.xquery.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

import net.xqj.basex.BaseXXQDataSource;

public class AccesoBaseX
{
  public static void main(String[] args) throws XQException
  {
	  //ConsultaBaseX();
	 // GetEmpleadosByDepToXML(10);
	  //GetEmpleadosByDep(10);
	  ModificarPoblacion();
  }
  public static void GetEmpleadosByDepToXML(int dep)
  {
	  //definimos las siguientes propiedades para el XML de salida
	  Properties serializationProps = new java.util.Properties();
      serializationProps.setProperty("method", "xml");
	  serializationProps.setProperty("indent", "yes");
	  serializationProps.setProperty("encoding", "UTF-8");
	  serializationProps.setProperty("omit-xml-declaration", "no"); 
	  
	  try {
	    XQDataSource xqd = new BaseXXQDataSource();
	 	xqd.setProperty("serverName", "localhost");
		xqd.setProperty("port", "1984");
	    xqd.setProperty("databaseName", "ACADT");
	    xqd.setProperty("user", "admin");
	    xqd.setProperty("password", "admin");
	   
	    XQConnection xqc = xqd.getConnection();

	    XQExpression xqe = xqc.createExpression();
	    XQResultSequence xqr = xqe.executeQuery("<EMPLEADOS>{/EMPLEADOS/EMPLEADO[DEPT_NO="+dep+"]}</EMPLEADOS>");                                            
	   
	    xqr.writeSequence(new FileOutputStream("resultado2.xml"),serializationProps);
	   	 
	    xqc.close();
	    
	    } catch (XQException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }
  public static void GetEmpleadosByDep(int dep)
  {
	  
	  try {
	    XQDataSource xqd = new BaseXXQDataSource();
	 	xqd.setProperty("serverName", "localhost");
		xqd.setProperty("port", "1984");
	    xqd.setProperty("databaseName", "ACADT");
	    xqd.setProperty("user", "admin");
	    xqd.setProperty("password", "admin");
	   
	    
	    XQConnection xqc = xqd.getConnection();

	    XQExpression xqe = xqc.createExpression();
	    XQResultSequence xqr = xqe.executeQuery("/EMPLEADOS/EMPLEADO[DEPT_NO="+dep+"]");                                            
	   
	   
	   
	    while (xqr.next())
	    {
	    	
	    	System.out.println(xqr.getItemAsString(null));
	    }
	   	 
	    xqc.close();
	    
	    } catch (XQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }
	  
  
  
  public static void ModificarPoblacion()
  {
  try {   
	    XQDataSource xqd = new BaseXXQDataSource();
	 	xqd.setProperty("serverName", "localhost");
		xqd.setProperty("port", "1984");
	    xqd.setProperty("databaseName", "ACADT");
	    xqd.setProperty("user", "admin");
	    xqd.setProperty("password", "admin");
	   
	    
	    XQConnection xqc = xqd.getConnection();

	    XQExpression xqe = xqc.createExpression();
	    XQResultSequence xqr= xqe.executeQuery("for $poblacion in /personas/persona/poblacion "+
	    										"return "+
	    										"replace value of node $poblacion with 'Cáceres'");                                            
	   
	    System.out.println("Total: "+ xqr.count());
	   	xqc.close();
	   	   
	    
	    } catch (XQException e) {
	    	System.out.println("Error al actualizar la BD XML: "+ e.getMessage());
			
		}
  }
  
  
}
