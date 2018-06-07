package mol.model.user;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mol.model.Entidade;
import mol.model.curso.disciplina.Disciplina;

@Entity
@Table(name="t_monitor")
public class Monitor extends Entidade {
	
	@OneToOne
	@JoinColumn(name="aluno_id")
	private Aluno aluno;
	
	@ManyToOne
	@JoinColumn(name="disciplina_id")
	private Disciplina disciplina;

	
	
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
}
