package mol.model.curso.atividade;

public enum StatusAtividade {
	CONSTRUCAO("Em construção"), LIBERADA("Liberada para resposta");
	
	private String statusAtividade;
	
	StatusAtividade (String status){
		this.statusAtividade = status;
	}
	
	public String getStatusAtividade() {
		return statusAtividade;
	}
}
