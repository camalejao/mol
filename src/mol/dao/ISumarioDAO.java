package mol.dao;

import mol.model.curso.disciplina.Disciplina;
import mol.model.curso.disciplina.Sumario;

public interface ISumarioDAO extends IDAOGenerico<Sumario> {

	Sumario consultarPorDisciplina(Disciplina disciplina);

}
