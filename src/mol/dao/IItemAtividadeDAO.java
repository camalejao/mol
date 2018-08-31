package mol.dao;

import java.util.List;

import mol.model.curso.atividade.Atividade;
import mol.model.curso.atividade.ItemAtividade;

public interface IItemAtividadeDAO extends IDAOGenerico<ItemAtividade> {

	List<ItemAtividade> consultarPorAtividade(Atividade atividade);

}
