package es.iesvjp.acadt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectMySQLOracle {

	public static void main(String[] args) {
		//SelectOracle();
		//SelectMySQL();
		SelectDepartamentos("oracle");

	}

	public static void SelectDepartamentos(String bd) {
		try {
			Connection conexion = Conexion.GetConexion(bd);
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM DEPARTAMENTOS");
			// Recorremos el resultado para visualizar cada fila
			// Se hace un bucle mientras haya registros, se van visualizando

			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}
			rs.close();// Cerrar ResultSet
			st.close();// Cerrar Statement
			conexion.close();// Cerrar conexión
		} catch (SQLException e) {System.out.println("Error SQL: "+ e.getMessage());
			
		}

	}

	public static void SelectOracle() {
		try {
			// Cargar el driver
			Class.forName("oracle.jdbc.OracleDriver");
			// Establecemos la conexión con la BD
			Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:acadt/12345@//localhost/XE");

			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM DEPARTAMENTOS");
			// Recorremos el resultado para visualizar cada fila
			// Se hace un bucle mientras haya registros, se van visualizando

			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}
			rs.close();// Cerrar ResultSet
			st.close();// Cerrar Statement
			conexion.close();// Cerrar conexión
		} catch (ClassNotFoundException en) {
			en.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void SelectMySQL() {
		try {
			// Cargar el driver
			Class.forName("com.mysql.jdbc.Driver");
			// Establecemos la conexión con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/acadt", "root", "");
			// Preparamos la consulta
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM departamentos");
			// Recorremos el resultado para visualizar cada fila
			// Se hace un bucle mientras haya registros, se van visualizando
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}
			rs.close();// Cerrar ResultSet
			st.close();// Cerrar Statement
			conexion.close();// Cerrar conexión
		} catch (ClassNotFoundException en) {
			en.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
