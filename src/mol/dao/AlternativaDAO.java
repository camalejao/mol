package mol.dao;

import javax.persistence.EntityManager;

import mol.model.curso.atividade.Alternativa;

public class AlternativaDAO extends DAOGenerico<Alternativa> implements IAlternativaDAO {

	public AlternativaDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}


}
