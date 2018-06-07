package mol.model.materialDidatico;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import mol.model.Entidade;

@Entity
@Table(name="t_material_didatico")
public class MaterialDidatico extends Entidade {
	
	@Column(length=40, nullable=false)
	private String titulo;
	
	@Column(nullable=false)
	private String documento;
	
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private TipoMaterialDidatico tipo;

	
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoMaterialDidatico getTipo() {
		return tipo;
	}

	public void setTipo(TipoMaterialDidatico tipo) {
		this.tipo = tipo;
	}
	
}
