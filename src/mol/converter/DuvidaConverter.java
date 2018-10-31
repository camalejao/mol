package mol.converter;

import org.springframework.core.convert.converter.Converter;

import mol.dao.DAOFactory;
import mol.dao.IDuvidaDAO;
import mol.model.curso.atividade.duvida.Duvida;

public class DuvidaConverter implements Converter<String, Duvida>{

	@Override
	public Duvida convert(String source) {
		Integer id = Integer.valueOf(source);
		IDuvidaDAO dDAO = DAOFactory.getDuvidaDAO();
		Duvida duvida = dDAO.consultarPorId(id);
		return duvida;
	}
	
}
