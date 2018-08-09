package mol.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mol.model.curso.disciplina.Topico;
import mol.model.curso.turma.TurmaDisciplina;

public class TopicoDAO extends DAOGenerico<Topico> implements ITopicoDAO {

	public TopicoDAO(EntityManager em) {
		super(em);
	}
	
	@Override
	public List<Topico> consultarPorTurma(TurmaDisciplina td){
		try {
			TypedQuery<Topico> query = getEntityManager().createQuery("select t from Topico t where t.sumario = :s and t.topico = null", Topico.class);
			query.setParameter("s", td.getDisciplina().getSumario());
            return query.getResultList();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}
	
	@Override
	public List<Topico> consultarSubtopicosPorTurma(TurmaDisciplina td){
		try {
			TypedQuery<Topico> query = getEntityManager().createQuery("select t from Topico t where t.sumario = :s and t.topico != null", Topico.class);
			query.setParameter("s", td.getSumarioTurma());
            return query.getResultList();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}
	
}
