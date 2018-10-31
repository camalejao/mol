package mol.model.curso.atividade.duvida;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import mol.model.Entidade;
import mol.model.user.Usuario;

@Entity
@Table(name="t_resp_duvidas")
public class RespostaDuvida extends Entidade{
	
	@NotEmpty @NotBlank
	@Size(max=400)
	@Column(length = 400, nullable = false)
	private String resposta;
	
	@ManyToOne
	private Usuario autor;
	
	@ManyToOne
	private Duvida duvida;

	
	
	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public Duvida getDuvida() {
		return duvida;
	}

	public void setDuvida(Duvida duvida) {
		this.duvida = duvida;
	}
		
}
