package mol.dao;

import java.util.List;

import mol.model.curso.atividade.Atividade;
import mol.model.curso.atividade.ItemResposta;
import mol.model.user.Aluno;

public interface IItemRespostaDAO extends IDAOGenerico<ItemResposta>{

	List<ItemResposta> consultarPorAlunoAtividade(Aluno aluno, Atividade atividade);
	
}
