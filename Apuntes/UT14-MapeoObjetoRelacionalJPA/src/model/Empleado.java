package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the empleados database table.
 * 
 */
@Entity
@Table(name = "empleados")
@NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "emp_no")
	private int empNo;

	private String apellido;

	private float comision;

	private int dir;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_alt")
	private Date fechaAlt;

	public Empleado(int empNo, String apellido, float comision, int dir, Date fechaAlt, String oficio, float salario) {

		this.empNo = empNo;
		this.apellido = apellido;
		this.comision = comision;
		this.dir = dir;
		this.fechaAlt = fechaAlt;
		this.oficio = oficio;
		this.salario = salario;

	}

	private String oficio;

	private float salario;

	// bi-directional many-to-one association to Departamento
	@ManyToOne
	@JoinColumn(name = "dept_no")
	private Departamento departamento;

	public Empleado() {
	}

	public int getEmpNo() {
		return this.empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public float getComision() {
		return this.comision;
	}

	public void setComision(float comision) {
		this.comision = comision;
	}

	public int getDir() {
		return this.dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public Date getFechaAlt() {
		return this.fechaAlt;
	}

	public void setFechaAlt(Date fechaAlt) {
		this.fechaAlt = fechaAlt;
	}

	public String getOficio() {
		return this.oficio;
	}

	public void setOficio(String oficio) {
		this.oficio = oficio;
	}

	public float getSalario() {
		return this.salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

}