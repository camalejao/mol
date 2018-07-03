package mol.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="t_professor")
@PrimaryKeyJoinColumn(name="id")
public class Professor extends Usuario {
	
	@Column(length=8, nullable=false, unique = true)
	private String matricula;



	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
}
