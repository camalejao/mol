package mol.model.curso.atividade.duvida;

public enum VisibilidadeDuvida {
	PUBLICA("P�blica"), PRIVADA("Privada");
	
	private String visibilidade;
	
	VisibilidadeDuvida(String visibilidade){
		this.visibilidade = visibilidade;
	}
	
	public String getVisibilidadeDuvida() {
		return visibilidade;
	}
}
