package mol.dao;

import mol.model.user.Aluno;
import mol.model.user.Monitor;

public interface IMonitorDAO extends IDAOGenerico<Monitor> {

	Monitor consultarPorAluno(Aluno aluno);

}
