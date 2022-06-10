package semana13;

import java.sql.*;
import java.util.*;

public class app13 {
	
	static DB db = new DB();

	public static void main(String[] args) {
		
		// Generar store procedure para insertar 8 equipos:
		registrarEquipos();
		
		// Crear procedure para mostrar y llamar:
		mostrarEquipos();
	}
	
	public static void registrarEquipos() {
		EquipoDTO uruguay = new EquipoDTO("grupo A", "uruguay", "sudamérica");
		EquipoDTO rusia = new EquipoDTO("grupo A", "rusia", "europa");
		EquipoDTO francia = new EquipoDTO("grupo B", "francia", "europa");
		EquipoDTO peru = new EquipoDTO("grupo B", "peru", "sudamérica");
		EquipoDTO alemania = new EquipoDTO("grupo C", "alemania", "europa");
		EquipoDTO brasil = new EquipoDTO("grupo C", "brasil", "sudamérica");
		
		EquipoDTO.mostrarEquipos();
		
		String sql_insert = "INSERT INTO equipo(grupo,pais,continente) VALUES ";
		ArrayList<EquipoDTO> equipos = EquipoDTO.getEquiposList();
		for (EquipoDTO e : equipos) {
			sql_insert += "('" + e.getGrupo() + "','" + e.getPais() + "','" + e.getContinente() + "'),";
		}
		StringBuffer sb= new StringBuffer(sql_insert);
		sb.deleteCharAt(sb.length()-1);
		sql_insert = sb.toString();
		sql_insert = "CREATE PROCEDURE insertEquipos() BEGIN TRUNCATE TABLE equipo; " + sql_insert + "; END";
		
		String sql_drop_procedure = "DROP PROCEDURE IF EXISTS insertEquipos;";
		
		Connection conn = null;
		try {
			conn = db.getConnection("poo");
			Statement st = conn.createStatement();
			// drop actual procedure
			st.execute(sql_drop_procedure);
			// create new procedure
			st.execute(sql_insert);
			// call procedure
			CallableStatement cs;
			cs = conn.prepareCall("{CALL insertEquipos()}");
			cs.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		EquipoDTO.cleanEquiposJava();
	}
	
	public static void mostrarEquipos() {
		String sql_drop_procedure = "DROP PROCEDURE IF EXISTS listEquipos;";
		String sql_select = "SELECT * FROM equipo";
		sql_select = "CREATE PROCEDURE listEquipos() BEGIN " + sql_select + "; END";
		Connection conn = null;
		try {
			conn = db.getConnection("poo");
			Statement st = conn.createStatement();
			// drop actual procedure
			st.execute(sql_drop_procedure);
			// create new procedure
			st.execute(sql_select);
			// call procedure
			CallableStatement cs;
			cs = conn.prepareCall("{CALL listEquipos()}");
			ResultSet rs = cs.executeQuery();
			
			while (rs.next()) {
				EquipoDTO e = new EquipoDTO(
						rs.getInt("id"),
						rs.getString("grupo"),
						rs.getString("pais"),
						rs.getString("continente")
						);				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		EquipoDTO.mostrarEquipos();
	}

}
