package es.iesvjp.acadt;

public class Empleado {
	String nombre;
	String apellidos;
	//relacionship
	Departamento depart;

	public Empleado(String nombre, String apellidos, Departamento depart) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.depart = depart;
	}
	
	public Empleado(String nombre, String apellidos) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		
	}
	
     public Empleado() {
		
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

	public Departamento getDepart() {
		return depart;
	}

	public void setDepart(Departamento depart) {
		this.depart = depart;
	}

	@Override
	public String toString() {
		String rdo="Empleado [nombre=" + nombre + ", apellidos=" + apellidos ;
		if(this.getDepart()!=null)
		{
			rdo=rdo+" departamento: "+ getDepart().getNombre();
		}
		rdo=rdo+ " ]";
		return rdo;
	}

	

	

}
