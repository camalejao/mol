package mol.dao;

import javax.persistence.EntityManager;

import mol.model.curso.turma.Turma;

public class TurmaDAO extends DAOGenerico<Turma> implements ITurmaDAO {

	public TurmaDAO(EntityManager em) {
		super(em);
	}
	
}
