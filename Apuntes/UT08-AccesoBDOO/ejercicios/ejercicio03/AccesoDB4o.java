package ejercicio03;

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

	static String BDEquipos = "BDEquipos.yap";

	/**
	 * @return devuelve un objeto ObjectContainer
	 */
	public static ObjectContainer getConexion() {
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		// cambiamos el nivel de actualización a 5
		config.common().updateDepth(5);
		config.common().activationDepth(5);

		return Db4oEmbedded.openFile(config, BDEquipos);
	}

	/**
	 * @param jug: Jugador a insertar, pero sin su equipo
	 */
	public static void InsertJugador(Jugador jug) {
		ObjectContainer db = getConexion();
		db.store(jug);
		db.commit();
		System.out.println("Jugador añadido correctamente");
		db.close();
	}

	/**
	 * @param equip: Equipo a insertar
	 */
	public static void InsertEquipo(Equipo equip) {
		ObjectContainer db = getConexion();
		db.store(equip);
		db.commit();
		System.out.println("Equipo añadido correctamente");
		db.close();
	}

	
	/**
	 * @param jug:Jugador al que se va asignar un Equipo
	 * @param equip: Equipo a asignar al Jugador
	 */
	public static void SetEquipoJugador(Jugador jug, Equipo equip) {
		ObjectContainer db = getConexion();
		// buscamos al jugador y al equipo
		Jugador jug_query = new Jugador(jug.getNombre(),jug.getApellidos());
		Equipo equi_query = new Equipo(equip.getNombre());
		
		ObjectSet<Jugador> osjug = db.queryByExample(jug_query);
		ObjectSet<Equipo> osequi = db.queryByExample(equi_query);
		
		if (osjug.size() > 0 && osequi.size() > 0) {
			//existen Jugador y Equipo
			Jugador jugador = osjug.next();
			Equipo equipo = osequi.next();
			//comprobamos que el Jugador no esté en ningún equipo
			if (jugador.getEquipo() == null) {
				jugador.setEquipo(equipo);
				db.store(jugador);
				db.commit();
				System.out.println("Equipo asignado correctamente al Jugador.");
			} else {
				System.out.println("El jugador ya está asignado a un equipo.");
			}
		} else {
			System.out.println("El equipo o el jugador no existen.");
		}
		db.close();
	}

	
	/**
	 * @param jug: Jugador a añadir en el Equipo
	 * @param equip: Equipo dónde se añadirá el Jugador
	 */
	public static void AddJugadorEquipo(Jugador jug, Equipo equip) {
		ObjectContainer db = getConexion();
		// buscamos al jugador y al equipo
		Jugador jug_query = new Jugador(jug.getNombre(),jug.getApellidos());
		Equipo equi_query = new Equipo(equip.getNombre());
		
		ObjectSet<Jugador> osjug = db.queryByExample(jug_query);
		ObjectSet<Equipo> osequi = db.queryByExample(equi_query);
		// si lo encontramos les hacemos cambios
		if (osjug.size() > 0 && osequi.size() > 0) {
			Jugador jugador = osjug.next();
			Equipo equipo = osequi.next();
			equipo.getJugadores().add(jugador);
			db.store(equipo);
			db.commit();
			System.out.println("Jugador insertado en equipo.");
		} else {
			System.out.println("El equipo o el jugador no existen.");
		}
		db.close();
	}

	
	/**
	 * @param jug: Jugador del cual vamos actualizar sus datos
	 */
	public static void UpdateJugador(Jugador jug) {
		ObjectContainer db = getConexion();
		// buscamos el jugador por su nombre y apellidos
		Jugador jug_query = new Jugador(jug.getNombre(),jug.getApellidos());
		ObjectSet<Jugador> osjug = db.queryByExample(jug_query);
		if (osjug.size() > 0) {
			Jugador jugador = osjug.next();
			Jugador newjugador=pedirJugador();
			//asignamos nuevos datos al jugador
			jugador.setNombre(newjugador.getNombre());
			jugador.setApellidos(newjugador.getApellidos());
			jugador.setPosicion(newjugador.getPosicion());
			jugador.setDorsal(newjugador.getDorsal());
			jugador.setNacionalidad(newjugador.getNacionalidad());
			
			db.store(jugador);
			db.commit();
			System.out.println("Datos del jugador cambiados correctamente.");
		} else {
			System.out.println("No se encontro el jugador.");
		}
		db.close();
	}

	
	/**
	 * @param equip: Equipo a actualizar sus datos
	 */
	public static void UpdateEquipo(String equip) {
		ObjectContainer db = getConexion();
		// buscamos el equipo por su nombre
		Equipo equ_query = new Equipo(equip);
		ObjectSet<Equipo> osequi = db.queryByExample(equ_query);
		if (osequi.size() > 0) {
			Equipo equipo = osequi.next();
			Equipo newEquipo= pedirEquipo();
			//cambiamos lo datos al Equipo
			equipo.setNombre(newEquipo.getNombre());
			equipo.setCategoria(newEquipo.getCategoria());
			equipo.setEstadio(newEquipo.getEstadio());
			equipo.setPais(newEquipo.getPais());
			
			db.store(equipo);
			db.commit();
			System.out.println("Datos del equipo cambiados correctamente");
		} else {
			System.out.println("No se encontró el equipo");
		}
		db.close();
	}

	
	/**
	 * @param jug: Jugador a cambiar de Equipo
	 * @param equip: Nuevo Equipo donde estará el Jugador
	 */
	public static void cambiarEquipoJugador(Jugador jug, Equipo equip) {
		ObjectContainer db = getConexion();
		Jugador jug_query = new Jugador(jug.getNombre(), jug.getApellidos());
		Equipo equi_query = new Equipo(equip.getNombre());

		ObjectSet<Jugador> osjug = db.queryByExample(jug_query);
		ObjectSet<Equipo> osequi = db.queryByExample(equi_query);

		if (osjug.size() > 0 && osequi.size() > 0) {
			Jugador jugador = osjug.next();
			Equipo equipo = osequi.next();
			if (jugador.getEquipo() != null) {// si no tiene equipo no se le
												// puede cambiar
				Equipo equipold = jugador.getEquipo();
				jugador.setEquipo(equipo); // añadimos el nuevo equipo al
											// jugador
				// añadimos el nuevo jugador al equipo
				equipo.getJugadores().add(jugador);

				// borramos el Jugador del antiguo Equipo
				equipold.getJugadores().remove(jugador);

				db.store(jugador);
				db.store(equipo);
				db.store(equipold);
				db.commit();
				System.out.println("Jugador cambiado de equipo correctamente.");
			} else {
				System.out.println("Jugador sin equipo");
			}
		} else {
			System.out.println("El equipo o el jugador no existen.");
		}
		db.close();
	}

	/**
	 * @param jug: Jugador a eliminar de la BD
	 */
	public static void DeleteJugador(Jugador jug) {
		ObjectContainer db = getConexion();
		Jugador jug_query = new Jugador(jug.getNombre(), jug.getApellidos());
		ObjectSet<Jugador> osjug = db.queryByExample(jug_query);
		if (osjug.size() > 0) {
			Jugador jugador = osjug.next();// cogemos el jugador
			Equipo equipo = jugador.getEquipo(); // obtenemos el Equipo del
													// Jugador
			equipo.getJugadores().remove(jugador); // eliminamos el jugador del
													// equipo

			db.store(equipo);// guardamos cambios equipo
			db.delete(jugador); // eliminamos el jugador
			db.commit();
			System.out.println("Jugador eliminado con éxito");
		} else {
			System.out.println("El jugador no existe");
		}
		db.close();
	}

	/**
	 * @param equip: Equipo del cual mostraremos todos sus jugadores
	 */
	public static void GetJugadoresByEquipo(String equip) {
		ObjectContainer db = getConexion();
		Equipo equi_query = new Equipo(equip);
		ObjectSet<Equipo> osequi = db.queryByExample(equi_query);
		if (osequi.size() > 0) {
			Equipo equipo = osequi.next();
			// sacamos todos los jugadores del equipo
			System.out.println(equipo.getJugadores());
		} else {
			System.out.println("El equipo no existe.");
		}
		db.close();
	}

	/**
	 * Mostrar todos los Jugadores de la BD
	 */
	public static void GetAllJugadores() {
		ObjectContainer db = getConexion();
		ObjectSet<Jugador> osjug = db.queryByExample(Jugador.class);
		while (osjug.hasNext()) {
			System.out.println(osjug.next());
		}
		db.close();
	}

	/**
	 * Mostrar todos los Equipos de la BD
	 */
	public static void GetAllEquipos() {
		ObjectContainer db = getConexion();
		ObjectSet<Equipo> osjug = db.queryByExample(Equipo.class);
		while (osjug.hasNext()) {
			System.out.println(osjug.next());
		}
		db.close();
	}

	/**
	 * @return: devuelve un objeto Jugador con sus datos
	 */
	public static Jugador pedirJugador() {
		Jugador jugador = new Jugador();
		jugador.setNombre(pedirCadena("Introduce el NOMBRE del Jugador"));
		jugador.setApellidos(pedirCadena("Introduce los APELLIDOS del Jugador"));
		jugador.setNacionalidad(pedirCadena("Introduce la NACIONALIDAD del Jugador"));
		jugador.setDorsal(pedirEntero("Introduce el número del DORSAL del Jugador"));
		jugador.setPosicion(pedirCadena("Introduce la POSICIÓN del Jugador"));

		return jugador;
	}
	
	/**
	 * @return: devuelve un objeto Jugador con sus datos
	 */
	public static Jugador pedirJugadorBusquedas() {
		Jugador jugador = new Jugador();
		jugador.setNombre(pedirCadena("Introduce el NOMBRE del Jugador"));
		jugador.setApellidos(pedirCadena("Introduce los APELLIDOS del Jugador"));
		
		return jugador;
	}

	/**
	 * @return: devuelve un objeto Equipo con sus datos
	 */
	public static Equipo pedirEquipo() {
		Equipo equipo = new Equipo();
		equipo.setNombre(pedirCadena("Introduce el NOMBRE del Equipo"));
		equipo.setCategoria(pedirCadena("Introduce la CATEGORÍA del Equipo"));
		equipo.setEstadio(pedirCadena("Introduce el ESTADIO del Equipo"));
		equipo.setPais(pedirCadena("Introduce el PAIS del Equipo"));

		return equipo;
	}
	
	/**
	 * @return: devuelve un objeto Equipo con sus datos
	 */
	public static Equipo pedirEquipoBusquedas() {
		Equipo equipo = new Equipo();
		equipo.setNombre(pedirCadena("Introduce el NOMBRE del Equipo"));
		equipo.setCategoria(pedirCadena("Introduce la CATEGORÍA del Equipo"));
		

		return equipo;
	}

	public static String pedirCadena(String cadena) {
		Scanner entrada = new Scanner(System.in);
		System.out.println(cadena);
		return entrada.nextLine();
	}

	public static Integer pedirEntero(String cadena) {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Introduzca " + cadena + ":");
		return entrada.nextInt();

	}

}
