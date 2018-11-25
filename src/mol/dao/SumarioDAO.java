package mol.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mol.model.curso.disciplina.Disciplina;
import mol.model.curso.disciplina.Sumario;
import mol.model.curso.disciplina.Topico;

public class SumarioDAO extends DAOGenerico<Sumario> implements ISumarioDAO {

	public SumarioDAO(EntityManager em) {
		super(em);
	}

	@Override
	public Sumario consultarPorDisciplina(Disciplina disciplina) {
		try {
			TypedQuery<Sumario> query = getEntityManager().createQuery("select s from Sumario s where s.disciplina = :d order by id", Sumario.class);
			query.setParameter("d", disciplina);
            return query.getSingleResult();
            		
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

}
