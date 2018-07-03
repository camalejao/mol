package mol.model.curso.turma;

public enum NivelAprendizagem {
	FACIL(1, "Fácil"), MEDIO(2, "Médio"), DIFICIL(3, "Difícil");

	private int id;
	private String nivel;

	NivelAprendizagem(int id, String nivel) {
		this.id = id;
		this.nivel = nivel;
	}

	public int getId() {
		return id;
	}

	public String getNivel() {
		return nivel;
	}

	public static NivelAprendizagem getById(int id) {
		for (NivelAprendizagem n : values()) {
			if (n.id == id) {
				return n;
			}
		}
		return null;
	}

}
