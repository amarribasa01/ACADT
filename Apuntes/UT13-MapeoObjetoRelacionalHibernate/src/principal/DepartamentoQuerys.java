package principal;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import jdbc.Departamentos;
import jdbc.Empleados;
import jdbc.SessionFactoryUtil;
import jdbc.Totales;

public class DepartamentoQuerys {

	
	public static void selectEmpleadoByDep(String dep)
	{
		SessionFactory sf = SessionFactoryUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createSQLQuery("SELECT e.emp_no,e.apellido,e.oficio, d.dnombre "
				+ "FROM empleados e inner join departamentos d on e.dept_no=d.dept_no "
				+ "where d.dnombre LIKE '"+dep+"'")
				 .addEntity("e",jdbc.Empleados.class)
				 //.addEntity("d",jdbc.Departamentos.class)
			     .addJoin("d","e.departamentos");
	
		List <Empleados> lista = q.list();
		// Obtenemos un Iterador y recorremos la lista 
		 Iterator <Empleados> iter = lista.iterator ();
		 Empleados emp = new Empleados(); 
		 while (iter.hasNext())
		 {
				emp = (Empleados) iter.next();//extraer el objeto
				System.out.println(emp.getApellido() +"--"+emp.getSalario());
		  }
	
		
		 session.close();
		 
	}
	public static void selectAllDepartamentos()
	{
		SessionFactory sf = SessionFactoryUtil.getSessionFactory();
		Session session = sf.openSession();
			
		
		Departamentos depar = new Departamentos () ;
		Query q = session.createSQLQuery("select * from Departamentos").addEntity(jdbc.Departamentos.class);
	   
		List <Departamentos> lista = q.list();
		
		// Obtenemos un Iterador y recorremos la lista.
		Iterator <Departamentos> iter = lista.iterator();
		
		while (iter.hasNext())
		{
		//extraemos el objeto 
		depar = (Departamentos) iter.next();
		System.out.println(depar.getDeptNo() +"--"+depar.getDnombre());

		}
		
			
		session.close();
		
		
	}
	
	//utilizando el método list()
		public static void GetAllDepartamentosList()
		{
			SessionFactory sf = SessionFactoryUtil.getSessionFactory();
			Session session = sf.openSession();
				
			
			Departamentos depar = new Departamentos () ;
			Query q = session.createQuery ("from Departamentos");
		   
			List <Departamentos> lista = q.list();
			
			// Obtenemos un Iterador y recorremos la lista.
			Iterator <Departamentos> iter = lista.iterator();
			
			while (iter.hasNext())
			{
			//extraemos el objeto 
			depar = (Departamentos) iter.next();
			System.out.println(depar.getDeptNo() +"--"+depar.getDnombre());

			}
			
				
			session.close();
			
			
		}
		
			
		//utilizando el método iterato()
		public static void GetAllDepartamentosIterate()
		{
			SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
			Session session = sesion.openSession();
			
			Departamentos depar = new Departamentos () ;
			Query q = session.createQuery ("from Departamentos");
			
			q.setFetchSize(5);
			
			Iterator<Departamentos> iter = q.iterate();
			
					
			while (iter.hasNext())
			{
			//extraemos el objeto 
			depar = (Departamentos) iter.next();
			System.out.println(depar.getDeptNo() +"--"+depar.getDnombre());

			}
			
				
			session.close();
			
		}
		
		
		
		public static void GetEmpleadoByDep(int dep)
		{
			SessionFactory sf = SessionFactoryUtil.getSessionFactory();
			Session session = sf.openSession();
			Query q = session.createQuery("from Empleados as e where e.departamentos.deptNo="+dep);
			List <Empleados> lista = q.list();
			// Obtenemos un Iterador y recorremos la lista 
			 Iterator <Empleados> iter = lista.iterator ();
			 Empleados emp = new Empleados(); 
			 while (iter.hasNext())
			 {
					emp = (Empleados) iter.next();//extraer el objeto
					System.out.println(emp.getApellido() +"--"+emp.getSalario());
			  }
		
			
			 session.close();
			 
		}

	public static void TotalesDepartamento()
	{
		SessionFactory sf = SessionFactoryUtil.getSessionFactory();
		Session session = sf.openSession();
	
		Query query = session.createQuery ("select new jdbc.Totales (" +
		" departamentos.deptNo,departamentos.dnombre,COUNT(empNo), AVG (salario)" + ")"+
        " from Empleados"+
		" group by departamentos.deptNo " );
		
		
		Iterator<Totales> it = query.iterate(); 
		while (it.hasNext()) {
		Totales tot =(Totales) it.next();
		
		System.out.println( "Numero Dep:"+tot.getNum_depart()+" Nombre:"+tot.getNombre() +" Salario medio: "
		+tot.getMedia_sal() + " N° emple:" +tot.getNum_empl());
		}
		
		session.close();
		
	
	}
	
	public static void TotalesDepartamentoArrays()
	{
		SessionFactory sf = SessionFactoryUtil.getSessionFactory();
		Session session = sf.openSession();
	
		Query query = session.createQuery ("select  departamentos.deptNo,departamentos.dnombre,"+
		"COUNT(empNo), AVG (salario) from Empleados"+
		" group by departamentos.deptNo " );
		
		
		List<Object [] > filas = query.list() ; //Todas las filas 
		for (int i = 0; i < filas. size () ; i++)
		{
		Object [] filaActual = filas.get (i) ; //Acceso a una fila
		System.out.println ("Dep: "+filaActual [0 ] + " Nombre:" + filaActual [1] + 
		" Empleados: "+ filaActual [2] + " Media : " + filaActual [3] ) ;

		}
		session.close();
		
	
	}
	
	public static void TotalesDepartamentoMap()
	{
		SessionFactory sf = SessionFactoryUtil.getSessionFactory();
		Session session = sf.openSession();

		Query query = session.createQuery("select new map (departamentos.deptNo,departamentos.dnombre,"
				+ "COUNT(empNo), AVG (salario)) from Empleados" 
				+ " group by departamentos.deptNo ");

		List<Map> lista = query.list();// Todas las filas

		for (int i = 0; i < lista.size(); i++) {
			Map mapa = lista.get(i);
			System.out.println("Datos de la fila:" + i); // Datos de la fila
			Set<String> columnasTodas = mapa.keySet();// devuelve las claves del conjunto
			Iterator<String> it = columnasTodas.iterator();
			while (it.hasNext()) {
				String columna = it.next();
				System.out.println("\tColumna: " + columna + ", valor: " + mapa.get(columna));
			}

		}
		session.close();
		
	
	}
	
	
	
	
	public static void ClasesNoAsociadas()
	{
		SessionFactory sf = SessionFactoryUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from Empleados e, Departamentos d where e.departamentos.deptNo=d.deptNo order by e.apellido");
		
		Iterator iter = q.iterate(); 
		while (iter.hasNext()) {
		  Object[] par =(Object[]) iter.next();
		  Empleados em = (Empleados) par[0];
		  Departamentos de = (Departamentos) par[1];
		  System.out.println( em.getApellido() +"--"+ em.getSalario() + "--"+ de.getDnombre()+"--"+ de.getLoc());
		
	     }
		
		session.close();
		
	}
	
	
	
}
