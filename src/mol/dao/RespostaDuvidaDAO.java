package mol.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mol.model.curso.atividade.Atividade;
import mol.model.curso.atividade.duvida.RespostaDuvida;
import mol.model.curso.turma.TurmaDisciplina;
import mol.model.user.Aluno;

public class RespostaDuvidaDAO extends DAOGenerico<RespostaDuvida> implements IRespostaDuvidaDAO{

	public RespostaDuvidaDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<RespostaDuvida> consultarPorAtividade(Atividade atv) {
		try {
			TypedQuery<RespostaDuvida> query = getEntityManager().createQuery("select r from RespostaDuvida r where r.duvida.item.atividade = :a", RespostaDuvida.class);
			query.setParameter("a", atv);
            return query.getResultList();
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

	@Override
	public List<RespostaDuvida> consultarPorTurmaDisciplina(TurmaDisciplina turmaDisc) {
		try {
			TypedQuery<RespostaDuvida> query = getEntityManager().createQuery("select r from RespostaDuvida r where r.duvida.item.atividade.turmaDisciplina = :td", RespostaDuvida.class);
			query.setParameter("td", turmaDisc);
            return query.getResultList();
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

	@Override
	public List<RespostaDuvida> consultarPorAluno(Aluno a) {
		try {
			TypedQuery<RespostaDuvida> query = getEntityManager().createQuery("select r from RespostaDuvida r where r.duvida.aluno = :a", RespostaDuvida.class);
			query.setParameter("a", a);
            return query.getResultList();
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

}
