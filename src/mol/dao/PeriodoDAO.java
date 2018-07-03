package mol.dao;

import javax.persistence.EntityManager;

import mol.model.curso.Periodo;

public class PeriodoDAO extends DAOGenerico<Periodo> implements IPeriodoDAO {

	public PeriodoDAO(EntityManager em) {
		super(em);
	}

}
