package mol.model.curso;

public enum ModalidadeCurso {
	TECNICO("Técnico Integrado"), SUBSEQUENTE("Subsequente"), SUPERIOR("Bacharelado/Licenciatura");

	private String descricao;

	ModalidadeCurso(String d){
		descricao = d;
	}

	public String getDescricao() {
		return descricao;
	}
}
