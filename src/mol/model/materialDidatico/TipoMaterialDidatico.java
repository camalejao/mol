package mol.model.materialDidatico;

public enum TipoMaterialDidatico {
	ARQUIVO("Arquivo"), LINK("Link");
	
	private String tipoMaterial;
	
	TipoMaterialDidatico(String tipo){
		tipoMaterial = tipo;
	}
	
	public String getTipoMaterial() {
		return tipoMaterial;
	}
}
