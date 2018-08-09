package mol.model.materialDidatico;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import mol.model.Entidade;
import mol.model.curso.disciplina.Topico;

@Entity
@Table(name="t_material_didatico")
public class MaterialDidatico extends Entidade {
	
	@Column(length=40, nullable=false)
	private String titulo;
	
	@Lob
	//@Column(columnDefinition="mediumblob")
	private byte[] arquivo;
	
	@Column(length=50, name="nome_material")
	private String nomeMaterial;
	
	@Column(length=160, name="tipo_arquivo")
	private String tipoArquivo;
	
	@Column(length=100, nullable=false)
	private String descricao;
	
	@Transient
	private CommonsMultipartFile upload;
	
	@Enumerated(EnumType.STRING)
	private TipoMaterialDidatico tipo;
	
	@Column(length=300)
	private String link;
	
	@ManyToOne
	private Topico topico;
	
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}
	
	public String getNomeMaterial() {
		return nomeMaterial;
	}

	public void setNomeMaterial(String nomeMaterial) {
		this.nomeMaterial = nomeMaterial;
	}

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
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

	public CommonsMultipartFile getUpload() {
		return upload;
	}

	public void setUpload(CommonsMultipartFile upload) {
		this.upload = upload;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Topico getTopico() {
		return topico;
	}

	public void setTopico(Topico topico) {
		this.topico = topico;
	}
	
}
