package mol.model.curso.turma;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mol.model.Entidade;
import mol.model.materialDidatico.MaterialDidatico;

@Entity
@Table(name="t_topico_sumario_turma")
public class TopicoSumarioTurma extends Entidade{
	
	@ManyToOne
	@JoinColumn(name="sumario_turma_id")
	private SumarioTurma sumarioTurma;
	
	@ManyToOne
	@JoinColumn(name="topico_pai_id")
	private TopicoSumarioTurma topicoSumarioTurma;
	
	@OneToMany
	private List<MaterialDidatico> materiaisDidaticos;

	
	
	public SumarioTurma getSumarioTurma() {
		return sumarioTurma;
	}

	public void setSumarioTurma(SumarioTurma sumarioTurma) {
		this.sumarioTurma = sumarioTurma;
	}

	public TopicoSumarioTurma getTopicoSumarioTurma() {
		return topicoSumarioTurma;
	}

	public void setTopicoSumarioTurma(TopicoSumarioTurma topicoSumarioTurma) {
		this.topicoSumarioTurma = topicoSumarioTurma;
	}

	public List<MaterialDidatico> getMateriaisDidaticos() {
		return materiaisDidaticos;
	}

	public void setMateriaisDidaticos(List<MaterialDidatico> materiaisDidaticos) {
		this.materiaisDidaticos = materiaisDidaticos;
	}
	
}
