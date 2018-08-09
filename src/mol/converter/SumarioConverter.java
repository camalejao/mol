package mol.converter;

import org.springframework.core.convert.converter.Converter;

import mol.dao.DAOFactory;
import mol.dao.ISumarioDAO;
import mol.model.curso.disciplina.Sumario;

public class SumarioConverter implements Converter<String, Sumario> {

	@Override
	public Sumario convert(String source) {
		Integer id = Integer.valueOf(source);
		ISumarioDAO sDAO = DAOFactory.getSumarioDAO();
		Sumario s = sDAO.consultarPorId(id);
		return s;
	}

}
