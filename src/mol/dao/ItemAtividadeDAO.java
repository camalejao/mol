package mol.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mol.model.curso.atividade.ItemAtividade;

public class ItemAtividadeDAO extends DAOGenerico<ItemAtividade> implements IItemAtividadeDAO {

	public ItemAtividadeDAO(EntityManager em) {
		super(em);
	}

	@Override
	public List<ItemAtividade> consultarPorIdAtividade(Integer id) {
		try {
			TypedQuery<ItemAtividade> query = getEntityManager().createQuery("select i from ItemAtividade i where i.atividade.id = :atv", ItemAtividade.class);
			query.setParameter("atv", id);
            return query.getResultList();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

}
