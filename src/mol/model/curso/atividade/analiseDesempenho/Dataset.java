package mol.model.curso.atividade.analiseDesempenho;

public class Dataset {
	
	private String backgroundColor;
	private String borderColor;
	private double[] data;
	private String label;
	
	
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public String getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
	public double[] getData() {
		return data;
	}
	public void setData(double[] dados) {
		this.data = dados;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
}