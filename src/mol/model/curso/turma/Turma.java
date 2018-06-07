package mol.model.curso.turma;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mol.model.Entidade;
import mol.model.curso.Curso;
import mol.model.curso.Periodo;

@Entity
@Table(name="t_turma")
public class Turma extends Entidade {
	
	@Column(nullable=false)
	private String identificacao;
	
	@ManyToOne
	@JoinColumn(name="curso_id")
	private Curso curso;
	
	@ManyToOne
	@JoinColumn(name="periodo_id")
	private Periodo periodo;
	
	@Enumerated(EnumType.STRING)
	private TurnoTurma turno;

	
	
	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public TurnoTurma getTurno() {
		return turno;
	}

	public void setTurno(TurnoTurma turno) {
		this.turno = turno;
	}
	
}
