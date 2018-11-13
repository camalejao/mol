package mol.model.curso.atividade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import mol.model.Entidade;

@Entity
@Table(name="t_item_atividade")
@JsonIgnoreProperties({"id","dataCadastro","dataUltimaAlteracao","usuarioLogado"})
public class ItemAtividade extends Entidade {
	@JsonIgnore
	@ManyToOne
	private Atividade atividade;
	
	@NotEmpty @NotBlank
	@Column(length=600, nullable=false)
	private String enunciado;
	
	@JsonManagedReference
	@OneToMany(mappedBy="item", cascade=CascadeType.ALL)
	private List<Alternativa> alternativas;
		
	private TipoItem tipoItem;
	
	@DecimalMin("0.25")
	private double valor;

	
	
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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
}
