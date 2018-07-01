package es.iesvjp.acadt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;


public class ManipulacionDatos {

	public static void main(String[] args)
	{
		//InsertarDepartamentov2(70,"MANTENIMIENTO","BADAJOZ");
		ModificarSalariov2(10, 120);
	
		//InsertarEmpleado(int num, String apellido, String oficio, int dir, double salario, double comision, int dep) 
		//InsertarEmpleado(10,"gomez","becario",1,1200,20,40); 
		//InsertarEmpleadov2(50,"Agundez","becario",1,1200,20,40); 
		//ConsultarEmpleadosByDep(10);
	}
	public  static void ConsultarEmpleadosByDep(int dep) 
	{
		try{
		    //Cargar el driver
			Class.forName("com.mysql.jdbc.Driver"); 
			//Establecemos la conexión con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/acadt","root", "");
			
			//construir orden SELECT
			String sql= "SELECT apellido, salario FROM empleados WHERE dept_no = ? ORDER BY salario";
			
						
			PreparedStatement pst = conexion.prepareStatement(sql);
			pst.setInt(1,dep);
			
			
			ResultSet rs= pst.executeQuery();
			
						
			while (rs.next())
			{
				System.out.println("Empleado:"+ rs.getString("apellido") +" sueldo:"+ rs.getDouble("salario"));
			}
			
			rs.close(); //Cerrar ResultSet
			pst.close();	//	Cerrar PreparedStatement
			conexion.close();	//Cerrar conexión*/

							
		} catch (ClassNotFoundException en) {en.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();}
		
		
	}
	public  static void ModificarSalariov2(int dep, double subida) 
	{
		try{
		    //Cargar el driver
			Class.forName("com.mysql.jdbc.Driver"); 
			//Establecemos la conexión con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/acadt","root", "");
			
			//construir orden UPDATE
			
			String sql= "update empleados set salario=salario+ ? where dept_no=?";
		
			PreparedStatement pst = conexion.prepareStatement(sql);
			
			pst.setInt(2, dep);
		    pst.setDouble(1, subida);
			
		
			int filas = pst.executeUpdate() ;
						
			System.out.println("Filas afectadas: "+filas);
			pst.close ();	//	Cerrar PreparedStatement
			conexion.close();	//Cerrar conexión*/

							
		} catch (ClassNotFoundException en) {en.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();}
		
		
	}
	public  static void InsertarEmpleado(int num, String apellido, String oficio, int dir, double salario, double comision, int dep) 
	{
		try{
		    //Cargar el driver
			Class.forName("com.mysql.jdbc.Driver"); 
			//Establecemos la conexión con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/acadt","root", "");
			
			//construir orden INSERT
			String sql= "INSERT INTO empleados VALUES (?,?,?,?,?,?,?,?)";
						
			PreparedStatement pst = conexion.prepareStatement(sql);
			pst.setInt(1,num);
			pst.setString(2, apellido);
			pst.setString(3, oficio);
			pst.setInt(4,dir);
			LocalDate fecha= LocalDate.now();
			java.sql.Date fechaSQL = java.sql.Date.valueOf(fecha);
			pst.setDate(5,fechaSQL);
			pst.setDouble(6,salario);
			pst.setDouble(7,comision);
			pst.setInt(8,dep);
			
			int filas = pst.executeUpdate();
			
			System.out.println("Filas afectadas: "+filas);
			pst.close();	//	Cerrar Statement
			conexion.close();	//Cerrar conexión*/

							
		} catch (ClassNotFoundException en) {en.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();}
		
	}
	
	public  static void InsertarEmpleadov2(int num, String apellido, String oficio, int dir, double salario, double comision, int dep) 
	{
		try{
		    //Cargar el driver
			Class.forName("com.mysql.jdbc.Driver"); 
			//Establecemos la conexión con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/acadt","root", "");
			
			//construir orden INSERT
//			String sql= "INSERT INTO empleados VALUES ("+num+",'"+apellido+"','"+oficio+"',"+dir+",sysdate(),"+salario+","
//			+comision+","+dep+")";
			
			LocalDate fecha= LocalDate.now();
			
			String sql= "INSERT INTO empleados VALUES ("+num+",'"+apellido+"','"+oficio+"',"+dir+",'"+fecha+"',"+salario+","
					+comision+","+dep+")";
						
			Statement pst = conexion.createStatement();
			System.out.println(sql);
			
			int filas = pst.executeUpdate(sql);
			
			System.out.println("Filas afectadas: "+filas);
			pst.close();	//	Cerrar Statement
			conexion.close();	//Cerrar conexión*/

							
		} catch (ClassNotFoundException en) {en.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();}
		
	}
	
	public  static void InsertarDepartamentov2(int dep, String nombre, String localidad) 
	{
		try{
		    //Cargar el driver
			Class.forName("com.mysql.jdbc.Driver"); 
			//Establecemos la conexión con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/acadt","root", "");
			
			//construir orden INSERT
			String sql= "INSERT INTO departamentos VALUES (?,?,?)";
						
			PreparedStatement pst = conexion.prepareStatement(sql);
			pst.setInt(1,dep);
			pst.setString(2, nombre);
			pst.setString(3, localidad);
			
			int filas = pst.executeUpdate();
			
			System.out.println("Filas afectadas: "+filas);
			pst.close();	//	Cerrar Statement
			conexion.close();	//Cerrar conexión*/

							
		} catch (ClassNotFoundException en) {en.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();}
		
	}
	public  static void InsertarDepartamento(int dep, String nombre, String localidad) 
	{
		try{
		    //Cargar el driver
			Class.forName("com.mysql.jdbc.Driver"); 
			//Establecemos la conexión con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/acadt","root", "");
			
			//construir orden INSERT
			String sql= "INSERT INTO departamentos VALUES (" + dep + ", '" + nombre + "','"+localidad+ "')";
			//System.out.println(sql);
			
			Statement st = conexion.createStatement();
			int filas = st.executeUpdate(sql);
			
			System.out.println("Filas afectadas: "+filas);
			st.close ();	//	Cerrar Statement
			conexion.close();	//Cerrar conexión*/

							
		} catch (ClassNotFoundException en) {en.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();}
		
		
	}

	
	public  static void ModificarSalario(int dep, double subida) 
	{
		try{
		    //Cargar el driver
			Class.forName("com.mysql.jdbc.Driver"); 
			//Establecemos la conexión con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/acadt","root", "");
			
			//construir orden UPDATE
			
			String sql= "UPDATE empleados SET salario=salario + " + subida + " WHERE dept_no = " +dep;
			System.out.println(sql);
			Statement st = conexion.createStatement();
			int filas = st.executeUpdate(sql) ;
						
			System.out.println("Filas afectadas: "+filas);
			st.close ();	//	Cerrar Statement
			conexion.close();	//Cerrar conexión*/

							
		} catch (ClassNotFoundException en) {en.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();}
		
		
	}
}
