package mol.converter;

import org.springframework.core.convert.converter.Converter;

import mol.dao.DAOFactory;
import mol.dao.IItemRespostaDAO;
import mol.model.curso.atividade.ItemResposta;

public class ItemRespostaConverter implements Converter <String, ItemResposta> {

	@Override
	public ItemResposta convert(String source) {
		Integer id = Integer.valueOf(source);
		IItemRespostaDAO irDAO = DAOFactory.getItemRespostaDAO();
		ItemResposta item = irDAO.consultarPorId(id);
		return item;
	}

}
