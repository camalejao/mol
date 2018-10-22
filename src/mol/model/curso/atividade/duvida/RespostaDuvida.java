package mol.model.curso.atividade.duvida;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mol.model.Entidade;
import mol.model.user.Usuario;

@Entity
@Table(name="t_resp_duvidas")
public class RespostaDuvida extends Entidade{
	
	@ManyToOne
	private Usuario autor;
	
	@ManyToOne
	private Duvida duvida;

	
	
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
