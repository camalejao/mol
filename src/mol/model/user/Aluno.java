package mol.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="t_aluno")
@PrimaryKeyJoinColumn(name="id")
public class Aluno extends Usuario {
	
	@Column(length=9, nullable=false)
	private String matricula;
	
	
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
}
