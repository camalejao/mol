package mol.converter;

import org.springframework.core.convert.converter.Converter;

import mol.dao.DAOFactory;
import mol.dao.IDisciplinaDAO;
import mol.model.curso.disciplina.Disciplina;

public class DisciplinaConverter implements Converter<String, Disciplina> {
	
	@Override
	public Disciplina convert(String sigla) {
		IDisciplinaDAO dDAO = DAOFactory.getDisciplinaDAO();
		Disciplina d = dDAO.consultarPorSigla(sigla);
		return d;
	}
}
