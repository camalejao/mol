package mol.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mol.model.user.Professor;

public class ProfessorDAO extends DAOGenerico<Professor> implements IProfessorDAO {

	public ProfessorDAO(EntityManager em) {
		super(em);
	}
	
	public Professor consultarPorMatricula(String mat) {
		try {
			TypedQuery<Professor> query = getEntityManager().createQuery("select p from Professor p where p.matricula = :mat", Professor.class);
			query.setParameter("mat", mat);
            return query.getSingleResult();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
        return null;
	}

	@Override
	public List<Professor> consultarPorNome(String nome) {
		try {
			TypedQuery<Professor> query = getEntityManager().createQuery("select p from Professor p where p.nome like :nome", Professor.class);
			query.setParameter("nome", "%" + nome + "%");
            return query.getResultList();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}
	
}
