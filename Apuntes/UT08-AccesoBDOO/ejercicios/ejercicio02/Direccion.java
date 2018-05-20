package ejercicio02;

/**
 * @author Ana Arribas
 *
 */
public class Direccion {

	private Integer numero;
	private String calle;
	private String ciudad;
	private String pais;
	
	public Direccion() {
		
	}
	public Direccion(Integer numero, String calle) {
		this.numero = numero;
		this.calle = calle;
	}

	public Direccion(Integer numero, String calle, String ciudad, String pais) {
		this.numero = numero;
		this.calle = calle;
		this.ciudad = ciudad;
		this.pais = pais;
	}
	
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	@Override
	public String toString() {
		return "Direccion [numero=" + numero + ", calle=" + calle + ", ciudad=" + ciudad + ", pais=" + pais + "]";
	}

	
	
}
