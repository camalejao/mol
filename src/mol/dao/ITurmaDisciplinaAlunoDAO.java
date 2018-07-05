package mol.dao;

import java.util.List;

import mol.model.curso.turma.TurmaDisciplinaAluno;
import mol.model.user.Aluno;

public interface ITurmaDisciplinaAlunoDAO extends IDAOGenerico<TurmaDisciplinaAluno> {

	List<TurmaDisciplinaAluno> consultarPorAluno(Aluno a);

}
