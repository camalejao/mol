package mol.model.curso.atividade;

public enum TipoSubmissao {
	ARQUIVO("Envio de Arquivo"), ITENS("Itens Online");
	
	private String tipo;
	
	TipoSubmissao(String tipo){
		this.tipo = tipo;
	}
	
	public String getTipoSubmissao() {
		return tipo;
	}
}
