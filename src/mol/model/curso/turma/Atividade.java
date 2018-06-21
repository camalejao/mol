package mol.model.curso.turma;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mol.model.Entidade;

@Entity
@Table(name="t_atividade")
public class Atividade extends Entidade {
	
	@Column(length=40, nullable=false)
	private String nome;
	
	@Column(name="valor_maximo", nullable=false)
	private double valorMaximo;
	
	@ManyToOne
	@JoinColumn(name="turma_disciplina_id")
	private TurmaDisciplina turmaDisciplina;

	@Column(nullable=false)
	private int peso;
	
	@Enumerated(EnumType.ORDINAL)
	private Unidades unidade;
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(double valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	public TurmaDisciplina getTurmaDisciplina() {
		return turmaDisciplina;
	}

	public void setTurmaDisciplina(TurmaDisciplina turmaDisciplina) {
		this.turmaDisciplina = turmaDisciplina;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public Unidades getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidades unidade) {
		this.unidade = unidade;
	}
		
}
