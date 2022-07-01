package semana16;

public class EquipoVO {
	
	private int id;
	private String pais;
	private int puntaje;
	private String grupo;
	
	public EquipoVO() {
		
	}
	
	public EquipoVO(String pais, int puntaje, String grupo) {
		this.pais = pais;
		this.puntaje = puntaje;
		this.grupo = grupo;
	}
	
	public EquipoVO(int id, String pais, int puntaje, String grupo) {
		this.id = id;
		this.pais = pais;
		this.puntaje = puntaje;
		this.grupo = grupo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	

}
