package mol.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mol.model.user.Aluno;
import mol.model.user.Monitor;

public class MonitorDAO extends DAOGenerico<Monitor> implements IMonitorDAO {

	public MonitorDAO(EntityManager em) {
		super(em);
	}

	@Override
	public Monitor consultarPorAluno(Aluno aluno) {
		try {
			TypedQuery<Monitor> query = getEntityManager().createQuery("select m from Monitor m where m.aluno = :a and m.status = 'ATIVO'", Monitor.class);
			query.setParameter("a", aluno);
            return query.getSingleResult();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

}
