package mol.converter;

import org.springframework.core.convert.converter.Converter;

import mol.dao.DAOFactory;
import mol.dao.INivelAprendizagemDAO;
import mol.model.curso.atividade.NivelAprendizagem;

public class NivelAprendizagemConverter implements Converter<String, NivelAprendizagem> {

	@Override
	public NivelAprendizagem convert(String arg) {
		Integer id = Integer.valueOf(arg);
		INivelAprendizagemDAO naDAO = DAOFactory.getNivelAprendizagemDAO();
		NivelAprendizagem na = naDAO.consultarPorId(id);
		return na;
	}

}
