package mol.model.curso.turma;

public enum NivelAprendizagem {
	FACIL(1, "F�cil"), MEDIO(2, "M�dio"), DIFICIL(3, "Dif�cil");

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
