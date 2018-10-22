package mol.model.curso.atividade.duvida;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import mol.model.Entidade;
import mol.model.curso.atividade.ItemAtividade;
import mol.model.user.Aluno;

@Entity
@Table(name="t_duvida")
public class Duvida extends Entidade {
	
	@NotEmpty @NotBlank
	@Column(length=400, nullable=false)
	private String duvida;
	
	@ManyToOne
	private Aluno aluno;
	
	@ManyToOne
	private ItemAtividade item;

	@Enumerated(EnumType.STRING)
	private VisibilidadeDuvida visibilidade;
	
	
	public String getDuvida() {
		return duvida;
	}

	public void setDuvida(String duvida) {
		this.duvida = duvida;
	}

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

	public VisibilidadeDuvida getVisibilidade() {
		return visibilidade;
	}

	public void setVisibilidade(VisibilidadeDuvida visibilidade) {
		this.visibilidade = visibilidade;
	}
	
}
