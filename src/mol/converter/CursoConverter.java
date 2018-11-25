package mol.converter;

import org.springframework.core.convert.converter.Converter;

import mol.dao.DAOFactory;
import mol.dao.ICursoDAO;
import mol.model.curso.Curso;

public class CursoConverter implements Converter<String, Curso> {
	
	@Override
	public Curso convert(String source) {
		Integer id = Integer.valueOf(source);
		ICursoDAO cDAO = DAOFactory.getCursoDAO();
		Curso c = cDAO.consultarPorId(id);
		return c;
	}
}
