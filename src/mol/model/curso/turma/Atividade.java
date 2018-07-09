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
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
	
	@Column(length=50, name="nome_documento")
	private String nomeDocumento;
	
	@Column(length=160, name="tipo_documento")
	private String tipoDocumento;
	
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	@Column(name="data_expiracao", nullable=false)
	private LocalDateTime dataExpiracao;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="valor_aprendizagem", nullable=false)
	private NivelAprendizagem nivel;
	
	@Transient
	private CommonsMultipartFile upload;
	
	
	
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
	
	public String getNomeDocumento() {
		return nomeDocumento;
	}

	public void setNomeDocumento(String nomeDocumento) {
		this.nomeDocumento = nomeDocumento;
	}
		
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
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

	public CommonsMultipartFile getUpload() {
		return upload;
	}

	public void setUpload(CommonsMultipartFile upload) {
		this.upload = upload;
	}
	
}
