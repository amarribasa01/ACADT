package ejercicio02;

import java.time.LocalDate;
import java.util.Scanner;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;

/**
 * @author Ana Arribas
 *
 */

public class AccesoDB4o {

	static String BDPersonas = "BDPersonas.yap";

	/**
	 * @return devuelve un objeto ObjectContainer
	 */
	public static ObjectContainer getConexion() {
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		// cambiamos el nivel de actualización a 5
		config.common().updateDepth(5);
		config.common().activationDepth(5);

		return Db4oEmbedded.openFile(config, BDPersonas);
	}

	/**
	 * @param per: Persona a insertar sin el campo Dirección
	 */
	public static void InsertPersona(Persona per) {
		ObjectContainer db = getConexion();
		db.store(per);// almacenamos la persona
		db.commit();
		System.out.println("Persona introducida correctamente.");
		db.close();
	}

	/**
	 * @param dir Dirección a insertar
	 */
	public static void InsertDireccion(Direccion dir) {
		ObjectContainer db = getConexion();
		db.store(dir);// almacenamos la dirección
		db.commit();
		System.out.println("Direccion introducida correctamente.");
		db.close();
	}

	
	/**
	 * @param per: persona actualizar sus campos
	 */
	public static void UpdatePersona(Persona per) {
		ObjectContainer db = getConexion();
		ObjectSet<Persona> osper = db.queryByExample(per);

		if (osper.size() > 0) {
			Persona persona = osper.next();
			System.out.println("\nIntroduzca los nuevos datos de la Persona");
			//obtenemos los nuevos datos de la persona	
			Persona newper=pedirDatosPersona();
			//Actualizamos los datos
			persona.setNombre(newper.getNombre());
			persona.setApellidos(newper.getApellidos());
			persona.setFecha_nac(newper.getFecha_nac());
			persona.setEmail(newper.getEmail());
		
			System.out.println("Datos cambiados correctamente.");
			db.store(persona);
		} else {
			System.out.println("La persona no existe.");
		}
		db.commit();
		db.close();
	}

	
	/**
	 * @param per: Persona a la que se le va a asignar su Dirección
	 * @param dir: Dirección que se va asignar a la Persona
	 */
	public static void AddDireccionPersona(Persona per, Direccion dir) {
		ObjectContainer db = getConexion();
		
		ObjectSet<Persona> osper = db.queryByExample(per);
		ObjectSet<Direccion> osdir = db.queryByExample(dir);

		if (osper.size() > 0 && osdir.size() > 0) {
			Persona persona = osper.next();
			Direccion direccion = osdir.next();
			persona.setDir(direccion);
			db.store(persona);
			System.out.println("Direccion asignada correctamente.");
		} else {
			System.out.println("La persona o la dirección no existen.");
		}
		db.commit();
		db.close();
	}

	
	/**
	 * @param per: Persona a eliminar, su Dirección no se borrará
	 */
	public static void DeletePersona(Persona per) {
		ObjectContainer db = getConexion();
		ObjectSet<Persona> osper = db.queryByExample(per);

		if (osper.size() > 0) {
			Persona persona = osper.next();
			db.delete(persona);
			System.out.println("Persona borrada correctamente.");
		} else {
			System.out.println("La persona no existe.");
		}
		db.commit();
		db.close();
	}

	/**
	 * @param ciudad:Ciudad donde viven las Personas a buscar
	 */
	public static void GetPersonasByCiudad(String ciudad) {
		ObjectContainer db = getConexion();

		Direccion dir = new Direccion(null, null, ciudad, null);
		Persona per_query = new Persona(null, null, null, null, null, dir);

		ObjectSet<Persona> osper = db.queryByExample(per_query);

		if (osper.size() > 0) {
			while (osper.hasNext()) {
				System.out.println(osper.next());
			}
		} else {
			System.out.println("No vive ninguna persona en la Ciudad introducida");
		}
		db.close();
	}

	/**
	 * Mostramos todas las Personas existentes en la BD
	 */
	public static void GetAllPersonas() {
		ObjectContainer db = getConexion();

		ObjectSet<Persona> osper = db.queryByExample(Persona.class);

		while (osper.hasNext()) {
			System.out.println(osper.next());
		}
		db.close();
	}

	/**
	 * Mostramos todas las Direcciones existentes en la BD
	 */
	public static void GetAllDirecciones() {
		ObjectContainer db = getConexion();

		ObjectSet<Direccion> osdir = db.queryByExample(Direccion.class);

		while (osdir.hasNext()) {
			System.out.println(osdir.next());
		}
		db.close();
	}

	// Devuelve un objeto Persona con sus datos
	public static Persona pedirDatosPersona() {
		Persona per = new Persona();
		per.setNombre(pedirString("Introduce el NOMBRE"));
		per.setApellidos(pedirString("Introduce los APELLIDOS"));
		per.setFecha_nac(pedirfecha("Introduce los FECHA DE NACIMIENTO (yyyy-MM-dd)"));
		per.setTelefono(pedirEntero("Introduce el TELÉFONO"));
		per.setEmail(pedirString("Introduce el EMAIL"));

		return per;
	}
	// Devuelve un objeto Persona con los datos nombre y apellidos, para facilitar la busqueda
	public static Persona pedirDatosBusquedaPersona() {
		// A la hora de buscar una persona lo haremos únicamente por nombre y
		// apellidos
		Persona per = new Persona();
		per.setNombre(pedirString("Introduce el NOMBRE"));
		per.setApellidos(pedirString("Introduce los APELLIDOS"));

		return per;
	}

	// Devuelve un objeto Dirección con todos sus datos
	public static Direccion pedirDatosDireccion() {
		Direccion dir = new Direccion();
		dir.setCalle(pedirString("Introduce la CALLE"));
		dir.setNumero(pedirEntero("Introduce el NUMERO  de la calle"));
		dir.setCiudad(pedirString("Introduce la CIUDAD"));
		dir.setPais(pedirString("Introduce el PAIS"));

		return dir;
	}

	
	public static String pedirString(String cadena) {
		Scanner entrada = new Scanner(System.in);
		System.out.println(cadena);
		return entrada.nextLine();
	}

	
	public static LocalDate pedirfecha(String cadena) {
		Scanner entrada = new Scanner(System.in);
		System.out.println(cadena);
		LocalDate fecha = LocalDate.parse(entrada.nextLine());
		return fecha;
	}

	
	public static int pedirEntero(String cadena) {
		Scanner entrada = new Scanner(System.in);
		System.out.println(cadena);
		return entrada.nextInt();

	}

}
