package mol.model.curso.atividade;

public enum StatusAtividade {
	CONSTRUCAO("Em constru��o"), LIBERADA("Liberada para resposta");
	
	private String status;
	
	StatusAtividade (String status){
		this.status = status;
	}
	
	public String getStatusAtividade() {
		return status;
	}
}
