package mol.dao;

import java.util.List;

import mol.model.curso.disciplina.Sumario;
import mol.model.curso.disciplina.Topico;
import mol.model.curso.turma.TurmaDisciplina;

public interface ITopicoDAO extends IDAOGenerico<Topico> {

	List<Topico> consultarPorTurma(TurmaDisciplina td);

	List<Topico> consultarSubtopicosPorTurma(TurmaDisciplina td);

	List<Topico> consultarPorSumario(Sumario sumario);

}
