package ejercicio01;

import java.util.Scanner;

public class Principal {

	static String BDEmp = "BDEmpleados.yap";

	public static void main(String[] args) {
		menu();
	}

	public static void menu() {

		Scanner entrada = new Scanner(System.in);
		int opc;

		do {
			System.out.println("\n1.- Alta Empleado.");
			System.out.println("2.- Alta Departamento.");
			System.out.println("3.- Asignar un Empleado a un Departamento");
			System.out.println("4.- Asignar un Empleado al Departamento.");
			System.out.println("5.- Mostrar Empleados.");
			System.out.println("6.- Mostrar Departamentos.");
			System.out.println("7.- Actualizar los datos del empleado.");
			System.out.println("8.- Cambiar a un empleado de departamento");
			System.out.println("9.- Actualizar los datos del departamento.");
			System.out.println("10.- Borrar un departamento.");
			System.out.println("11.- Salir.");
			opc = entrada.nextInt();
			switch (opc) {
			case 1:
				//alta empleado
				AccesoDB4o.Insert_Empleado(AccesoDB4o.pedirEmpleado());
				break;
			case 2:
				//alta departamento
				AccesoDB4o.Insert_Departamento(AccesoDB4o.pedirDepartamento());
				break;	
			
			case 3:
				//Asignar al Empleado un departamento
				AccesoDB4o.setDepartamentoEmpleado(AccesoDB4o.pedirEmpleado(), AccesoDB4o.pedirDepartamento());
				break;
			case 4:
				//Añadir al departamento el empleado
			   AccesoDB4o.AddEmpleadoDepartamento(AccesoDB4o.pedirEmpleado(), AccesoDB4o.pedirDepartamento());
				break;
			case 5:
				//mostrar Empleados
				AccesoDB4o.GetAllEmpleados();;
				break;
			case 6:
				//mostrar departamentos
				AccesoDB4o.GetAllDepartamentos();;
				break;	
			
			case 7:
				//Actualizar empleado
			    AccesoDB4o.UpdateEmpleado(AccesoDB4o.pedirEmpleado());
				break;
			case 8:
				//Cambiar a un empleado de Departamento
				AccesoDB4o.CambiarEmpleadoDepartamento(AccesoDB4o.pedirEmpleado(), AccesoDB4o.pedirDepartamento());
				break;
			case 9:
				//Actualizar departamento
				AccesoDB4o.UpdateDepartamento(AccesoDB4o.pedirDepartamento());
				break;
		
			case 10:
				//eliminar departamento
				AccesoDB4o.deleteDepartamento(AccesoDB4o.pedirDepartamento());
				break;
			case 11:
				System.out.println("Saliendo....");
				break;
			default:
				System.out.println("Eso no es una opción valida.");
				break;
			}

		} while (opc<11);

	}
	
	
		

	
}
