package es.iesvjp.acadt;

import java.time.LocalDate;

public class Conductor {
	private LocalDate fechaCarnet;
	private IVehiculo vehiculo;

	public Conductor(IVehiculo vehiculo, LocalDate fecha) {
		this.vehiculo=vehiculo;
		this.fechaCarnet=fecha;
		
	}
	
	public LocalDate getFechaCarnet() {
		return fechaCarnet;
	}

	public void setFechaCarnet(LocalDate fechaCarnet) {
		this.fechaCarnet = fechaCarnet;
	}

	public IVehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(IVehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public void conducir()
	{
		vehiculo.mover();
	}

	@Override
	public String toString() {
		return "Conductor [fechaCarnet=" + fechaCarnet + ", vehiculo=" + vehiculo + "]";
	}




}
