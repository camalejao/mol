package mol.model.user;

public enum TipoUsuario {
	ALUNO("Aluno"), PROFESSOR("Professor"), MONITOR("Monitor"), ADMINISTRADOR("Administrador");
	
	private String tipoUsuario;
	
	TipoUsuario(String tipo){
		this.tipoUsuario = tipo;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}
	
}
