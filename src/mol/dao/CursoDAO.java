package mol.dao;

import javax.persistence.EntityManager;

import mol.model.curso.Curso;

public class CursoDAO extends DAOGenerico<Curso> implements ICursoDAO {

	public CursoDAO(EntityManager em) {
		super(em);
	}

}
