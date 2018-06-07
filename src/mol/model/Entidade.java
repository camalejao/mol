package mol.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import mol.model.user.Usuario;

@MappedSuperclass
public abstract class Entidade {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private StatusEntidade status;
	
	//@Temporal(TemporalType.DATE)
	@Column(name="data_ultima_alteracao")
	private LocalDate dataUltimaAlteracao;
	
	@OneToOne
	@JoinColumn(name="usuario_logado")
	private Usuario usuarioLogado;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StatusEntidade getStatus() {
		return status;
	}

	public void setStatus(StatusEntidade status) {
		this.status = status;
	}

	public LocalDate getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(LocalDate dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}
