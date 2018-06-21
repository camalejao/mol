package mol.model.curso.turma;

public enum Unidades {
	UM(1), DOIS(2), TRES(3), QUATRO(4);
	
	private int unidade;
	
	Unidades(int u){
		unidade = u;
	}
	
	public int getUnidade() {
		return unidade;
	}
}
