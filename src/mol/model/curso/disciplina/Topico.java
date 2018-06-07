package mol.model.curso.disciplina;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mol.model.Entidade;
import mol.model.materialDidatico.MaterialDidatico;

@Entity
@Table(name="t_topico")
public class Topico extends Entidade {
	
	@ManyToOne
	@JoinColumn(name="sumario_id")
	private Sumario sumario;
	
	@ManyToOne
	@JoinColumn(name="topico_pai_id")
	private Topico topico;
	
	@OneToMany
	private List<MaterialDidatico> materiaisDidaticos;

	
	
	public Sumario getSumario() {
		return sumario;
	}

	public void setSumario(Sumario sumario) {
		this.sumario = sumario;
	}

	public Topico getTopico() {
		return topico;
	}

	public void setTopico(Topico topico) {
		this.topico = topico;
	}

	public List<MaterialDidatico> getMateriaisDidaticos() {
		return materiaisDidaticos;
	}

	public void setMateriaisDidaticos(List<MaterialDidatico> materiaisDidaticos) {
		this.materiaisDidaticos = materiaisDidaticos;
	}
	
	
}
