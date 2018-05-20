package principal;

import jdbc.SessionFactoryUtil;

public class Main {

	public static void main(String[] args) {

		org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);

		//SQL NATIVO
		//DepartamentoQuerys.selectAllDepartamentos();
		// DepartamentoQuerys.selectEmpleadoByDep("VENTAS");
		
			
		//DepartamentoQuerys.TotalesDepartamentoArrays();
		//apartado a)
		DepartamentoQuerys.GetEmpleadoByDep(30);
		//DepartamentoQuerys.GetAllDepartamentosIterate();
		//DepartamentoQuerys.GetAllDepartamentosList();
		
		
		//DepartamentoUtils.DeleteDepartamento(10);
		
		//EmpleadoQuerys.GetEmpleadosByOficio(30,"becario",2);
		//EmpleadoQuerys.DeleteEmpledosByDep(10);
		
	//	DepartamentoUtils.InsertarDepartamento();
		//DepartamentoUtils.InsertarEmpleado();
		//DepartamentoUtils.GetDepartamento(10);
		//DepartamentoUtils.InsertarDepartamento();
		
		//DepartamentoUtils.ActualizarEmpleado();
		
		
		
	
		
		

	}
}
