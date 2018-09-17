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
	
	@Override
	public List<ItemResposta> consultarEnviadosPorAlunoAtividade(Aluno aluno, Atividade atividade) {
		try {
			TypedQuery<ItemResposta> query = getEntityManager().createQuery("select ir from ItemResposta ir where ir.aluno = :a and ir.item.atividade = :atv and enviado=1", ItemResposta.class);
			query.setParameter("a", aluno);
			query.setParameter("atv", atividade);
            return query.getResultList();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

	@Override
	public ItemResposta consultarPorIdItemIdAluno(Integer idItem, Integer idAluno) {
		try {
			TypedQuery<ItemResposta> query = getEntityManager().createQuery("select ir from ItemResposta ir where ir.aluno.id = :a and ir.item.id = :item", ItemResposta.class);
			query.setParameter("a", idAluno);
			query.setParameter("item", idItem);
            return query.getSingleResult();
            
		} catch (RuntimeException re) {
	        re.printStackTrace();
	    }
		return null;
	}

	@Override
	public List<ItemResposta> consultarNaoEnviadosPorAlunoAtividade(Aluno aluno, Atividade atividade) {
		try {
			TypedQuery<ItemResposta> query = getEntityManager().createQuery("select ir from ItemResposta ir where ir.aluno = :a and ir.item.atividade = :atv and ir.enviado = FALSE", ItemResposta.class);
			query.setParameter("a", aluno);
			query.setParameter("atv", atividade);
            return query.getResultList();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

	@Override
	public ItemResposta consultarNaoEnviadoPorIdItemIdAluno(Integer idItem, Integer idAluno) {
		try {
			TypedQuery<ItemResposta> query = getEntityManager().createQuery("select ir from ItemResposta ir where ir.aluno.id = :a and ir.item.id = :item and ir.enviado = FALSE", ItemResposta.class);
			query.setParameter("a", idAluno);
			query.setParameter("item", idItem);
            return query.getSingleResult();
            
		} catch (RuntimeException re) {
	        re.printStackTrace();
	    }
		return null;
	}

}
