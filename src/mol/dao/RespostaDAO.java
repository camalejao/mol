package mol.dao;

import javax.persistence.EntityManager;

import mol.model.curso.turma.Resposta;

public class RespostaDAO extends DAOGenerico<Resposta> implements IRespostaDAO {

	public RespostaDAO(EntityManager em) {
		super(em);
	}
	
}
