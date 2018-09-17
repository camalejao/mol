package mol.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mol.model.curso.atividade.Atividade;
import mol.model.curso.turma.TurmaDisciplina;
import mol.model.user.Aluno;
import mol.model.user.Professor;

public class AtividadeDAO extends DAOGenerico<Atividade> implements IAtividadeDAO {

	public AtividadeDAO(EntityManager em) {
		super(em);
	}

	@Override
	public List<Atividade> consultarPorProfessor(Professor p) {
		try {
			TypedQuery<Atividade> query = getEntityManager().createQuery("select a from Atividade a where a.turmaDisciplina.professor = :prof", Atividade.class);
			query.setParameter("prof", p);
            return query.getResultList();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}
	
	@Override
	public List<Atividade> consultarPorTurmaDisciplina(TurmaDisciplina td) {
		try {
			TypedQuery<Atividade> query = getEntityManager().createQuery("select a from Atividade a where a.turmaDisciplina = :turmad and a.status='ATIVO'", Atividade.class);
			query.setParameter("turmad", td);
            return query.getResultList();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

	@Override
	public List<Atividade> consultarPorIdTurmaDisciplina(Integer id) {
		try {
			TypedQuery<Atividade> query = getEntityManager().createQuery("select a from Atividade a where a.turmaDisciplina.id = :id", Atividade.class);
			query.setParameter("id", id);
            return query.getResultList();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}
	
	@Override
	public List<Atividade> consultarRespondidas(TurmaDisciplina td, Aluno a){
		try {
			TypedQuery<Atividade> query = getEntityManager().createQuery("select a from Atividade a where a.turmaDisciplina = :turmad and a.status = 'ATIVO' and a.statusAtividade = 'LIBERADA' and exists (select r from Resposta r where r.atividade = a and r.aluno = :aluno )", Atividade.class);
			query.setParameter("turmad", td);
			query.setParameter("aluno", a);
            return query.getResultList();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}
	
	@Override
	public List<Atividade> consultarNaoRespondidas(TurmaDisciplina td, Aluno a){
		try {
			TypedQuery<Atividade> query = getEntityManager().createQuery("select a from Atividade a where a.turmaDisciplina = :turmad and a.status = 'ATIVO' and a.statusAtividade = 'LIBERADA' and not exists (select r from Resposta r where r.atividade = a and r.aluno = :aluno )", Atividade.class);
			query.setParameter("turmad", td);
			query.setParameter("aluno", a);
            return query.getResultList();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

}
