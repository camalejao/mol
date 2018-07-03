package mol.model.curso.turma;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mol.model.Entidade;
import mol.model.user.Aluno;

@Entity
@Table(name="t_resposta")
public class Resposta extends Entidade {
	
	@ManyToOne
	@JoinColumn(name="atividade_id")
	private Atividade atividade;
	
	@ManyToOne
	@JoinColumn(name="aluno_id")
	private Aluno aluno;
	
	private double nota;
	
	@Column(length=500)
	private String observacoes;
	
	@Lob
	@Column(columnDefinition="mediumblob", name="doc_resposta")
	private byte[] documentoResposta;
	
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

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public byte[] getDocumentoResposta() {
		return documentoResposta;
	}

	public void setDocumentoResposta(byte[] documentoResposta) {
		this.documentoResposta = documentoResposta;
	}
	
}
