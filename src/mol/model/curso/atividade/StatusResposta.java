package mol.model.curso.atividade;

public enum StatusResposta {
	CORRIGIDA("Corrigida"), NAO_CORRIGIDA("Não corrigida");
	
	private String status;
	
	StatusResposta(String status){
		this.status = status;
	}
	
	public String getStatusResposta() {
		return status;
	}
}
