package mol.converter;

import org.springframework.core.convert.converter.Converter;

import mol.dao.DAOFactory;
import mol.dao.ITurmaDAO;
import mol.model.curso.turma.Turma;

public class TurmaConverter implements Converter<String, Turma> {
	
	@Override
	public Turma convert(String source) {
		Integer id = Integer.valueOf(source);
		ITurmaDAO tdDAO = DAOFactory.getTurmaDAO();
		Turma t = tdDAO.consultarPorId(id);
		return t;
	}
}
