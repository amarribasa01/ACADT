package ejercicio03;

import java.util.ArrayList;

/**
 * @author Ana Arribas
 *
 */
public class Equipo {

	private String nombre;
	private String categoria;
	private String pais;
	private String estadio;
	//relationship
	ArrayList<Jugador> jugadores = new ArrayList<>();

	public Equipo() {
		
	}
	public Equipo(String nombre) {
		this.nombre = nombre;
	}

	public Equipo(String nombre, String categoria, String pais, String estadio) {
		this.nombre = nombre;
		this.categoria = categoria;
		this.pais = pais;
		this.estadio = estadio;
	}

	public Equipo(String nombre, String categoria, String pais, String estadio, ArrayList<Jugador> jugadores) {
		this.nombre = nombre;
		this.categoria = categoria;
		this.pais = pais;
		this.estadio = estadio;
		this.jugadores = jugadores;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEstadio() {
		return estadio;
	}

	public void setEstadio(String estadio) {
		this.estadio = estadio;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	
	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", categoria=" + categoria + ", pais=" + pais + ", estadio=" + estadio
				+ ", jugadores=" + jugadores + "]";
	}

	

}
