package mol.model.curso.atividade.analiseDesempenho;

import java.util.ArrayList;
import java.util.List;

public class Scales {
	
	private List<YAxes> yAxes;
	private List<XAxes> xAxes;

	
	public Scales() {
		this.yAxes = new ArrayList<>();
		this.xAxes = new ArrayList<>();
	}
	
	public List<YAxes> getyAxes() {
		return yAxes;
	}

	public void setyAxes(List<YAxes> yAxes) {
		this.yAxes = yAxes;
	}

	public List<XAxes> getxAxes() {
		return xAxes;
	}

	public void setxAxes(List<XAxes> xAxes) {
		this.xAxes = xAxes;
	}
}
