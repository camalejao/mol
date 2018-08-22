package mol.model.curso.disciplina;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import mol.model.Entidade;

@Entity
@Table(name="t_disciplina")
public class Disciplina extends Entidade{
	
	@Size(min=6, max=40)
	@Column(length=40, nullable=false)
	private String nome;
	
	@Size(min=3, max=5)
	@Column(length=5, nullable=false, unique=true)
	private String sigla;
	
	@OneToOne
	@JoinColumn(name="sumario_id")
	private Sumario sumario;

	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Sumario getSumario() {
		return sumario;
	}

	public void setSumario(Sumario sumario) {
		this.sumario = sumario;
	}
	
}
