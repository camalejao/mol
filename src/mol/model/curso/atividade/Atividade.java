package mol.model.curso.atividade;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import mol.model.Entidade;
import mol.model.curso.turma.TurmaDisciplina;

@Entity
@Table(name="t_atividade")
public class Atividade extends Entidade {
	
	@NotEmpty @NotBlank
	@Size(min=8, max=40)
	@Column(length=40, nullable=false)
	private String titulo;
	
	//@DecimalMin("0.25") @DecimalMax("10")
	//@Column(name="valor_maximo", nullable=false)
	private double valorMaximo;
	
	@ManyToOne
	@JoinColumn(name="turma_disciplina_id")
	private TurmaDisciplina turmaDisciplina;
	
	//@Min(1) @Max(10)
	//@Column(nullable=false)
	//private int peso;
	
	@Enumerated(EnumType.ORDINAL)
	private Unidades unidade;
	
	@Size(max=300)
	@Column(length=300)
	private String descricao;
	
	@Lob
	//@Column(columnDefinition="mediumblob")
	private byte[] documento;
	
	@Column(length=50, name="nome_documento")
	private String nomeDocumento;
	
	@Column(length=160, name="tipo_documento")
	private String tipoDocumento;
	
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	@Column(name="data_expiracao", nullable=false)
	private LocalDateTime dataExpiracao;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="valor_aprendizagem")
	private NivelAprendizagemEnum nivel;
	
	@ManyToOne
	@JoinColumn(name="nivel_aprendizagem_id")
	private NivelAprendizagem nivelAprendizagem;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_submissao")
	private TipoSubmissao tipoSubmissao;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status_atividade")
	private StatusAtividade statusAtividade;
	
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

	/*public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	} */

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

	public NivelAprendizagemEnum getNivel() {
		return nivel;
	}

	public void setNivel(NivelAprendizagemEnum nivel) {
		this.nivel = nivel;
	}

	public CommonsMultipartFile getUpload() {
		return upload;
	}

	public void setUpload(CommonsMultipartFile upload) {
		this.upload = upload;
	}
	
	public NivelAprendizagem getNivelAprendizagem() {
		return nivelAprendizagem;
	}

	public void setNivelAprendizagem(NivelAprendizagem nivelAprendizagem) {
		this.nivelAprendizagem = nivelAprendizagem;
	}

	public TipoSubmissao getTipoSubmissao() {
		return tipoSubmissao;
	}

	public void setTipoSubmissao(TipoSubmissao tipoSubmissao) {
		this.tipoSubmissao = tipoSubmissao;
	}

	public StatusAtividade getStatusAtividade() {
		return statusAtividade;
	}

	public void setStatusAtividade(StatusAtividade statusAtividade) {
		this.statusAtividade = statusAtividade;
	}

	//expirada retorna false, dentro do prazo retorna true
	public boolean verificaExpiracao() {
		if(this.dataExpiracao.isAfter(LocalDateTime.now()))
			return true;
		else
			return false;
	}
	
}
