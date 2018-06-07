package mol.dao;

import java.util.List;

import mol.model.user.Usuario;

public interface IUsuarioDAO extends IDAOGenerico<Usuario> {
	
	public Usuario consultarPorEmail(String email);
	
	public List<Usuario> consultarPorNome(String nome);
}
