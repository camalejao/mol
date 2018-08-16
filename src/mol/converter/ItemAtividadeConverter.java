package mol.converter;

import org.springframework.core.convert.converter.Converter;

import mol.dao.DAOFactory;
import mol.dao.IItemAtividadeDAO;
import mol.model.curso.atividade.ItemAtividade;;

public class ItemAtividadeConverter implements Converter <String, ItemAtividade> {

	@Override
	public ItemAtividade convert(String source) {
		Integer id = Integer.valueOf(source);
		IItemAtividadeDAO iaDAO = DAOFactory.getItemAtividadeDAO();
		ItemAtividade item = iaDAO.consultarPorId(id);
		return item;
	}

}
