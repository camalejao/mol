package mol.model.curso.disciplina;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mol.model.Entidade;
import mol.model.curso.turma.TurmaDisciplina;

@Entity
@Table(name="t_sumario")
public class Sumario extends Entidade {
	
	@OneToOne(mappedBy="sumario")
	private Disciplina disciplina;
	
	@OneToOne(mappedBy="sumarioTurma")
	private TurmaDisciplina turmaDisciplina;
	
	

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public TurmaDisciplina getTurmaDisciplina() {
		return turmaDisciplina;
	}

	public void setTurmaDisciplina(TurmaDisciplina turmaDisciplina) {
		this.turmaDisciplina = turmaDisciplina;
	}
		
}
