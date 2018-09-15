package mol.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mol.model.curso.atividade.NivelAprendizagem;
import mol.model.curso.turma.TurmaDisciplina;

public class NivelAprendizagemDAO extends DAOGenerico<NivelAprendizagem> implements INivelAprendizagemDAO {

	public NivelAprendizagemDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<NivelAprendizagem> consultarPorTurmaDisciplina(TurmaDisciplina td) {
		try {
			TypedQuery<NivelAprendizagem> query = getEntityManager().createQuery("select na from NivelAprendizagem na where na.turmaDisciplina = :td", NivelAprendizagem.class);
			query.setParameter("td", td);
			return query.getResultList();
			
		} catch (RuntimeException re) {
            re.printStackTrace();
        }
		return null;
	}

}
