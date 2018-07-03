package mol.dao;

import javax.persistence.EntityManager;

import mol.interceptor.Permissoes;

public class PermissoesDAO extends DAOGenerico<Permissoes> implements IPermissoesDAO{

	public PermissoesDAO(EntityManager em) {
		super(em);
	}

}
