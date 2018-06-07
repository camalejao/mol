package mol.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mol.model.user.Aluno;

public class AlunoDAO extends DAOGenerico<Aluno> implements IAlunoDAO{

	public AlunoDAO(EntityManager em) {
		super(em);
	}

	@Override
	public Aluno consultarPorMatricula(String mat) {
		try {
			TypedQuery<Aluno> query = getEntityManager().createQuery("select a from Aluno a where a.matricula = :mat", Aluno.class);
			query.setParameter("mat", mat);
            return query.getSingleResult();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
        return null;
	}

	@Override
	public List<Aluno> consultarPorNome(String nome) {
		try {
			TypedQuery<Aluno> query = getEntityManager().createQuery("select a from Aluno a where a.nome like :nome", Aluno.class);
			query.setParameter("nome", "%" + nome + "%");
            return query.getResultList();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

}
