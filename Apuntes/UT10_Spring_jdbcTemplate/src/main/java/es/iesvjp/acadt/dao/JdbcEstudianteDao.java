package es.iesvjp.acadt.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.iesvjp.acadt.model.Estudiante;

@Repository("JdbcEstudianteDao")
public class JdbcEstudianteDao implements IEstudianteDao {
	
  private JdbcTemplate jdbcTemplate;
		
		
  @Autowired
  public void setDataSource(DataSource dataSource) {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
		
  public void insertEstudiante(Estudiante estudiante) {
	this.jdbcTemplate.update("INSERT INTO ESTUDIANTE(NOMBRE, EDAD) VALUES (?,?)", new Object[]{estudiante.getNombre(),
                                              estudiante.getEdad()});
		
	}

public void updateEstudiante(Estudiante estudiante) {
  this.jdbcTemplate.update("UPDATE ESTUDIANTE SET NOMBRE=?, EDAD=? WHERE ID=?", new Object[]{estudiante.getNombre(),
                      estudiante.getEdad(),estudiante.getId()});
	}

	
public void deleteEstudiante(Estudiante estudiante) {
  this.jdbcTemplate.update("DELETE FROM ESTUDIANTE WHERE ID = ?", estudiante.getId());
		
	}
public Estudiante findById(int id) {
	return this.jdbcTemplate.queryForObject("SELECT * FROM ESTUDIANTE WHERE ID = ?",
			new EstudianteMapper(), id);
}


	
public List<Estudiante> getAll() {
	return jdbcTemplate.query("SELECT * FROM ESTUDIANTE", 	new EstudianteMapper());
}



}


