package jdbc;

public class Totales {
	private int num_depart;
	private String nombre;
	private long num_empl;
	private double media_sal;
	
	public Totales(int num_depart, String nombre, long num_empl, double media_sal) {
		
		this.num_depart = num_depart;
		this.nombre = nombre;
		this.num_empl = num_empl;
		this.media_sal = media_sal;
	}


		
	public int getNum_depart() {
		return num_depart;
	}


	public void setNum_depart(int num_depart) {
		this.num_depart = num_depart;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public long getNum_empl() {
		return num_empl;
	}


	public void setNum_empl(int num_empl) {
		this.num_empl = num_empl;
	}


	public double getMedia_sal() {
		return media_sal;
	}


	public void setMedia_sal(double media_sal) {
		this.media_sal = media_sal;
	}


	



}
