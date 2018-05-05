package es.iesvjp.acadt;

import com.google.gson.annotations.SerializedName;

public class SerializeEmpleado {
	
@SerializedName("empleado")
private Empleado empleado;

public Empleado getEmpleado() {
	return empleado;
}

public void setEmpleado(Empleado empleado) {
	this.empleado = empleado;
}

public SerializeEmpleado(Empleado empleado) {
	super();
	this.empleado = empleado;
}


}