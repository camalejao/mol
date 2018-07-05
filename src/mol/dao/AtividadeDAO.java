package mol.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mol.model.curso.turma.Atividade;
import mol.model.curso.turma.TurmaDisciplina;
import mol.model.user.Professor;

public class AtividadeDAO extends DAOGenerico<Atividade> implements IAtividadeDAO {

	public AtividadeDAO(EntityManager em) {
		super(em);
	}

	@Override
	public List<Atividade> consultarPorProfessor(Professor p) {
		try {
			TypedQuery<Atividade> query = getEntityManager().createQuery("select a from Atividade a where a.turmaDisciplina.professor = :prof", Atividade.class);
			query.setParameter("prof", p);
            return query.getResultList();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}
	
	@Override
	public List<Atividade> consultarPorTurmaDisciplina(TurmaDisciplina td) {
		try {
			TypedQuery<Atividade> query = getEntityManager().createQuery("select a from Atividade a where a.turmaDisciplina = :turmad and a.status='ATIVO'", Atividade.class);
			query.setParameter("turmad", td);
            return query.getResultList();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

}
