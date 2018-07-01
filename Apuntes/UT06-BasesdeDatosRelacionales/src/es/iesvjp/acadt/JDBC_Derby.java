package es.iesvjp.acadt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Derby {

	
		public static void main(String[] args) { 
			try {
				
				// Cargar el driver
				Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
				// Establecemos la conexión con la BD
				Connection conexion = DriverManager.getConnection("jdbc:derby:C:\\AD\\UT2\\DERBY\\ejemplo");
				// Preparamos la consulta
				
								
				Statement st = conexion.createStatement();
				ResultSet rs = st.executeQuery ("SELECT * FROM departamentos");
				// Recorremos el resultado para visualizar cada fila
				// Se hace un bucle mientras haya registros, se van visualizando
				while (rs.next ())
				{
					System.out.println (rs.getInt(1) + " " + rs.getString(2)+ " " + rs.getString (3));
				}
				rs.close();// Cerrar ResultSet 
				st.close();// Cerrar Statement 
				conexion.close();//Cerrar conexión
				} catch (ClassNotFoundException en) {en.printStackTrace();} 
			      catch (SQLException e) {e.printStackTrace();}
				} //fin de main 
	}





