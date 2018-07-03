package mol.model.user;

import java.time.LocalDate;

import javax.persistence.Column;
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
	
	@Column(name="data_inicio")
	private LocalDate dataInicioContrato;
	
	@Column(name="data_termino")
	private LocalDate dataTerminoContrato;
	
	
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

	public LocalDate getDataInicioContrato() {
		return dataInicioContrato;
	}

	public void setDataInicioContrato(LocalDate dataInicioContrato) {
		this.dataInicioContrato = dataInicioContrato;
	}

	public LocalDate getDataTerminoContrato() {
		return dataTerminoContrato;
	}

	public void setDataTerminoContrato(LocalDate dataTerminoContrato) {
		this.dataTerminoContrato = dataTerminoContrato;
	}

}
