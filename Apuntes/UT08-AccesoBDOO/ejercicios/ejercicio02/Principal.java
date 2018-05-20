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
public class Principal {

	static String BDPesronas = "personas.yap";

	public static void main(String[] args) {
		menu();
	}

	public static void menu() {
		Scanner entrada = new Scanner(System.in);
		int opc;
		do {
			System.out.println("\n1.- Insertar Persona.");
			System.out.println("2.- Insertar Dirección");
			System.out.println("3.- Añadir dirección a la persona.");
			System.out.println("4.- Modificar datos persona.");
			System.out.println("5.- Mostrar todos las personas que viven en una ciudad.");
			System.out.println("6.- Eliminar persona.");
			System.out.println("7.- Mostrar personas.");
			System.out.println("8.- Mostrar direccion.");
			System.out.println("9.- Salir.");
			opc = entrada.nextInt();

			switch (opc) {
			case 1:
				//Insertar persona sin Dirección
				AccesoDB4o.InsertPersona(AccesoDB4o.pedirDatosPersona());
				break;
			case 2:
				//Insertar Direccón
				AccesoDB4o.InsertDireccion(AccesoDB4o.pedirDatosDireccion());
				break;
			case 3:
				//Añadir la Dirección a una persona
				AccesoDB4o.AddDireccionPersona(AccesoDB4o.pedirDatosBusquedaPersona(),
						AccesoDB4o.pedirDatosDireccion());
				break;

			case 4:
				//Actualizar los datos de las Persona, solamente sus datos personales
				AccesoDB4o.UpdatePersona(AccesoDB4o.pedirDatosBusquedaPersona());
				break;
			case 5:
				//Mostrar todas las personas de una determinada Ciudad
				AccesoDB4o.GetPersonasByCiudad(AccesoDB4o.pedirString("Introduce la Ciudad"));
				break;
			case 6:
				AccesoDB4o.DeletePersona(AccesoDB4o.pedirDatosBusquedaPersona());
				break;
			case 7:
				AccesoDB4o.GetAllPersonas();
				break;
			case 8:
				AccesoDB4o.GetAllDirecciones();
				
				break;
			case 9:
				System.out.println("Fin...");
				break;
			default:
				System.out.println("Eso no es una opción valida.");
				break;
			}
		} while (opc<9);

	}

}
