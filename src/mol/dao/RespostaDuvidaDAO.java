package mol.dao;

import javax.persistence.EntityManager;

import mol.model.curso.atividade.duvida.RespostaDuvida;

public class RespostaDuvidaDAO extends DAOGenerico<RespostaDuvida> implements IRespostaDuvidaDAO{

	public RespostaDuvidaDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

}
