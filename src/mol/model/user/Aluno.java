package mol.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="t_aluno")
@PrimaryKeyJoinColumn(name="id")
public class Aluno extends Usuario {
	
	@NotEmpty @NotBlank
	@Size(min=8, max=10)
	@Column(length=10, nullable=false, unique = true)
	private String matricula;
	
	
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
}
