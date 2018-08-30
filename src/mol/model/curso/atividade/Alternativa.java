package mol.model.curso.atividade;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import mol.model.Entidade;

@Entity
@Table(name="t_alternativa")
@JsonIgnoreProperties({"dataCadastro","dataUltimaAlteracao","usuarioLogado"})
public class Alternativa extends Entidade{

	@JsonBackReference
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
