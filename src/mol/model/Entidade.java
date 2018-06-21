package mol.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import mol.model.user.Usuario;

@MappedSuperclass
public abstract class Entidade {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private StatusEntidade status;
	
	@CreationTimestamp
	@Column(name="data_cadastro")
	private LocalDateTime dataCadastro;
	
	//@Temporal(TemporalType.DATE)
	@UpdateTimestamp
	@Column(name="data_ultima_alteracao")
	private LocalDateTime dataUltimaAlteracao;
	
	@ManyToOne
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

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataUltimaAlteracao = dataCadastro;
	}
	
	public LocalDateTime getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(LocalDateTime dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}
