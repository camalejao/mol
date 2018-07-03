package mol.dao;

import java.util.List;

import mol.interceptor.Permissoes;
import mol.model.user.TipoUsuario;

public interface IPermissoesDAO extends IDAOGenerico<Permissoes> {
	
	public List<Permissoes> consultarPorTipoUsuario(TipoUsuario tipo);
	
}
