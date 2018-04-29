package es.iesvjp.acadt;

import java.io.Serializable;

/**
 * @author Ana Arribas
 *
 */
public class Persona implements Serializable {

	private static final long serialVersionUID = 314226818941960136L;
	private String nombre;
	private int edad;

	public Persona() {
	}

	public Persona(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
	}

	public void setNombre(String nomb) {
		this.nombre = nomb;
	} // para establecer el nombre

	public void setEdad(int ed) {
		this.edad = ed;
	}// para establecer la edad

	public String getNombre() {
		return nombre;
	} // devuelve nombre

	public int getEdad() {
		return edad;
	} // devuelve edad

}
