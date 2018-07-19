package mol.model.curso.turma;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import mol.model.Entidade;
import mol.model.user.Aluno;

@Entity
@Table(name = "t_resposta")
public class Resposta extends Entidade {

	@ManyToOne
	@JoinColumn(name = "atividade_id")
	private Atividade atividade;

	@ManyToOne
	@JoinColumn(name = "aluno_id")
	private Aluno aluno;

	private double nota;

	@Column(length = 500)
	private String comentarios;
	
	@Column(length = 500, name="obs_professor")
	private String observacoesProfessor;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status_resposta", nullable=false)
	private StatusResposta statusResposta;
	
	@Lob
	@Column(columnDefinition="mediumblob", name = "doc_resposta")
	private byte[] documentoResposta;
	
	@Column(length=50, name="nome_documento")
	private String nomeDocumentoResposta;
	
	@Column(length=160, name="tipo_documento")
	private String tipoDocumentoResposta;
	
	@Transient
	private CommonsMultipartFile upload;

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getObservacoesProfessor() {
		return observacoesProfessor;
	}

	public void setObservacoesProfessor(String observacoesProfessor) {
		this.observacoesProfessor = observacoesProfessor;
	}
	
	public StatusResposta getStatusResposta() {
		return statusResposta;
	}

	public void setStatusResposta(StatusResposta statusResposta) {
		this.statusResposta = statusResposta;
	}

	public byte[] getDocumentoResposta() {
		return documentoResposta;
	}

	public void setDocumentoResposta(byte[] documentoResposta) {
		this.documentoResposta = documentoResposta;
	}
	
	public String getNomeDocumentoResposta() {
		return nomeDocumentoResposta;
	}

	public void setNomeDocumentoResposta(String nomeDocumentoResposta) {
		this.nomeDocumentoResposta = nomeDocumentoResposta;
	}

	public String getTipoDocumentoResposta() {
		return tipoDocumentoResposta;
	}

	public void setTipoDocumentoResposta(String tipoDocumentoResposta) {
		this.tipoDocumentoResposta = tipoDocumentoResposta;
	}

	public CommonsMultipartFile getUpload() {
		return upload;
	}

	public void setUpload(CommonsMultipartFile upload) {
		this.upload = upload;
	}
}
