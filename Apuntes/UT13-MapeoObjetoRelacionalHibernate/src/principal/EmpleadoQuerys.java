package principal;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import jdbc.Empleados;
import jdbc.SessionFactoryUtil;

public class EmpleadoQuerys {

	
	public static void UpdateSalario(float salario, String apellido)
	{
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
	   
	    Query query = session.createQuery("Update Empleados e set e.salario= :nuevoSal where e.apellido= :ape"); 
	    query.setFloat("nuevoSal",salario);
	    query.setString("ape",apellido);
	    query.executeUpdate() ;
	    
	    tx.commit();
	    session.close();
			
	}
	
	public static void DeleteEmpledosByDep(int dep)
	{
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
	   
	    Query query = session.createQuery("Delete Empleados e where e.departamentos.deptNo= :dep"); 
	    query.setFloat("dep",dep);
	   
	    query.executeUpdate() ;
	    
	    tx.commit();
	    session.close();
			
	}
	public static void GetSalarioMedio()
	{
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();
		//salario medio de los empleados
		Query q = session.createQuery("select avg(em.salario) from Empleados as em");
		Double suma = (Double) q.uniqueResult();
		
		System.out.println( "Salario medio: "+suma);

		
	}
	public static void ListaParametros() {
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();
		// Lista de parámetros nombrados
		// Empleados cuyo número de departamento sea 10 o 20
		List<Integer> numeros = new ArrayList<Integer>();
		numeros.add(10);
		numeros.add(30);
		Query q = session.createQuery("from Empleados emp where emp.departamentos.deptNo in :listadep");
		q.setParameterList("listadep", numeros);
		Empleados emp = new Empleados();
		List<Empleados> lista = q.list();

		// Obtenemos un Iterador y recorremos la lista.
		Iterator<Empleados> iter = lista.iterator();

		while (iter.hasNext()) {
			// extraemos el objeto
			emp = (Empleados) iter.next();
			System.out.println(emp.getApellido() + "--" + emp.getSalario());

		}

		session.close();

	}

	public static void GetEmpleadosByFechAlta() {
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();
		// Parámetro de tipo Date
		// Empleados cuya fecha de alta es 1991-12-03
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
		String strFecha = "11-02-2005";
		Date fecha = null;
		try {

			fecha = formatoFecha.parse(strFecha);

		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		Query q = session.createQuery("from Empleados emp where emp.fechaAlt = ?");
		q.setDate(0, fecha);
		
		Empleados emp = new Empleados();
		List<Empleados> lista = q.list();

		// Obtenemos un Iterador y recorremos la lista.
		Iterator<Empleados> iter = lista.iterator();

		while (iter.hasNext()) {
			// extraemos el objeto
			emp = (Empleados) iter.next();
			System.out.println(emp.getApellido() + "--" + emp.getSalario());

		}

		session.close();

	}

	public static void GetEmpleadosByOficio(int departamento, String oficio, int tipo) {
		SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
		Session session = sesion.openSession();
		Query q;

		Empleados emp = new Empleados();
		if (tipo == 1) {// Utilizando parámetros nombrados
			q = session.createQuery("from Empleados emp where emp.departamentos.deptNo= :ndep and emp.oficio = :ofi");
			q.setInteger("ndep", departamento);
			q.setString("ofi", oficio);
		} else {
			// Utilizando parámetros por posición
			q = session.createQuery("from Empleados emp where emp.departamentos.deptNo = ? and emp.oficio = ?");
			q.setInteger(0, departamento);
			q.setString(1, oficio);
		}

		List<Empleados> lista = q.list();

		// Obtenemos un Iterador y recorremos la lista.
		Iterator<Empleados> iter = lista.iterator();

		while (iter.hasNext()) {
			// extraemos el objeto
			emp = (Empleados) iter.next();
			System.out.println(emp.getApellido() + "--" + emp.getSalario());

		}

		session.close();

	}

}
