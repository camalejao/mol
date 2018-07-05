package mol.dao;

import java.util.List;

import mol.model.curso.turma.Atividade;
import mol.model.curso.turma.TurmaDisciplina;
import mol.model.user.Professor;

public interface IAtividadeDAO extends IDAOGenerico<Atividade> {
	List<Atividade> consultarPorProfessor(Professor p);

	List<Atividade> consultarPorTurmaDisciplina(TurmaDisciplina td);
}
