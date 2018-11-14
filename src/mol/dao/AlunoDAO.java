package mol.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mol.model.StatusEntidade;
import mol.model.curso.turma.TurmaDisciplina;
import mol.model.user.Aluno;
import mol.model.user.TipoUsuario;

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

	@Override
	public List<Aluno> consultarPossiveisMonitores() {
		try {
			TypedQuery<Aluno> query = getEntityManager().createQuery("select a from Aluno a where a.status = :s and a.tipo =:t", Aluno.class);
			query.setParameter("s", StatusEntidade.ATIVO);
			query.setParameter("t", TipoUsuario.ALUNO);
            return query.getResultList();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

	@Override
	public Boolean verificarPorMatricula(String mat) {
		Boolean b;
		if(consultarPorMatricula(mat) != null) {
			b = false;
			return b;
		} else {
			b = true;
			return b;
		}
	}

	@Override
	public List<Aluno> consultarAlunosNaoInseridosNaTurma(TurmaDisciplina td) {
		try {
			TypedQuery<Aluno> query = getEntityManager().createQuery("select a from Aluno a where a.status='ATIVO'"
					+ "and not exists (select tda from TurmaDisciplinaAluno tda where tda.turmaDisciplina = :td and tda.aluno = a)", Aluno.class);
			query.setParameter("td", td);
            return query.getResultList();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

}
