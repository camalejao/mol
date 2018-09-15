package mol.model.curso.atividade;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mol.model.Entidade;
import mol.model.curso.turma.TurmaDisciplina;

@Entity
@Table(name="t_nivel_aprendizagem")
public class NivelAprendizagem extends Entidade {
		
	private String titulo;
	
	private Integer porcentagem;

	@ManyToOne
	private TurmaDisciplina turmaDisciplina;
	
	
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(Integer porcentagem) {
		this.porcentagem = porcentagem;
	}

	public TurmaDisciplina getTurmaDisciplina() {
		return turmaDisciplina;
	}

	public void setTurmaDisciplina(TurmaDisciplina turmaDisciplina) {
		this.turmaDisciplina = turmaDisciplina;
	}
	
}
