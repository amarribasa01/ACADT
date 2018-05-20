package es.iesvjp.acadt;

import java.util.Iterator;

import com.db4o.config.EmbeddedConfiguration;
import com.db4o.constraints.UniqueFieldValueConstraint;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class AccesoDB4o {

	final static String BDEmp = "C:/AD/UT4/DB4O/BDEmpleados.yap";

	public static void main(String[] args) {
		
	  ConsultarBD("Ventas");
	   // GetEmpleadosByName("Pedro");
		
		
		Empleado emp= new Empleado("Pedro", "Lopez");
		//Departamento dep = new Departamento("Contabilidad", "Madrid");
		Departamento dep = new Departamento("Ventas", "Sevilla");
		//Empleado emp = new Empleado("Pepe", "Gomez");

		//RemoveDepartamento(dep);
		
		//RemoveEmpleadoDepartamento(emp, dep);
		//CambiarEmpleadoDepartamento(emp,dep);
//1º nos quedamos los objetos y después actualizamos las relaciones
  //  Insert_Empleado(emp);
	//Insert_Departamento(dep);
	//SetDepartamentoEmpleado(emp, dep);
   //AddEmpleadoDepartamento(emp,dep);
	
	}

	public static ObjectContainer GetConexionBD() {
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		// actualizamos en cascada
		config.common().objectClass(Departamento.class).cascadeOnUpdate(true);
		config.common().objectClass(Departamento.class).cascadeOnDelete(true);
		config.common().objectClass(Empleado.class).cascadeOnUpdate(true);
		// cambiamos el nivel de actualización a 5
		config.common().updateDepth(5);
		config.common().activationDepth(5);
		config.common().objectClass(Departamento.class).objectField("nombre").indexed(true);
		config.common().add(new UniqueFieldValueConstraint(Departamento.class, "nombre"));
		// creamos/abrimos la BD con la configuración creada
		ObjectContainer db = Db4oEmbedded.openFile(config, BDEmp);

		return db;
	}

	/**
	 * 
	 * @param dep: nuevo departamento a insertar pero sin ArrayList<Empleado>
	 */
	public static void Insert_Departamento(Departamento dep) {
		ObjectContainer db = GetConexionBD();// obtener la conexión a la BD
		db.store(dep);// almacenar el objeto
		db.commit();
		db.close(); // cerrar base de datos
	}

	/**
	 * @param emp: nuevo empleado a insertar pero sin el objeto Departamento
	 */
	public static void Insert_Empleado(Empleado emp) {
		ObjectContainer db = GetConexionBD();// obtener la conexión a la BD
		db.store(emp);// almacenamos el objeto empleado
		db.commit();
		db.close(); // cerrar base de datos
	}
	
	/**
	 * @param emp: empleado ya existente en la BD, al cual vamos a asignar el Departamento (@param dep)
	 * @param dep: departamento ya existente en la BD, el cual vamos a asignar al Empleado (@param emp)
	 */
	public static void SetDepartamentoEmpleado(Empleado emp, Departamento dep) {
		ObjectContainer db = GetConexionBD();// obtener la conexión a la BD

		Empleado emp_query=new Empleado(emp.getNombre(), emp.getApellidos());
		Departamento depto_query=new Departamento(dep.getNombre(), dep.getLocalidad());
		
		ObjectSet<Empleado> osemp = db.queryByExample(emp_query);
		ObjectSet<Departamento> osdep = db.queryByExample(depto_query);

		if (osemp.size() > 0) {
			//hemos encontrado el empleado
			Empleado empleado = osemp.next();
			if (osdep.size() > 0) {
				//hemos encontrado el Departamento, por lo tanto actualizamos el campo Departamento
				//del Empleado
				empleado.setDepart(osdep.next());
				//guardamos el objeto Empleado
				db.store(empleado);
			}
		}
		db.commit();
		db.close();

	}

	
	/**
	 * @param emp: Empleado ya existente en la BD, el cual vamos a añadir al ArrayList del Departamento
	 * @param dep: Departamento ya existente en la BD al cual se le va a añadir el empleado
	 */
	public static void AddEmpleadoDepartamento(Empleado emp, Departamento dep) {
		ObjectContainer db = GetConexionBD();// obtener la conexión a la BD
		
		Empleado emp_query=new Empleado(emp.getNombre(), emp.getApellidos());
		Departamento depto_query=new Departamento(dep.getNombre(), dep.localidad);
		//buscamos el empleado
		ObjectSet<Empleado> osemp = db.queryByExample(emp_query);
		//buscamos el departamento
		ObjectSet<Departamento> osdep = db.queryByExample(depto_query);

		if (osdep.size() > 0) {
		//existe el departamento
			Departamento dept = osdep.next();
			
			if (osemp.size() > 0) {
				//existe el empleado a añadir
				dept.addEmpleados(osemp.next());
				//actualizamos el departamento
				db.store(dept);
			}
		}
		db.commit();
		db.close();

	}
	
	/**
	 * @param emp: Empleado ya existente en la BD, el cual vamos a borrar del ArrayList del Departamento
	 * @param dep: Departamento ya existente en la BD al cual se le va a borrar el empleado
	 */
	public static void RemoveEmpleadoDepartamento(Empleado emp, Departamento dep) {
		ObjectContainer db = GetConexionBD();// obtener la conexión a la BD
		
		Empleado emp_query=new Empleado(emp.getNombre(), emp.getApellidos());
		Departamento depto_query=new Departamento(dep.getNombre(), dep.localidad);
		//buscamos el empleado
		ObjectSet<Empleado> osemp = db.queryByExample(emp_query);
		//buscamos el departamento
		ObjectSet<Departamento> osdep = db.queryByExample(depto_query);

		if (osdep.size() > 0) {
		//existe el departamento
			Departamento dept = osdep.next();
			
			if (osemp.size() > 0) {
				Empleado emple=osemp.next();
				//existe el empleado a borrar, actualizamos el departamento
				dept.empleados.remove(emple);
				//borramos el departamento del empleado
				emple.setDepart(null);
				//actualizamos en la BD los objetos empleado y departamento
				db.store(emple);
				db.store(dept);
			}
		}
		db.commit();
		db.close();

	}
	
	public static void RemoveDepartamento(Departamento dep) {
		ObjectContainer db = GetConexionBD();// obtener la conexión a la BD
		Departamento depto_query=new Departamento(dep.getNombre(), dep.localidad);
		
		//buscamos el departamento
		ObjectSet<Departamento> osdep = db.queryByExample(depto_query);

		if (osdep.size() > 0) {
		//existe el departamento, lo borramos
			Departamento dept = osdep.next();
			db.delete(dept);
			
			}
		
		db.commit();
		db.close();

	}
	
	public static void CambiarEmpleadoDepartamento(Empleado emp, Departamento newdep) {

		ObjectContainer db = GetConexionBD();
				
		Empleado emp_query=new Empleado(emp.getNombre(),emp.getApellidos());
		Departamento dpto_query=new Departamento(newdep.getNombre(), newdep.localidad);
		
		ObjectSet<Departamento> rsdpto = db.queryByExample(dpto_query);
		
		ObjectSet<Empleado> rsemp = db.queryByExample(emp_query);

		if(rsdpto.size()>0)
		{//el nuevo departamento del empleado existe
			if(rsemp.size()>0){
				//el empleado existe vamos a obtener su antiguo Departamento
				Empleado empleado=rsemp.next();
				Departamento olddep=empleado.getDepart();
			   //asignamos al empleado su nuevo departamento
				Departamento newdpto=rsdpto.next();
				empleado.setDepart(newdpto);
				
				db.store(empleado);
				db.commit();
				db.close();
				
				//borramos del antiguo departamento al empleado
				RemoveEmpleadoDepartamento(empleado,olddep) ;
				//añadimos al nuevo departamento el empleado
				AddEmpleadoDepartamento(empleado, newdpto);
				
				
			}
			
			
		}else{
			System.out.println("El departamento no existe");
		}

	}

	public static void ModificarDepartamento(String nombre) {

		ObjectContainer db = GetConexionBD();

		Departamento dep = new Departamento(nombre, null);

		ObjectSet<Departamento> rsdepartamentos = db.queryByExample(dep);

		if (rsdepartamentos.size() > 0) {
			Departamento depart = rsdepartamentos.next();
			depart.setNombre("Contabilidades");
			db.store(depart);
			db.commit();

		} else {
			System.out.println("El departamento no existe");
		}

		db.close(); // cerrar base de datos

	}

	public static Departamento GetDepartamento(Departamento dep) {
		Departamento dep_res = null;
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDEmp);
		Departamento dep_cons = new Departamento(dep.nombre, dep.localidad);
		ObjectSet<Departamento> result = db.queryByExample(dep_cons);

		if (result.size() == 0)
			System.out.println("No existen Registros de Departamento..");
		else {
			dep_res = result.next();
		}

		db.close();
		return dep_res;

	}

	public static void GetEmpleadosByName(String nombre) {

		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDEmp);
		Empleado emp = new Empleado(nombre, null, null);
		ObjectSet<Empleado> result = db.queryByExample(emp);
		
		while(result.hasNext())
		{
			Empleado emp1 = result.next();
			System.out.println(emp1.toString());
		}
		
		db.close();

	}

	public static void ConsultarBD(String dep) {
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDEmp);
		Departamento departamento = new Departamento(dep, null);
		ObjectSet<Departamento> result = db.queryByExample(departamento);

		if (result.size() == 0)
			System.out.println("No existen Registros de Departamento..");
		else {
			System.out.println("Número de registros: " + result.size());

			while (result.hasNext()) {
				Departamento d = result.next();
				System.out.println("Nombre:" + d.getNombre() + ", Localidad: " + d.localidad);
				if (d.getEmpleados().size() != 0) {
					Iterator<Empleado> itemp = d.getEmpleados().iterator();
					while (itemp.hasNext()) {
						Empleado emp = itemp.next();
						System.out.println(emp.toString());

					}
				}
				else 
				{
					System.out.println("No hay ningún empleado en el Departamento");
				}

			} // fin while
		}
		db.close(); // cerrar base de datos

	}
}
