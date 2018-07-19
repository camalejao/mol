package mol.converter;

import org.springframework.core.convert.converter.Converter;

import mol.dao.DAOFactory;
import mol.dao.ITurmaDisciplinaDAO;
import mol.model.curso.turma.TurmaDisciplina;

public class TurmaDisciplinaConverter implements Converter<String, TurmaDisciplina> {
	
	@Override
	public TurmaDisciplina convert(String source) {
		Integer id = Integer.valueOf(source);
		ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
		TurmaDisciplina td = tdDAO.consultarPorId(id);
		return td;
	}
}
