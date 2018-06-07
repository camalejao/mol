package mol.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mol.model.curso.disciplina.Disciplina;

public class DisciplinaDAO extends DAOGenerico<Disciplina> implements IDisciplinaDAO{

	public DisciplinaDAO(EntityManager em) {
		super(em);
	}
	
	public Disciplina consultarPorSigla(String sigla) {
		try {
			TypedQuery<Disciplina> query = getEntityManager().createQuery("select d from Disciplina d where d.sigla = :si", Disciplina.class);
			query.setParameter("si", sigla);
            return query.getSingleResult();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
        return null;
	}

}
