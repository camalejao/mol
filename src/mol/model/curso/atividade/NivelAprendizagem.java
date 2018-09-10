package mol.model.curso.atividade;

import javax.persistence.Entity;
import javax.persistence.Table;

import mol.model.Entidade;

@Entity
@Table(name="t_nivel_aprendizagem")
public class NivelAprendizagem extends Entidade {
		
	private String titulo;
	
	private Integer porcentagem;

	
	
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
	
}
