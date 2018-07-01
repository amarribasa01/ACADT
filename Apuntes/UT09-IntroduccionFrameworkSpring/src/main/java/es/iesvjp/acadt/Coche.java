package es.iesvjp.acadt;

public class Coche implements IVehiculo {
	
	private int deposito;
	private String marca;
	private String modelo;
	
	public Coche() {
		
	}
	
	public Coche(int deposito, String marca, String modelo) {
		this.deposito = deposito;
		this.marca = marca;
		this.modelo = modelo;
	}
	
	public int getDeposito() {
		return deposito;
	}
	public void setDeposito(int deposito) {
		this.deposito = deposito;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Override
	public void mover() {
		if(deposito>0) deposito --;
		
	}

	@Override
	public void arrancar() {
		System.out.println("Arrancando el coche");
		
	}

	@Override
	public void parar() {
		System.out.println("Parando el coche");
		
	}
	@Override
	public String toString() {
		return "Coche [deposito=" + deposito + ", marca=" + marca + ", modelo=" + modelo + "]";
	}


}
