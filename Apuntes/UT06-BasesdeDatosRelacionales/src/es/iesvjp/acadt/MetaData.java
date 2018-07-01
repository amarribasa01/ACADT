package es.iesvjp.acadt;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class MetaData {
public static void main(String[] args) 
{ 
	//infoTabla() ;
	//InfoBDSQLite();
	//GetClaves() ;
	//GetClaves();
	//GetColumnasInfo();
	//GetColumnasInfo();
	//InfoBDOracle();
	GetClavesAjenas();
	//LocalDate local=LocalDate.now();
	//System.out.println(local);
} //fin de main 


public  static void GetColumnasInfo() 
{
	try{
	    //Cargar el driver
		//Class.forName("com.mysql.jdbc.Driver"); 
		//Establecemos la conexión con la BD
		//Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/acadt","root", "");
		// Cargar el driver
		Class.forName("org.sqlite.JDBC");
		// Establecemos la conexión con la BD
		Connection conexion = DriverManager.getConnection("jdbc:sqlite:C:\\AD\\UT3\\SQLite\\ejemplo.db");
		
		Statement st= conexion.createStatement();
		ResultSet rs= st.executeQuery("SELECT * FROM DEPARTAMENTOS");
		ResultSetMetaData rsmd= rs.getMetaData();
		int nColumnas=rsmd.getColumnCount();
		String nula;
		System.out.println("Número de columnas recuperadas: "+ nColumnas);
		
		
	 for (int i = 1; i <= nColumnas; i++) {
		System.out.println("Columna " +i+":");
		System.out.println(" Nombre : " +rsmd.getColumnName(i));
		System.out.println(" Tipo : " + rsmd.getColumnTypeName(i)); 
		if (rsmd.isNullable(i)==0) nula="NO"; else nula ="SI";
		System.out.println("¿Puede ser nula? : " + nula);
		System.out.println(" Máximo ancho de la columna: "+ rsmd.getColumnDisplaySize(i));

		}
		

		
	} catch (ClassNotFoundException en) {en.printStackTrace();
	} catch (SQLException e) {e.printStackTrace();}
	
	
}
public  static void GetClavesAjenas() 
{
	try{
	    //Cargar el driver
		Class.forName("com.mysql.jdbc.Driver"); 
//		//Establecemos la conexión con la BD
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/acadt","root", "");
		
//		Class.forName("org.sqlite.JDBC"); 
//		//Establecemos la conexión con la BD
//		Connection conexion = DriverManager.getConnection("jdbc:sqlite:C:\\AD\\UT3\\SQLite\\ejemplo.db");
		DatabaseMetaData dbmd = conexion.getMetaData();//Creamos objeto
		ResultSet fk=null;
		
		System.out.println("CLAVES REFERENCIADAS DE LA TABLA DEPARTAMENTO:");
		System.out.println("===================================");
		
		fk = dbmd.getExportedKeys(null, "acadt", "departamentos");
		while (fk.next()) {
			String fk_name =fk.getString("FKCOLUMN_NAME");
			String pk_name =fk.getString("PKCOLUMN_NAME");
			String pk_tablename =fk.getString("PKTABLE_NAME");
			String fk_tablename =fk.getString("FKTABLE_NAME");
			System.out.println("Tabla PK: "+ pk_tablename+ ", Clave Primaria: "+ pk_name); 
			System.out.println ("Tabla FK: "+ fk_tablename+ ", Clave Ajena: " + fk_name);

		}
		

		
	} catch (ClassNotFoundException en) {en.printStackTrace();
	} catch (SQLException e) {e.printStackTrace();}
	
	
}
public  static void GetClaves() 
{
	try{
	    //Cargar el driver
		Class.forName("com.mysql.jdbc.Driver"); 
		//Establecemos la conexión con la BD
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/acadt","root", "");
		DatabaseMetaData dbmd = conexion.getMetaData();//Creamos objeto
		ResultSet pk=null;
		
		System.out.println("CLAVES PRIMARIAS DE LA TABLA DEPARTAMENTO:");
		System.out.println("===================================");
		
		pk = dbmd.getPrimaryKeys(null, "acadt", "empleados");
		String pkDep="", separador=""; 
		while (pk.next()) {
		pkDep = pkDep + separador + pk.getString("COLUMN_NAME");//getString(4) 
		separador="+";
		}
		System.out.println("Clave Primaria: " + pkDep);

		
	} catch (ClassNotFoundException en) {en.printStackTrace();
	} catch (SQLException e) {e.printStackTrace();}
	
	
}
public  static void infoTabla() 
{
	try{
	    //Cargar el driver
	Class.forName("com.mysql.jdbc.Driver"); 
//		//Establecemos la conexión con la BD
	Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/acadt","root", "");
		
		
		DatabaseMetaData dbmd = conexion.getMetaData();//Creamos objeto
		ResultSet columnas=null;
		
		System.out.println("COLUMNAS TABLA DEPARTAMENTOS:");
		System.out.println("===================================");
		
		columnas = dbmd.getColumns(null, "acadt", "departamentos", null);
		while (columnas.next()) {
			String nombreCol = columnas.getString("COLUMN_NAME");//getString(4)
			String tipoCol = columnas.getString("TYPE_NAME"); //getString(6)
			String tamCol = columnas.getString("COLUMN_SIZE");//getString(7)
			String nula = columnas.getString("IS_NULLABLE"); //getString(18)
			System.out.println("Columna: " + nombreCol + ", Tipo: " + tipoCol + ", Tamaño: " + tamCol +", ¿Puede ser Nula:? " + nula);
		}
		
	} catch (ClassNotFoundException en) {en.printStackTrace();
	} catch (SQLException e) {e.printStackTrace();}
	
	
}
public void InfoBD(){
	try
	{
	//Cargar el driver
	Class.forName("com.mysql.jdbc.Driver"); 
	//Establecemos la conexión con la BD
	Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/acadt","root", "");
	DatabaseMetaData dbmd = conexion.getMetaData();//Creamos objeto
	ResultSet rs = null;
	String nombre = dbmd.getDatabaseProductName();
	String driver = dbmd.getDriverName ();
	String url = dbmd.getURL();
	String usuario = dbmd.getUserName() ;
	
	System.out.println("INFORMACIÓN SOBRE LA BASE DE DATOS:");
	System.out.println("===================================");
	System.out.println("Nombre : " + nombre );
	System.out.println("Driver : " + driver );
	System.out.println("URL : " + url );
	System.out.println("Usuario: " + usuario );
	//Obtener información de las tablas y vistas que hay 
	//rs = dbmd.getTables(null, "acadt", null, null);
	String[] tipos = {"TABLE"};
	rs = dbmd.getTables(null, "acadt", null, tipos);

	while (rs.next()) {
	/*String catalogo = rs.getString (1); //columna 1 que devuelve ResulSet
	String esquema = rs.getString(2); //columna 2
	String tabla = rs.getString(3); //columna 3
	String tipo = rs.getString (4);	//columna 4*/
	
	String	catalogo=	rs.getString("TABLE_CAT");
	String	esquema	=	rs.getString("TABLE_SCHEM");
	String	tabla	=	rs.getString("TABLE_NAME");
	String	tipo	=	rs.getString("TABLE TYPE");

	System.out.println(tipo + " - Catalogo: " + catalogo + ", Esquema : " + esquema + ", Nombre : " + tabla);
	}
	conexion.close(); //Cerrar conexión
	} catch (ClassNotFoundException en) {en.printStackTrace();
	} catch (SQLException e) {e.printStackTrace();}
}

public static void InfoBDOracle(){
	try
	{
	//Cargar el driver
	Class.forName("oracle.jdbc.OracleDriver"); 
	//Establecemos la conexión con la BD
	Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:acadt/12345@//localhost/XE");
	DatabaseMetaData dbmd = conexion.getMetaData();//Creamos objeto
	ResultSet rs = null;
	String nombre = dbmd.getDatabaseProductName();
	String driver = dbmd.getDriverName ();
	String url = dbmd.getURL();
	String usuario = dbmd.getUserName() ;
	
	System.out.println("INFORMACIÓN SOBRE LA BASE DE DATOS:");
	System.out.println("===================================");
	System.out.println("Nombre : " + nombre );
	System.out.println("Driver : " + driver );
	System.out.println("URL : " + url );
	System.out.println("Usuario: " + usuario );
	//Obtener información de las tablas y vistas que hay 
	rs = dbmd.getTables(null, "ACADT", null, null);
	//String[] tipos = {"TABLE"};
	//rs = dbmd.getTables(null, "acadt", null, tipos);

	while (rs.next()) {
	String catalogo = rs.getString (1); //columna 1 que devuelve ResulSet
	String esquema = rs.getString(2); //columna 2
	String tabla = rs.getString(3); //columna 3
	String tipo = rs.getString (4);	//columna 4*/
	
	

	System.out.println(tipo + " - Catalogo: " + catalogo + ", Esquema : " + esquema + ", Nombre : " + tabla);
	}
	conexion.close(); //Cerrar conexión
	} catch (ClassNotFoundException en) {en.printStackTrace();
	} catch (SQLException e) {e.printStackTrace();}
}


public static void InfoBDSQLite(){
	try
	{
	//Cargar el driver
	Class.forName("org.sqlite.JDBC"); 
	//Establecemos la conexión con la BD
	Connection conexion = DriverManager.getConnection("jdbc:sqlite:C:\\AD\\UT3\\SQLite\\ejemplo.db");
	DatabaseMetaData dbmd = conexion.getMetaData();//Creamos objeto
	ResultSet rs = null;
	String nombre = dbmd.getDatabaseProductName();
	String driver = dbmd.getDriverName ();
	String url = dbmd.getURL();
	String usuario = dbmd.getUserName() ;
	
	System.out.println("INFORMACIÓN SOBRE LA BASE DE DATOS:");
	System.out.println("===================================");
	System.out.println("Nombre : " + nombre );
	System.out.println("Driver : " + driver );
	System.out.println("URL : " + url );
	System.out.println("Usuario: " + usuario );
	//Obtener información de las tablas y vistas que hay 
	rs = dbmd.getTables(null, null, null, null);
	//String[] tipos = {"TABLE"};
	//rs = dbmd.getTables(null, "acadt", null, tipos);

	while (rs.next()) {
	String catalogo = rs.getString (1); //columna 1 que devuelve ResulSet
	String esquema = rs.getString(2); //columna 2
	String tabla = rs.getString(3); //columna 3
	String tipo = rs.getString (4);	//columna 4*/
	
	

	System.out.println(tipo + " - Catalogo: " + catalogo + ", Esquema : " + esquema + ", Nombre : " + tabla);
	}
	conexion.close(); //Cerrar conexión
	} catch (ClassNotFoundException en) {en.printStackTrace();
	} catch (SQLException e) {e.printStackTrace();}
}
	}//fin de la clase
	



