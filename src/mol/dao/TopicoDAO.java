package mol.dao;

import javax.persistence.EntityManager;

import mol.model.curso.disciplina.Topico;

public class TopicoDAO extends DAOGenerico<Topico> implements ITopicoDAO {

	public TopicoDAO(EntityManager em) {
		super(em);
	}

}
