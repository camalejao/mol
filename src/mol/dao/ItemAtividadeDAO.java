package mol.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mol.model.curso.atividade.Atividade;
import mol.model.curso.atividade.ItemAtividade;

public class ItemAtividadeDAO extends DAOGenerico<ItemAtividade> implements IItemAtividadeDAO {

	public ItemAtividadeDAO(EntityManager em) {
		super(em);
	}

	@Override
	public List<ItemAtividade> consultarPorAtividade(Atividade atividade) {
		try {
			TypedQuery<ItemAtividade> query = getEntityManager().createQuery("select i from ItemAtividade i where i.atividade = :atv", ItemAtividade.class);
			query.setParameter("atv", atividade);
            return query.getResultList();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

}
