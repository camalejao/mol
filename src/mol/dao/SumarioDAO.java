package mol.dao;

import javax.persistence.EntityManager;

import mol.model.curso.disciplina.Sumario;

public class SumarioDAO extends DAOGenerico<Sumario> implements ISumarioDAO {

	public SumarioDAO(EntityManager em) {
		super(em);
	}

}
