package mol.model.curso.atividade.duvida;

public enum VisibilidadeDuvida {
	PUBLICA("Pública"), PRIVADA("Privada");
	
	private String visibilidade;
	
	VisibilidadeDuvida(String visibilidade){
		this.visibilidade = visibilidade;
	}
	
	public String getVisibilidadeDuvida() {
		return visibilidade;
	}
}
