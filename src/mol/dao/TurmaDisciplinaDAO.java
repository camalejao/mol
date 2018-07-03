package mol.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mol.model.curso.turma.TurmaDisciplina;
import mol.model.user.Professor;

public class TurmaDisciplinaDAO extends DAOGenerico<TurmaDisciplina> implements ITurmaDisciplinaDAO {

	public TurmaDisciplinaDAO(EntityManager em) {
		super(em);
	}
	
	@Override
	public List<TurmaDisciplina> consultarPorProfessor(Professor p) {
		try {
			TypedQuery<TurmaDisciplina> query = getEntityManager().createQuery("select td from TurmaDisciplina td where td.professor = :prof", TurmaDisciplina.class);
			query.setParameter("prof", p);
            return query.getResultList();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}
}
