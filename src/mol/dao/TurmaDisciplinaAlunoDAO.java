package mol.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mol.model.curso.turma.TurmaDisciplina;
import mol.model.curso.turma.TurmaDisciplinaAluno;
import mol.model.user.Aluno;

public class TurmaDisciplinaAlunoDAO extends DAOGenerico<TurmaDisciplinaAluno>
		implements ITurmaDisciplinaAlunoDAO {

	public TurmaDisciplinaAlunoDAO(EntityManager em) {
		super(em);
	}
	
	@Override
	public List<TurmaDisciplinaAluno> consultarPorAluno(Aluno a) {
		try {
			TypedQuery<TurmaDisciplinaAluno> query = getEntityManager().createQuery("select tda from TurmaDisciplinaAluno tda where tda.aluno = :aluno", TurmaDisciplinaAluno.class);
			query.setParameter("aluno", a);
            return query.getResultList();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

	@Override
	public TurmaDisciplinaAluno consultarPorAlunoETurmaDisciplina(Aluno a, TurmaDisciplina td) {
		try {
			TypedQuery<TurmaDisciplinaAluno> query = getEntityManager().createQuery("select tda from TurmaDisciplinaAluno tda where tda.aluno = :aluno and tda.turmaDisciplina = :turmad", TurmaDisciplinaAluno.class);
			query.setParameter("aluno", a);
			query.setParameter("turmad", td);
            return query.getSingleResult();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

	@Override
	public List<TurmaDisciplinaAluno> consultarPorTurmaDisciplina(TurmaDisciplina td) {
		try {
			TypedQuery<TurmaDisciplinaAluno> query = getEntityManager().createQuery("select tda from TurmaDisciplinaAluno tda where tda.turmaDisciplina = :turmad", TurmaDisciplinaAluno.class);
			query.setParameter("turmad", td);
            return query.getResultList();		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}
}
