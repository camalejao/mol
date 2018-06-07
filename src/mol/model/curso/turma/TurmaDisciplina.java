package mol.model.curso.turma;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mol.model.Entidade;
import mol.model.curso.disciplina.Disciplina;
import mol.model.user.Aluno;
import mol.model.user.Professor;

@Entity
@Table(name="t_turma_disciplina")
public class TurmaDisciplina extends Entidade {
	
	@ManyToOne
	@JoinColumn(name="turma_id")
	private Turma turma;
	
	@ManyToOne
	@JoinColumn(name="disciplina_id")
	private Disciplina disciplina;
	
	@ManyToOne
	@JoinColumn(name="professor_id")
	private Professor professor;
	
	@ManyToMany
	private List<Aluno> alunos;

	
	
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
}
