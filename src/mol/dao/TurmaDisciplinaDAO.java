package mol.dao;

import javax.persistence.EntityManager;

import mol.model.curso.turma.TurmaDisciplina;

public class TurmaDisciplinaDAO extends DAOGenerico<TurmaDisciplina> implements ITurmaDisciplinaDAO {

	public TurmaDisciplinaDAO(EntityManager em) {
		super(em);
	}

}
