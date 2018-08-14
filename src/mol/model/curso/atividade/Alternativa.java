package mol.model.curso.atividade;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mol.model.Entidade;

@Entity
@Table(name="t_alternativa")
public class Alternativa extends Entidade{

	@ManyToOne
	private ItemAtividade item;
	
	private boolean correta;
	
	private String enunciado;

	
	
	
	public ItemAtividade getItem() {
		return item;
	}

	public void setItem(ItemAtividade item) {
		this.item = item;
	}
	
	public boolean getCorreta() {
		return correta;
	}
	
	public void setCorreta(boolean correta) {
		this.correta = correta;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	
}
