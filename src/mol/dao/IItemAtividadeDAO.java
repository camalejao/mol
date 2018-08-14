package mol.dao;

import java.util.List;

import mol.model.curso.atividade.ItemAtividade;

public interface IItemAtividadeDAO extends IDAOGenerico<ItemAtividade> {

	List<ItemAtividade> consultarPorIdAtividade(Integer id);

}
