package es.iesvjp.acadt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
public class CallProcedure {

	public CallProcedure() {
		
		
	}
	public static void main(String[] args)
	{
		
		//SubidaSalarioEmpleados(20,100) ;
		//SelectNombreDepart(10);
		//SelectTodosDepart();
		GetDepartamentosMySQL();
		//SelectNombreDepart(10);
	}
	
	
	public  static void SubidaSalarioEmpleados(int dep,double subida) 
	{
		try{
		    //Cargar el driver
			Class.forName("com.mysql.jdbc.Driver"); 
			//Establecemos la conexión con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/acadt","root", "");
			
			String sql= "{call subida_sal(?, ?)}";
			// Preparamos la llamada
			CallableStatement cst = conexion.prepareCall(sql);
			// Damos valor a los argumentos
			cst.setInt(1,dep);	// primer argumento-dep
			cst.setDouble(2,subida); // segundo argumento-subida
			cst.executeUpdate(); //ejecutar el procedimiento 
			System.out.println ("Subida realizada....");
						
			
			cst.close();	//	Cerrar CallableStatement
			conexion.close();	//Cerrar conexión*/

							
		} catch (ClassNotFoundException en) {en.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();}
		
		
	}
	
	public  static void GetDepartamentosMySQL() 
	{
		try{
		    //Cargar el driver
			Class.forName("com.mysql.jdbc.Driver"); 
			//Establecemos la conexión con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/acadt","root", "");
			
			String sql= "{call getdepartamentos()}";
			// Preparamos la llamada
			
			    
				
			CallableStatement cst = conexion.prepareCall(sql);
			
//			CallableStatement cst = conexion.prepareCall(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
//				    ResultSet.CONCUR_READ_ONLY);
			
			ResultSet rs=cst.executeQuery(); //ejecutar el procedimiento 
			while (rs.next())
			{
				System.out.println(rs.getString(2));
				
			}
			
						
			
			cst.close();	//	Cerrar CallableStatement
			conexion.close();	//Cerrar conexión*/

							
		} catch (ClassNotFoundException en) {en.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();}
		
		
	}
	
	public static void SelectNombreDepart(int dep)
	{
		try {
			// Cargar el driver
			Class.forName("oracle.jdbc.OracleDriver");
			// Establecemos la conexión con la BD
			Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:acadt/12345@//localhost/XE");
			
			// Preparamos la consulta
			String sql="{?=call localidad_depart(?,?)}";
			CallableStatement cst = conexion.prepareCall(sql);
			
			
			cst.registerOutParameter(1, Types.VARCHAR);//devuelve el nombre del departamento
			cst.setInt(2, dep);//recibe el número de departamento
			cst.registerOutParameter(3, Types.VARCHAR);//devuelve la localidad
			
			cst.executeQuery(); // ejecutamos el procedimiento almacenado
			
			System.out.println("Nombre de departamento: "+ cst.getString(1)+ " localidad: "+cst.getString(3));
			
			cst.close();// Cerrar CallableStatement
			conexion.close();//Cerrar conexión
			
			} catch (ClassNotFoundException en) {en.printStackTrace();} 
		      catch (SQLException e) {e.printStackTrace();}
	}
	
	public static void SelectTodosDepart()
	{
		try {
			// Cargar el driver
			Class.forName("oracle.jdbc.OracleDriver");
			// Establecemos la conexión con la BD
			Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:acadt/12345@//localhost/XE");
			
			// Preparamos la consulta
			String sql="{call GetDepartamentos(?)}";
			OracleCallableStatement ocst = (OracleCallableStatement)conexion.prepareCall(sql);
			
			
			ocst.registerOutParameter(1, OracleTypes.CURSOR);//devuelve datos del departamento
					
			ocst.execute(); // ejecutamos el procedimiento almacenado
			ResultSet rs = ocst.getCursor(1);
			
			while (rs.next())
			{
			
			System.out.println("Nombre de departamento: "+ rs.getString("dnombre")+ " localidad: "+rs.getString("loc"));
			}
			
			rs.close(); //cerramos el ResulSet
			ocst.close();// Cerrar OracleCallableStatement
			conexion.close();//Cerrar conexión
			
			} catch (ClassNotFoundException en) {en.printStackTrace();} 
		      catch (SQLException e) {e.printStackTrace();}
	}

}
