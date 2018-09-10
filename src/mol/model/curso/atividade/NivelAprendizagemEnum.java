package mol.model.curso.atividade;

public enum NivelAprendizagemEnum {
	FACIL(1, "Fácil"), MEDIO(2, "Médio"), DIFICIL(3, "Difícil");

	private int id;
	private String nivel;

	NivelAprendizagemEnum(int id, String nivel) {
		this.id = id;
		this.nivel = nivel;
	}

	public int getId() {
		return id;
	}

	public String getNivel() {
		return nivel;
	}

	public static NivelAprendizagemEnum getById(int id) {
		for (NivelAprendizagemEnum n : values()) {
			if (n.id == id) {
				return n;
			}
		}
		return null;
	}

}
