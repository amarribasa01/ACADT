package ejercicio03;

import java.util.Scanner;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.io.Storage;
/**
 * @author Ana Arribas
 *
 */
public class Principal {

	

	public static void main(String[] args) {
		menu();
	}

	
	public static void menu() {
		Scanner entrada = new Scanner(System.in);
		int opc;
		do {
			System.out.println("\n1.- Alta jugador.");
			System.out.println("2- Alta equipo.");
			System.out.println("3- Asignar equipo al jugador");
			System.out.println("4- Añadir jugadores a un equipo");
			System.out.println("5- Actualizar los datos jugador.");
			System.out.println("6- Actualizar los datos equipo.");
			System.out.println("7- Cambiar equipo de un jugador.");
			System.out.println("8- Eliminar un jugador.");
			System.out.println("9- Mostrar los jugadores de un equipo.");
			System.out.println("10-Mostrar Jugadores.");
			System.out.println("11-Mostrar equipos.");
			System.out.println("12-Salir");
			opc = entrada.nextInt();
			switch (opc) {
			case 1:
				//alta jugador
				AccesoDB4o.InsertJugador(AccesoDB4o.pedirJugador());
				break;
			case 2:
				//alta equipo
				AccesoDB4o.InsertEquipo(AccesoDB4o.pedirEquipo());
				break;
			case 3:
				//Asignar equipo a un Jugador
				AccesoDB4o.SetEquipoJugador(AccesoDB4o.pedirJugadorBusquedas(),AccesoDB4o.pedirEquipoBusquedas());
				break;
			case 4:
				//añadir un Jugador a un Equipo
				AccesoDB4o.AddJugadorEquipo(AccesoDB4o.pedirJugadorBusquedas(),AccesoDB4o.pedirEquipoBusquedas());
				break;
			case 5:
				//Actualizar los datos del Jugador
				AccesoDB4o.UpdateJugador(AccesoDB4o.pedirJugadorBusquedas());
				break;
			case 6:
				//Actualizar los datos del Equipo
				AccesoDB4o.UpdateEquipo(AccesoDB4o.pedirCadena("Introduce el Equipo a actualizar"));;
				break;
			case 7:
				//Cambiar a un Jugador de Equipo
				AccesoDB4o.cambiarEquipoJugador(AccesoDB4o.pedirJugador(),AccesoDB4o.pedirEquipo());
				break;
			case 8:
				//Eliminar un Jugador
				AccesoDB4o.DeleteJugador(AccesoDB4o.pedirJugadorBusquedas());
				break;
			case 9:
				//Mostrar Jugadores de un determinado Equipo
				AccesoDB4o.GetJugadoresByEquipo(AccesoDB4o.pedirCadena("Introduce el Equipo para ver sus Jugadores"));
				break;
			case 10:
				//Mostrar todos los Jugadores de la BD
				AccesoDB4o.GetAllJugadores();
				break;
			case 11:
				//Mostrar todos lo Equipos de la BD
				AccesoDB4o.GetAllEquipos();
				break;
			case 12:
				System.out.println("Fin....");
				break;
			default:
				System.out.println("Opción inválida.");
				break;
			}
		} while (opc<12);

	}

	
}
