package mol.model.curso.atividade.analiseDesempenho;

import mol.model.curso.atividade.analiseDesempenho.Data;
import mol.model.curso.atividade.analiseDesempenho.Options;

public class Grafico {
	
	private String type;
	private Data data;
	private Options options;
	
	public Grafico() {
		type = "bar";
	}
	
	public Grafico(String tipo) {
		this.type = tipo;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public Options getOptions() {
		return options;
	}
	public void setOptions(Options options) {
		this.options = options;
	}
	
}