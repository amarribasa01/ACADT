package es.iesvjp.acadt.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import es.iesvjp.acadt.model.Estudiante;

@Repository("JdbcEstudianteDao")
public class JdbcEstudianteDao implements IEstudianteDao {
	
	private NamedParameterJdbcTemplate jdbcNamedParameterTemplate;
		
		
	@Autowired
	public void setDataSource(DataSource dataSource) {
			this.jdbcNamedParameterTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
		
	public void insertEstudiante(Estudiante estudiante) {
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(estudiante);	
		this.jdbcNamedParameterTemplate.update("INSERT INTO ESTUDIANTE(NOMBRE, EDAD) VALUES (:nombre,:edad)",
				parameterSource);
		
	}

	public void updateEstudiante(Estudiante estudiante) {
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(estudiante);
		this.jdbcNamedParameterTemplate.update("UPDATE ESTUDIANTE SET NOMBRE=:nombre, EDAD=:edad WHERE ID=:id",
				parameterSource);
		
	}

	
	public void deleteEstudiante(Estudiante estudiante) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("id",estudiante.getId());
		
		this.jdbcNamedParameterTemplate.update("DELETE FROM ESTUDIANTE WHERE ID = ?", parameters);
		
	}


	
	public Estudiante findById(Estudiante estudiante) {
//		MapSqlParameterSource parameters = new MapSqlParameterSource();
//		parameters.addValue("id",id);
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(estudiante);
		return this.jdbcNamedParameterTemplate.queryForObject("SELECT * FROM ESTUDIANTE WHERE ID =:id",
		parameters, BeanPropertyRowMapper.newInstance(Estudiante.class));
	}

	public Estudiante findById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id",id);
		
		return this.jdbcNamedParameterTemplate.queryForObject("SELECT * FROM ESTUDIANTE WHERE ID =:id",
		parameters, new EstudianteMapper());
	}


	public List<Estudiante> getAll() {
		return this.jdbcNamedParameterTemplate.query("SELECT * FROM ESTUDIANTE", 
				new EstudianteMapper());
	}


}
