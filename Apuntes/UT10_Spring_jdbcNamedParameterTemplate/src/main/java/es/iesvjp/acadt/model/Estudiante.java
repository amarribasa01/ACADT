package es.iesvjp.acadt.model;

public class Estudiante {
	private Integer id;//es un campo autonumérico en la BD
	private String nombre;
	private Integer edad;

	public Estudiante() {

	}
	
	public Estudiante(String nombre, Integer edad) {
		this.nombre = nombre;
		this.edad = edad;
	}
	
	public Estudiante(Integer id, String nombre, Integer edad) {
		this.id=id;
		this.nombre = nombre;
		this.edad = edad;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

}
