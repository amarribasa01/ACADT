package ejercicio02;

import java.time.LocalDate;
/**
 * @author Ana Arribas
 *
 */
public class Persona {

	private String nombre;
	private String apellidos;
	private LocalDate fecha_nac;
	private Integer telefono;
	private String email;

	// relationship
	Direccion direccion;

	public Persona() {

	}

	public Persona(String nombre, String apellidos, LocalDate fecha_nac, Integer telefono, String email) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fecha_nac = fecha_nac;
		this.telefono = telefono;
		this.email = email;
	}

	public Persona(String nombre, String apellidos, LocalDate fecha_nac, Integer telefono, String email,
			Direccion dir) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fecha_nac = fecha_nac;
		this.telefono = telefono;
		this.email = email;
		this.direccion = dir;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(LocalDate fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Direccion getDir() {
		return direccion;
	}

	public void setDir(Direccion dir) {
		this.direccion = dir;
	}

	@Override
	public String toString() {
		String cad = "Persona [nombre=" + nombre + ", apellidos=" + apellidos + ", fecha_nac=" + fecha_nac
				+ ", telefono=" + telefono + ", email=" + email;

		if (getDir() != null) {
			cad = cad + ", direccion=" + direccion + "]";
		} else {
			cad = cad + "]";
		}
		return cad;
	}

}
