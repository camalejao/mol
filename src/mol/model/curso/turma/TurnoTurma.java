package mol.model.curso.turma;

public enum TurnoTurma {
	MATUTINO("Manh�"), VESPERTINO("Tarde"), NOTURNO("Noite");

	private String turno;

	TurnoTurma(String t){
		turno = t;
	}

	public String getTurno() {
		return turno;
	}
}