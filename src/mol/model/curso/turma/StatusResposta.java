package mol.model.curso.turma;

public enum StatusResposta {
	CORRIGIDA("Corrigida"), NAO_CORRIGIDA("N�o corrigida");
	
	private String status;
	
	StatusResposta(String status){
		this.status = status;
	}
	
	public String getStatusResposta() {
		return status;
	}
}
