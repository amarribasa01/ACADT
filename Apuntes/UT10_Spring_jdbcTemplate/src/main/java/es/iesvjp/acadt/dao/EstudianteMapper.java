package es.iesvjp.acadt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.iesvjp.acadt.model.Estudiante;

public class EstudianteMapper implements RowMapper<Estudiante> {

//método para mapear el resultado de la consulta SQL a un objeto estudiante
	/* 
	 * rs: resultSet a mapear
	 * rowNum: numero de fila actual
	 */
	public Estudiante mapRow(ResultSet rs, int rowNum) throws SQLException {
		Estudiante student = new Estudiante();
	      student.setId(rs.getInt("id"));
	      student.setNombre(rs.getString("nombre"));
	      student.setEdad(rs.getInt("edad"));	      
		return student;
	}
}

