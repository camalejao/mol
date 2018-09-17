package mol.dao;

import java.util.List;

import mol.model.curso.atividade.Atividade;
import mol.model.curso.atividade.ItemResposta;
import mol.model.user.Aluno;

public interface IItemRespostaDAO extends IDAOGenerico<ItemResposta>{

	List<ItemResposta> consultarPorAlunoAtividade(Aluno aluno, Atividade atividade);

	ItemResposta consultarPorIdItemIdAluno(Integer idItem, Integer idAluno);

	List<ItemResposta> consultarNaoEnviadosPorAlunoAtividade(Aluno aluno, Atividade atividade);

	ItemResposta consultarNaoEnviadoPorIdItemIdAluno(Integer idItem, Integer idAluno);

	List<ItemResposta> consultarEnviadosPorAlunoAtividade(Aluno aluno, Atividade atividade);
	
}
