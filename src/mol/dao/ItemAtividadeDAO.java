package mol.dao;

import javax.persistence.EntityManager;

import mol.model.curso.turma.ItemAtividade;

public class ItemAtividadeDAO extends DAOGenerico<ItemAtividade> implements IItemAtividadeDAO {

	public ItemAtividadeDAO(EntityManager em) {
		super(em);
	}

}
