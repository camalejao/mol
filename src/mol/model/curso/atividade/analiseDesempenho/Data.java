package mol.model.curso.atividade.analiseDesempenho;

import java.util.ArrayList;
import java.util.List;

public class Data {
	
	private String[] labels;
	private List<Dataset> datasets;
	
	
	
	public Data() {
		datasets = new ArrayList<>();
	}
	
	public String[] getLabels() {
		return labels;
	}
	public void setLabels(String[] labels) {
		this.labels = labels;
	}
	public List<Dataset> getDatasets() {
		return datasets;
	}
	public void setDatasets(List<Dataset> datasets) {
		this.datasets = datasets;
	}
	
}
