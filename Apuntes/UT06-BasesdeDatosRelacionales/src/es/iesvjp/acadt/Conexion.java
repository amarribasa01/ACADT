package es.iesvjp.acadt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {

	public static Connection GetConexion(String bd) {
		Properties propiedades = new Properties();
		FileInputStream fis;
		Connection conexion = null;
		String database = bd.toLowerCase();
		

		try {
			fis = new FileInputStream("configuracion.properties");

			// cargamos el archivo de propiedades
			propiedades.load(fis);

			// obtenemos las propiedades y las imprimimos
			//System.out.println(propiedades.getProperty("driver-" + database));
			//System.out.println(propiedades.getProperty("conexion-" + database));

			switch (database) {
			case "oracle":
				// Cargar el driver
				Class.forName(propiedades.getProperty("driver-" + database));
				// Establecemos la conexión con la BD
				conexion = DriverManager.getConnection(propiedades.getProperty("conexion-" + database));
				break;

			case "mysql":
				// Cargar el driver
				Class.forName(propiedades.getProperty("driver-" + database));
				// Establecemos la conexión con la BD
				conexion = DriverManager.getConnection(propiedades.getProperty("conexion-" + database),
						propiedades.getProperty("usuario-" + database), "");

				break;

			default:
				System.out.println("No existe la base de datos");
				break;
			}

		} catch (IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return conexion;

	}

	public static void main(String[] args) {
		GetConexion("mysq");
	}
}
