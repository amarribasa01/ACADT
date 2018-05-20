package principal;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import jdbc.Departamentos;
import jdbc.Empleados;
import jdbc.SessionFactoryUtil;

public class DepartamentoUtils {

	
	public static void LoadDepartamento(int num) {
		// abrimos sesión
		SessionFactory sf = SessionFactoryUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			// cargamos el departamento
			Departamentos dep = new Departamentos();
			dep = (Departamentos) session.load(Departamentos.class, num);

			System.out.println("Nombre Departamento:" + dep.getDnombre());
			System.out.println("Localidad:" + dep.getLoc());

		} catch (ObjectNotFoundException ex) {
			System.out.println("Departamento no encontrado");
			session.close();
		}

		tx.commit();
		// cerramos la sesión
		session.close();
		sf.close();

	}
	public static void GetDepartamento(int num) {
		// abrimos sesión
		SessionFactory sf = SessionFactoryUtil.getSessionFactory();
		Session session = sf.openSession();

		// cargamos el departamento
		Departamentos dep = new Departamentos();
		dep = (Departamentos) session.get(Departamentos.class, num);
		if (dep == null) {
			System.out.println("El departamento no existe");

		} else {

			System.out.println("Nombre Departamento: " + dep.getDnombre());
			System.out.println("Localidad: " + dep.getLoc());
		}
		// cerramos la sesión
		session.close();
		sf.close();
	}

	public static void GetEmpleadosbyDep(int dep) {
		// abrimos sesión
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();

		System.out.println("DATOS DEL DEPARTAMENTO " + dep);
		Departamentos dept = new Departamentos();
		// obtenemos el objeto departamento a buscar
		dept = (Departamentos) session.get(Departamentos.class, dep);
		if (dept != null) {
			// existe el departamento
			System.out.println("Nombre Dep:" + dept.getDnombre());
			System.out.println("Localidad:" + dept.getLoc());
			System.out.println("==============================");

			System.out.println("EMPLEADOS DEL DEPARTAMENTO " + dep);
			Set<Empleados> listaemple = dept.getEmpleadoses();// obtenemos
																// empleados
			Iterator<Empleados> it = listaemple.iterator();
			System.out.println("Número de empleados: " + listaemple.size());
			while (it.hasNext()) {
				Empleados emple = new Empleados();
				emple = it.next();
				System.out.println(emple.getApellido() + "--> Salario:" + emple.getSalario() + "€");
				System.out.println("==============================");
			}
		} else {
			System.out.println("No existe el departamento " + dept);

		}

		session.close();
	}
	public static void InsertarEmpleado() {
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		// TODO:código de persistencia
		
		Empleados emp= new Empleados();
		emp.setApellido("Lopez");
		emp.setComision(20.0f);
		emp.setEmpNo(100);
		
		//buscamos el departamento a asignar al nuevo empleado
		Departamentos dep = new Departamentos();
		dep = (Departamentos) session.get(Departamentos.class,60);
		emp.setDepartamentos(dep);
		
	    session.save(emp);
		
		
		
		
		
		
		tx.commit(); // validad la transacción
		session.close();// cierra la sesión

	}
	
	public static void InsertarDepartamento() {
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		// TODO:código de persistencia

		System.out.println("Inserto una fila en la tabla DEPARTAMENTOS.");
		Departamentos dep = new Departamentos();
		dep.setDeptNo(110);
		dep.setDnombre("INFORMATICA");
		dep.setLoc("PLASENCIA");
		session.save(dep);
		
	    tx.commit(); // validad la transacción
		session.close();// cierra la sesión
		sesion.close();

	}
	
	public static void ActualizarEmpleado()
	{
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		
		Empleados em = new Empleados();
		em = (Empleados) session.get(Empleados.class,2);
		
		System.out.println("Salario antiguo: "+em.getSalario());
		System.out.println("Comisión antigua: "+em.getComision());
		
		em.setSalario((float) 3000);
		em. setComision ((float) 100);
		session.update (em) ; //modifica el objeto
		System.out.println("Salario nuevo: "+em.getSalario());
		System.out.println("Comisión nueva: "+em.getComision ());
		
		tx.commit(); // validad la transacción
		session.close();// cierra la sesión
		
	}
	
	public static void ActualizarDepartamento() {
		// abrimos sesión
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();

		// cargamos el departamento
		Departamentos dep = new Departamentos();
		dep = (Departamentos) session.get(Departamentos.class,60);
		//Cambiamos de localidad al departamento de Marketing	
		dep.setLoc("CACERES");
		session.update(dep);
	
		tx.commit(); // validad la transacción
		session.close();// cierra la sesión

	}

	public static void DeleteDepartamento(int dept) {
		// abrimos sesión
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();

		// cargamos el departamento
		Departamentos dep = new Departamentos();
		
		dep = (Departamentos) session.get(Departamentos.class,dept);
			
		session.delete(dep);
				
		tx.commit(); // validad la transacción
		session.close();// cierra la sesión

	}

	

	


}
