package mol.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mol.model.curso.turma.Atividade;
import mol.model.curso.turma.Resposta;

public class RespostaDAO extends DAOGenerico<Resposta> implements IRespostaDAO {

	public RespostaDAO(EntityManager em) {
		super(em);
	}

	@Override
	public List<Resposta> consultarPorAtividade(Atividade atv) {
		try {
			TypedQuery<Resposta> query = getEntityManager().createQuery("select r from Resposta r where r.atividade = :atv", Resposta.class);
			query.setParameter("atv", atv);
            return query.getResultList();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

	@Override
	public List<Resposta> consultarPorIdAtividade(Integer id) {
		try {
			TypedQuery<Resposta> query = getEntityManager().createQuery("select r from Resposta r where r.atividade.id = :atv", Resposta.class);
			query.setParameter("atv", id);
            return query.getResultList();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}
	
}
