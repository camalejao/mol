package mol.dao;

import java.util.List;

import mol.model.curso.atividade.Atividade;
import mol.model.curso.atividade.duvida.Duvida;
import mol.model.curso.disciplina.Disciplina;
import mol.model.curso.turma.TurmaDisciplina;
import mol.model.user.Aluno;

public interface IDuvidaDAO extends IDAOGenerico<Duvida> {
	
	List<Duvida> consultarDuvidasPublicas();
	
	List<Duvida> consultarDuvidasPorAluno(Aluno a);

	List<Duvida> consultarDuvidasPorAtividade(Atividade atv);

	List<Duvida> consultarDuvidasPublicasPorAtividade(Atividade atv);
	
	List<Duvida> consultarDuvidasPorTurmaDisciplina(TurmaDisciplina turmaDisciplina);

	List<Duvida> consultarDuvidasPorDisciplina(Disciplina disciplina);
}
