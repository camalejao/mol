package mol.model.user;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import mol.model.Entidade;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "t_usuario")
public class Usuario extends Entidade {

	@Column(length = 30, nullable = false)
	private String nome;

	@Column(length = 30, nullable = false, unique = true)
	private String email;

	@Column(length = 64, nullable = false)
	private String senha;
	
	@Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String senhaSHA() {
		return senhaSHA(this.getSenha());
	}

	public static String senhaSHA(String senha) {
		MessageDigest menssagem;
		try {
			menssagem = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException excessao) {
			throw new RuntimeException("Não foi possível gerar a criptografia com o hash SHA-2.");
		}
		menssagem.update(senha.getBytes(), 0, senha.length());
		return new BigInteger(1, menssagem.digest()).toString(16);
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

}
