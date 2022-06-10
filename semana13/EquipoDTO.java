package semana13;

import java.util.*;

public class EquipoDTO {
	
	private int id;
	private String grupo;
	private String pais;
	private String continente;
	
	// Es el mapa que guardará "pais":EquipoDTO
	private static ArrayList<EquipoDTO> equipos = new ArrayList<EquipoDTO>();
	
	public static void mostrarEquipos() {
		System.out.println("--- Equipos:");
		for (EquipoDTO equipoDTO : equipos) {
			System.out.println(
					"id: " + equipoDTO.getId() +
					" / " + equipoDTO.getGrupo() +
					" / " + equipoDTO.getPais() +
					" / " + equipoDTO.getContinente()
					);
		}
	}
	
	public static ArrayList<EquipoDTO> getEquiposList() {
		return equipos;
	}
	
	public static void cleanEquiposJava() {
		equipos.clear();
	}
	
	// Recibe 3 parametros, porque lo utilizaremos antes de insertar a la tabla:
	public EquipoDTO(String grupo, String pais, String continente) {
		this.grupo = grupo;
		this.pais = pais;
		this.continente = continente;
		equipos.add(this);
	}

	// Recibe 4 parametros, porque lo utilizaremos al solicitar el registro creado de la tabla:
	public EquipoDTO(int id, String grupo, String pais, String continente) {
		this.id = id;
		this.grupo = grupo;
		this.pais = pais;
		this.continente = continente;
		equipos.add(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getContinente() {
		return continente;
	}

	public void setContinente(String continente) {
		this.continente = continente;
	}

}
