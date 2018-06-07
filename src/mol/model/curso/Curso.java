package mol.model.curso;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import mol.model.Entidade;

@Entity
@Table(name="t_curso")
public class Curso extends Entidade {
	
	@Column(length=30, nullable=false)
	private String nome;
	
	@Column(name="carga_horaria")
	private int cargaHoraria;
	
	@Enumerated(EnumType.STRING)
	private ModalidadeCurso modalidade;
	
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public ModalidadeCurso getModalidade() {
		return modalidade;
	}

	public void setModalidade(ModalidadeCurso modalidade) {
		this.modalidade = modalidade;
	}
}
