package mol.converter;

import org.springframework.core.convert.converter.Converter;

import mol.dao.DAOFactory;
import mol.dao.IProfessorDAO;
import mol.model.user.Professor;;

public class ProfessorConverter implements Converter<String, Professor> {
	
	@Override
	public Professor convert(String source) {
		Integer id = Integer.valueOf(source);
		IProfessorDAO tdDAO = DAOFactory.getProfessorDAO();
		Professor p = tdDAO.consultarPorId(id);
		return p;
	}
}
