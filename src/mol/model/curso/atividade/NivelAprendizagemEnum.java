package mol.model.curso.atividade;

public enum NivelAprendizagemEnum {
	FACIL(1, "F�cil"), MEDIO(2, "M�dio"), DIFICIL(3, "Dif�cil");

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
