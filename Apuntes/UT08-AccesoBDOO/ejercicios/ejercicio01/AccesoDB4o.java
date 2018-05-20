package ejercicio01;

import java.util.ArrayList;
import java.util.Scanner;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.constraints.UniqueFieldValueConstraint;

public class AccesoDB4o {

	static String BDEmp = "BDEmpleados.yap";

	/**
	 * @return devuelve un objeto ObjectContainer
	 */
	public static ObjectContainer getConexionBD() {

		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		// actualizamos en cascada
		config.common().objectClass(Departamento.class).cascadeOnUpdate(true);
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
	 * @return devuelve un objeto Departamento con NOMBRE y LOCALIDAD
	 */
	public static Departamento pedirDepartamento() {
		Departamento dep = new Departamento();
		dep.setNombre(pedirCadena("Introduce el NOMBRE del departamento"));
		dep.setLocalidad(pedirCadena("Introduce la LOCALIDAD del departamento"));
		return dep;
	}

	/**
	 * @return devuelve un objeto Empleado con NOMBRE y APELLIDOS
	 */
	public static Empleado pedirEmpleado() {
		Empleado emp = new Empleado();
		emp.setNombre(pedirCadena("Introduce el NOMBRE del empleado"));
		emp.setApellidos(pedirCadena("Introduce el APELLIDO del empleado"));
		return emp;
	}

	/**
	 * @param dep: nuevo departamento a insertar pero sin ArrayList<Empleado>
	 */
	public static void Insert_Departamento(Departamento dep) {
		ObjectContainer db = getConexionBD();// abrimos la conexión a la BD
		db.store(dep);
		db.commit();
		System.out.println("\nDepartamento añadido correctamente");
		db.close();// cerramos la conexión a la BD
	}

	/**
	 * @param emp: nuevo empleado a insertar pero sin el objeto Departamento
	 */
	public static void Insert_Empleado(Empleado emp) {
		ObjectContainer db = getConexionBD();// abrimos la conexión a la BD
		db.store(emp);
		System.out.println("\nEmpleado añadido");
		db.commit();
		db.close();// cerramos la conexión a la BD
	}

	/**
	 * @param emp: empleado ya existente en la BD, al cual vamos a asignar el
	 *            Departamento (@param dep)
	 * @param dep: departamento ya existente en la BD, el cual vamos a asignar al
	 *            Empleado (@param emp)
	 */
	public static void setDepartamentoEmpleado(Empleado emp, Departamento dep) {

		ObjectContainer db = getConexionBD();

		Empleado emp_query = new Empleado(emp.getNombre(), emp.getApellidos());
		Departamento dep_query = new Departamento(dep.getNombre(), dep.getLocalidad());

		ObjectSet<Empleado> osemp = db.queryByExample(emp_query);
		ObjectSet<Departamento> osdep = db.queryByExample(dep_query);

		if (osemp.size() > 0) {
			// hemos encontrado el empleado
			Empleado empleado = osemp.next();
			if (osdep.size() > 0) {
				// hemos encontrado el Departamento, por lo tanto actualizamos
				// el campo Departamento
				// del Empleado
				empleado.setDepart(osdep.next());
				// guardamos el objeto Empleado
				db.store(empleado);
			} else {
				System.out.println("El departamento no existe");
			}
		} else {
			System.out.println("El empleado no existe");
		}

		db.commit();
		db.close();
	}

	/**
	 * @param emp: Empleado ya existente en la BD, el cual vamos a añadir al ArrayList del Departamento
	 * @param dep: Departamento ya existente en la BD al cual se le va a añadir el empleado
	 */
	public static void AddEmpleadoDepartamento(Empleado emp, Departamento dep) {
		ObjectContainer db = getConexionBD();// obtener la conexión a la BD

		Empleado emp_query = new Empleado(emp.getNombre(), emp.getApellidos());
		Departamento depto_query = new Departamento(dep.getNombre(), dep.getLocalidad());
		// buscamos el empleado
		ObjectSet<Empleado> osemp = db.queryByExample(emp_query);
		// buscamos el departamento
		ObjectSet<Departamento> osdep = db.queryByExample(depto_query);

		if (osdep.size() > 0) {
			// existe el departamento
			Departamento dept = osdep.next();

			if (osemp.size() > 0) {
				// existe el empleado a añadir
				dept.addEmpleados(osemp.next());
				// actualizamos el departamento
				db.store(dept);

			} else {
				System.out.println("El empleado no existe");
			}
		} else {
			System.out.println("El departamento no existe");
		}
		db.commit();
		db.close();

	}

	/**
	 * @param emp: Empleado a actualizar su Nombre y Apellidos
	 */
	public static void UpdateEmpleado(Empleado emp) {

		ObjectContainer db = getConexionBD(); // creamos la conexion

		Empleado emp_query = new Empleado(emp.getNombre(), emp.getApellidos());

		ObjectSet<Empleado> osemp = db.queryByExample(emp_query);

		if (osemp.size() > 0) {
			// el empleado exsite
			System.out.println("\nIntroduce los nuevos datos del empleado");
			Empleado empleado = osemp.next();

			empleado.setNombre(pedirCadena("Introduce el nuevo NOMBRE del Empleado"));
			empleado.setNombre(pedirCadena("Introduce el nuevo APELLIDO del Empleado"));
			db.store(empleado); // almacenamos el objeto con los cambios

		} else {
			System.out.println("El empleado no existe.");
		}
		db.commit(); // guardamos los cambios en la BD
		System.out.println("\nInformación actualizada");
		db.close();
	}

	/**
	 * @param dep Departamento a actualizar su Localidad
	 */
	public static void UpdateDepartamento(Departamento dep) {

		ObjectContainer db = getConexionBD();

		Departamento dep_query = new Departamento(dep.getNombre(), dep.getLocalidad());

		ObjectSet<Departamento> osdep = db.queryByExample(dep_query);

		if (osdep.size() > 0) {
			Departamento departamento = osdep.next();
			departamento.setLocalidad(pedirCadena("Introduce la nueva LOCALIDAD del Departamento"));
			db.store(departamento);// almacenamos el objeto con los cambios

		} else {
			System.out.println("El departamento no existe.");
		}
		db.commit(); // guardamos los cambios en la BD
		System.out.println("\nInformación actualizada");
		db.close();
	}

	/**
	 * @param emp:Empleado ya existente en la BD, el cual vamos a borrar del ArrayList del Departamento
	 * @param dep:Departamento ya existente en la BD al cual se le va a borrar el empleado
	 */
	public static void RemoveEmpleadoDepartamento(Empleado emp, Departamento dep) {
		ObjectContainer db = getConexionBD();// obtener la conexión a la BD

		Empleado emp_query = new Empleado(emp.getNombre(), emp.getApellidos());
		Departamento depto_query = new Departamento(dep.getNombre(), dep.getLocalidad());
		// buscamos el empleado
		ObjectSet<Empleado> osemp = db.queryByExample(emp_query);
		// buscamos el departamento
		ObjectSet<Departamento> osdep = db.queryByExample(depto_query);

		if (osdep.size() > 0) {
			// existe el departamento
			Departamento dept = osdep.next();

			if (osemp.size() > 0) {
				Empleado emple = osemp.next();
				// existe el empleado a borrar, actualizamos el departamento
				dept.getEmpleados().remove(emple);
				// borramos el departamento del empleado
				emple.setDepart(null);
				// actualizamos en la BD los objetos empleado y departamento
				db.store(emple);
				db.store(dept);

			} else {
				System.out.println("El empleado no existe");
			}
		} else {
			System.out.println("El departamento no existe");
		}

		db.commit();
		db.close();

	}

	
	/**
	 * @param emp Empleado a cambiar de Departamento
	 * @param newdep Nuevo Departamento al que se va a asignar el Empleado
	 */
	public static void CambiarEmpleadoDepartamento(Empleado emp, Departamento newdep) {

		ObjectContainer db = getConexionBD();

		Empleado emp_query = new Empleado(emp.getNombre(), emp.getApellidos());
		Departamento dpto_query = new Departamento(newdep.getNombre(), newdep.getLocalidad());

		ObjectSet<Departamento> rsdpto = db.queryByExample(dpto_query);

		ObjectSet<Empleado> rsemp = db.queryByExample(emp_query);

		if (rsdpto.size() > 0) {// el nuevo departamento del empleado existe
			if (rsemp.size() > 0) {
				// el empleado existe vamos a obtener su antiguo Departamento
				Empleado empleado = rsemp.next();
				Departamento olddep = empleado.getDepart();
				// asignamos al empleado su nuevo departamento
				Departamento newdpto = rsdpto.next();
				empleado.setDepart(newdpto);

				db.store(empleado);
				db.commit();
				db.close();

				// borramos del antiguo departamento al empleado
				RemoveEmpleadoDepartamento(empleado, olddep);
				// añadimos al nuevo departamento el empleado
				AddEmpleadoDepartamento(empleado, newdpto);

			}

		} else {
			System.out.println("El departamento no existe");
		}

	}

	/**
	 * @param dep Departamento a eliminar
	 */
	public static void deleteDepartamento(Departamento dep) {

		ObjectContainer db = getConexionBD();

		ObjectSet<Departamento> osdep = db.queryByExample(dep);

		if (osdep.size() > 0) {

			Departamento departamento = osdep.next();
			ArrayList<Empleado> empleados = departamento.getEmpleados();
			//todos los empleados asociados al Departamento a eliminar actualizamos su
			//campo Departamento a NULL
			for (Empleado emp : empleados) {
				emp.setDepart(null);
			}
			db.delete(departamento); // borramos el departamento
		} else {
			System.out.println("El Departamento no existe");
		}
		db.commit();
		System.out.println("Departamento elimidado");
		db.close();
	}

	
	/**
	 * Muestra todos los Departamentos existentes en la BD
	 */
	public static void GetAllDepartamentos() {

		ObjectContainer db = getConexionBD();
		ObjectSet<Departamento> osdep = db.queryByExample(Departamento.class);

		System.out.println("\nDepartamentos existentes:"+osdep.size());
		
		while (osdep.hasNext()) {
			Departamento dep = osdep.next();
			System.out.println("\nNombre: " + dep.getNombre());
			System.out.println("Localidad: " + dep.getLocalidad());
			for (int i = 0; i < dep.getEmpleados().size(); i++) {
				System.out.println(dep.getEmpleados().get(i));
			}
		}
	
		db.close();
	}

	/**
	 * Muestra todos los Empleados existentes en la BD
	 */
	public static void GetAllEmpleados() {
		ObjectContainer db = getConexionBD();
		ObjectSet<Empleado> osemp = db.queryByExample(Empleado.class);

		System.out.println("\nEmpleados existentes:"+osemp.size());
		while (osemp.hasNext()) {
			Empleado emp = osemp.next();
		System.out.println(emp.toString());

		}

		db.close();
	}

	public static String pedirCadena(String cadena) {
		Scanner entrada = new Scanner(System.in);
		System.out.println(cadena);
		return entrada.nextLine();
	}
}
