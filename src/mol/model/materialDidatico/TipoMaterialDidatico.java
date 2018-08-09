package mol.model.materialDidatico;

public enum TipoMaterialDidatico {
	SLIDE("Slide"), APOSTILA("Apostila"), LINK("Link");
	
	private String tipoMaterial;
	
	TipoMaterialDidatico(String tipo){
		tipoMaterial = tipo;
	}
	
	public String getTipoMaterial() {
		return tipoMaterial;
	}
}
