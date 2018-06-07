package mol.model.curso.disciplina;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mol.model.Entidade;

@Entity
@Table(name="t_sumario")
public class Sumario extends Entidade {
	
	@OneToOne
	@JoinColumn(name="disciplina_id")
	private Disciplina disciplina;

	
	
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
}
