package ejercicio01;

import java.util.ArrayList;


public class Departamento {
	private String nombre;
	private String localidad;
	// relationship
	private ArrayList<Empleado> empleados = new ArrayList<Empleado>();

	public Departamento() {
		

	}
	public Departamento(String nombre, String localidad) {
		this.nombre = nombre;
		this.localidad = localidad;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}

	public void addEmpleados(Empleado emp) {
		this.empleados.add(emp);
	}

}
