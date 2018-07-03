package mol.dao;

import javax.persistence.EntityManager;

import mol.model.curso.turma.TurmaDisciplinaAluno;

public class TurmaDisciplinaAlunoDAO extends DAOGenerico<TurmaDisciplinaAluno>
		implements ITurmaDisciplinaAlunoDAO {

	public TurmaDisciplinaAlunoDAO(EntityManager em) {
		super(em);
	}

}
