package ejercicio03;

/**
 * @author Ana Arribas
 *
 */
public class Jugador {

	private String nombre;
	private String apellidos;
	private String nacionalidad;
	private String posicion;
	private Integer dorsal;
	//relationship
    private Equipo equipo;

    public Jugador() {
		
	}
	public Jugador(String nombre, String apellidos) {
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public Jugador(String nombre, String apellidos, String nacionalidad, String posicion, Integer dorsal) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nacionalidad = nacionalidad;
		this.posicion = posicion;
		this.dorsal = dorsal;
	}

	public Jugador(String nombre, String apellidos, String nacionalidad, String posicion, Integer dorsal,
			Equipo equipo) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nacionalidad = nacionalidad;
		this.posicion = posicion;
		this.dorsal = dorsal;
		this.equipo = equipo;
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

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public Integer getDorsal() {
		return dorsal;
	}

	public void setDorsal(Integer dorsal) {
		this.dorsal = dorsal;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

		
	
	@Override
	public String toString() {
		return "Jugador [nombre=" + nombre + ", apellidos=" + apellidos + ", nacionalidad=" + nacionalidad
				+ ", posicion=" + posicion + ", dorsal=" + dorsal + ", equipo=" + equipo + "]";
	}

	

}
