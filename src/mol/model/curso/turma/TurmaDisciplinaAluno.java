package mol.model.curso.turma;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mol.model.Entidade;
import mol.model.user.Aluno;

@Entity
@Table(name="t_turma_disciplina_aluno")
public class TurmaDisciplinaAluno extends Entidade {
	
	@ManyToOne
	@JoinColumn(name="turma_disciplina_id")	
	private TurmaDisciplina turmaDisciplina;
	
	@ManyToOne
	@JoinColumn(name="aluno_id")	
	private Aluno aluno;
	
	private Double media;


	public TurmaDisciplina getTurmaDisciplina() {
		return turmaDisciplina;
	}


	public void setTurmaDisciplina(TurmaDisciplina turmaDisciplina) {
		this.turmaDisciplina = turmaDisciplina;
	}


	public Aluno getAluno() {
		return aluno;
	}


	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}


	public Double getMedia() {
		return media;
	}


	public void setMedia(Double media) {
		this.media = media;
	}
	
}
