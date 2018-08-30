package mol.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mol.model.curso.atividade.Atividade;
import mol.model.curso.atividade.ItemResposta;
import mol.model.user.Aluno;

public class ItemRespostaDAO extends DAOGenerico<ItemResposta> implements IItemRespostaDAO {

	public ItemRespostaDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ItemResposta> consultarPorAlunoAtividade(Aluno aluno, Atividade atividade) {
		try {
			TypedQuery<ItemResposta> query = getEntityManager().createQuery("select ir from ItemResposta ir where ir.aluno = :a and ir.item.atividade = :atv", ItemResposta.class);
			query.setParameter("a", aluno);
			query.setParameter("atv", atividade);
            return query.getResultList();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

}
