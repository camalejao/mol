package mol.model.curso.turma;

public enum TipoCalculo {
	SOMA("Somativa"), ARITMETICA("Média Aritmética"), PONDERADA("Média Ponderada");
	
	private String tipoCalculo;

	TipoCalculo(String tC){
		tipoCalculo = tC;
	}

	public String getTipoCalculo() {
		return tipoCalculo;
	}
}
