package mol.model.curso;

public enum EntradaPeriodo {
	PRIMEIRO_SEMESTRE(1), SEGUNDO_SEMESTRE(2);
	
	private int semestre;
	
	EntradaPeriodo(int s){
		semestre = s;
	}
	
	public int getSemestre() {
		return semestre;
	}
}
