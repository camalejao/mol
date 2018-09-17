package mol.model.curso.atividade;

public enum TipoSubmissao {
	ARQUIVO("Envio de Arquivo"), ITENS("Itens Online");
	
	private String tipoSubmissao;
	
	TipoSubmissao(String tipo){
		this.tipoSubmissao = tipo;
	}
	
	public String getTipoSubmissao() {
		return tipoSubmissao;
	}
}
