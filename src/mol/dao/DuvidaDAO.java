package mol.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mol.model.curso.atividade.duvida.Duvida;
import mol.model.curso.disciplina.Disciplina;
import mol.model.user.Aluno;

public class DuvidaDAO extends DAOGenerico<Duvida> implements IDuvidaDAO {

	public DuvidaDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Duvida> consultarDuvidasPublicas() {
		try {
			TypedQuery<Duvida> query = getEntityManager().createQuery("select d from Duvida d where d.visibilidade = 'PUBLICA'", Duvida.class);
            return query.getResultList();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

	@Override
	public List<Duvida> consultarDuvidasPorAluno(Aluno a) {
		try {
			TypedQuery<Duvida> query = getEntityManager().createQuery("select d from Duvida d where d.aluno = :aluno", Duvida.class);
            query.setParameter("aluno", a);
			return query.getResultList();   		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

}
