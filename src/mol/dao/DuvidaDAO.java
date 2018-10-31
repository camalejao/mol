package mol.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mol.model.curso.atividade.Atividade;
import mol.model.curso.atividade.duvida.Duvida;
import mol.model.curso.disciplina.Disciplina;
import mol.model.curso.turma.TurmaDisciplina;
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

	@Override
	public List<Duvida> consultarDuvidasPorAtividade(Atividade atv) {
		try {
			TypedQuery<Duvida> query = getEntityManager().createQuery("select d from Duvida d where d.item.atividade = :atv", Duvida.class);
            query.setParameter("atv", atv);
			return query.getResultList();   		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

	@Override
	public List<Duvida> consultarDuvidasPublicasPorAtividade(Atividade atv) {
		try {
			TypedQuery<Duvida> query = getEntityManager().createQuery("select d from Duvida d where d.item.atividade = :atv and d.visibilidade = 'PUBLICA'", Duvida.class);
            query.setParameter("atv", atv);
			return query.getResultList();   		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

	@Override
	public List<Duvida> consultarDuvidasPorTurmaDisciplina(TurmaDisciplina turmaDisciplina) {
		try {
			TypedQuery<Duvida> query = getEntityManager().createQuery("select d from Duvida d where d.item.atividade.turmaDisciplina = :td", Duvida.class);
            query.setParameter("td", turmaDisciplina);
			return query.getResultList();   		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

	@Override
	public List<Duvida> consultarDuvidasPorDisciplina(Disciplina disciplina) {
		try {
			TypedQuery<Duvida> query = getEntityManager().createQuery("select d from Duvida d where d.item.atividade.turmaDisciplina.disciplina = :d", Duvida.class);
            query.setParameter("d", disciplina);
			return query.getResultList();   		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

}
