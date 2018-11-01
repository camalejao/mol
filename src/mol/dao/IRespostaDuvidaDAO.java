package mol.dao;

import java.util.List;

import mol.model.curso.atividade.Atividade;
import mol.model.curso.atividade.duvida.RespostaDuvida;
import mol.model.curso.disciplina.Disciplina;
import mol.model.curso.turma.TurmaDisciplina;
import mol.model.user.Aluno;

public interface IRespostaDuvidaDAO extends IDAOGenerico<RespostaDuvida> {
	
	List<RespostaDuvida> consultarPorAtividade(Atividade atv);

	List<RespostaDuvida> consultarPorTurmaDisciplina(TurmaDisciplina turmaDisc);

	List<RespostaDuvida> consultarPorAluno(Aluno a);

	List<RespostaDuvida> consultarPorDisciplina(Disciplina disciplina);
	
}
