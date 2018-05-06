package es.iesvjp.acadt.dao;

import java.util.List;

import es.iesvjp.acadt.model.Estudiante;

public interface IEstudianteDao {

	public void insertEstudiante(Estudiante estudiante);

	public void updateEstudiante(Estudiante estudiante);

	public void deleteEstudiante(Estudiante estudiante);

	public Estudiante findById(int id);
	
	public Estudiante findById(Estudiante estudiante);

	public List<Estudiante> getAll();

	
}
