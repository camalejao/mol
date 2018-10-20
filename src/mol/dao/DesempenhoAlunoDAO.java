package mol.dao;

import javax.persistence.EntityManager;

import mol.model.curso.atividade.analiseDesempenho.DesempenhoAluno;

public class DesempenhoAlunoDAO extends DAOGenerico<DesempenhoAluno> implements IDesempenhoAlunoDAO {

	public DesempenhoAlunoDAO(EntityManager em) {
		super(em);
	}

}
