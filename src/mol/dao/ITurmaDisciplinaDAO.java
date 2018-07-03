package mol.dao;

import java.util.List;

import mol.model.curso.turma.TurmaDisciplina;
import mol.model.user.Professor;

public interface ITurmaDisciplinaDAO extends IDAOGenerico<TurmaDisciplina> {

	List<TurmaDisciplina> consultarPorProfessor(Professor p);

}
