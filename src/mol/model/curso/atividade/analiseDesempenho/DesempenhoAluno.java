package mol.model.curso.atividade.analiseDesempenho;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mol.model.Entidade;
import mol.model.curso.turma.TurmaDisciplinaAluno;

@Entity
@Table(name="t_desempenho_aluno")
public class DesempenhoAluno extends Entidade{
	
	private Integer nivel;
	
	private double percentual;
	
	@ManyToOne
	@JoinColumn(name="turma_disc_aluno")
	private TurmaDisciplinaAluno turmaDisciplinaAluno;

	
	
	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public double getPercentual() {
		return percentual;
	}

	public void setPercentual(double percentual) {
		this.percentual = percentual;
	}

	public TurmaDisciplinaAluno getTurmaDisciplinaAluno() {
		return turmaDisciplinaAluno;
	}

	public void setTurmaDisciplinaAluno(TurmaDisciplinaAluno turmaDisciplinaAluno) {
		this.turmaDisciplinaAluno = turmaDisciplinaAluno;
	}
}
