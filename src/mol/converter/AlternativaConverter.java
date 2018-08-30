package mol.converter;

import org.springframework.core.convert.converter.Converter;

import mol.dao.DAOFactory;
import mol.dao.IAlternativaDAO;
import mol.model.curso.atividade.Alternativa;

public class AlternativaConverter implements Converter<String, Alternativa>{

	@Override
	public Alternativa convert(String source) {
		Integer id = Integer.valueOf(source);
		IAlternativaDAO aDAO = DAOFactory.getAlternativaDAO();
		Alternativa alternativa = aDAO.consultarPorId(id);
		
		return alternativa;
	}
}
