package mol.model.curso.turma;

public enum TipoCalculo {
	SOMA("Somativa"), ARITMETICA("M�dia Aritm�tica"), PONDERADA("M�dia Ponderada");
	
	private String tipoCalculo;

	TipoCalculo(String tC){
		tipoCalculo = tC;
	}

	public String getTipoCalculo() {
		return tipoCalculo;
	}
}
