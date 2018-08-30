package mol.model.curso.atividade;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mol.model.Entidade;
import mol.model.user.Aluno;

@Entity
@Table(name="t_item_resposta")
public class ItemResposta extends Entidade {
	
	@ManyToOne
	Aluno aluno;
	
	@ManyToOne
	ItemAtividade item;
	
	String texto;
	
	@ManyToOne
	Alternativa alternativa;

	
	

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public ItemAtividade getItem() {
		return item;
	}

	public void setItem(ItemAtividade item) {
		this.item = item;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Alternativa getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(Alternativa alternativa) {
		this.alternativa = alternativa;
	}	
}
