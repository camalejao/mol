package mol.model.curso.turma;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mol.model.Entidade;

@Entity
@Table(name="t_atividade")
public class Atividade extends Entidade {
	
	@Column(length=40, nullable=false)
	private String titulo;
	
	@Column(name="valor_maximo", nullable=false)
	private double valorMaximo;
	
	@ManyToOne
	@JoinColumn(name="turma_disciplina_id")
	private TurmaDisciplina turmaDisciplina;

	@Column(nullable=false)
	private int peso;
	
	@Enumerated(EnumType.ORDINAL)
	private Unidades unidade;
	
	@Column(length=300)
	private String descricao;
	
	@Lob
	@Column(columnDefinition="mediumblob")
	private byte[] documento;
	
	@Column(name="data_expiracao", nullable=false)
	private LocalDateTime dataExpiracao;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="valor_aprendizagem", nullable=false)
	private NivelAprendizagem nivel;
	
	
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
		
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public byte[] getDocumento() {
		return documento;
	}

	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}

	public LocalDateTime getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(LocalDateTime dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	public NivelAprendizagem getNivel() {
		return nivel;
	}

	public void setNivel(NivelAprendizagem nivel) {
		this.nivel = nivel;
	}
	
}
