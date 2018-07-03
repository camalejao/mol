package mol.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mol.interceptor.Permissoes;
import mol.model.user.TipoUsuario;

public class PermissoesDAO extends DAOGenerico<Permissoes> implements IPermissoesDAO {

	public PermissoesDAO(EntityManager em) {
		super(em);
	}

	@Override
	public List<Permissoes> consultarPorTipoUsuario(TipoUsuario tipo) {
		try {
			TypedQuery<Permissoes> query = getEntityManager()
					.createQuery("select p from Permissoes p where p.tipo = :t", Permissoes.class);
			query.setParameter("t", tipo);
			return query.getResultList();
		} catch (RuntimeException re) {
			re.printStackTrace();
		}
		return null;
	}
}
