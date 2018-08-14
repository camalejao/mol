package mol.model.curso.atividade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mol.model.Entidade;

@Entity
@Table(name="t_item_atividade")
public class ItemAtividade extends Entidade {
	
	@ManyToOne
	private Atividade atividade;
	
	private String enunciado;
		
	@OneToMany(mappedBy="item", cascade=CascadeType.ALL)
	private List<Alternativa> alternativas;
		
	private TipoItem tipoItem;

	
	
	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	
	public List<Alternativa> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(List<Alternativa> alternativas) {
		this.alternativas = alternativas;
	} 
	
	public TipoItem getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(TipoItem tipoItem) {
		this.tipoItem = tipoItem;
	}
	
}
