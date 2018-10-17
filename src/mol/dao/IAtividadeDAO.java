package mol.dao;

import java.util.List;

import mol.model.curso.atividade.Atividade;
import mol.model.curso.turma.TurmaDisciplina;
import mol.model.curso.turma.TurmaDisciplinaAluno;
import mol.model.user.Professor;

public interface IAtividadeDAO extends IDAOGenerico<Atividade> {
	List<Atividade> consultarPorProfessor(Professor p);

	List<Atividade> consultarPorTurmaDisciplina(TurmaDisciplina td);

	List<Atividade> consultarPorIdTurmaDisciplina(Integer id);

	List<Atividade> consultarRespondidas(TurmaDisciplinaAluno tda);

	List<Atividade> consultarNaoRespondidas(TurmaDisciplinaAluno tda);

	boolean verfificarAtividadesNoNivelAnterior(Integer nivelAprendizagem, TurmaDisciplina turmaDisciplina);
}
