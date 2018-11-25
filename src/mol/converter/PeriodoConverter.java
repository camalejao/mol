package mol.converter;

import org.springframework.core.convert.converter.Converter;

import mol.dao.DAOFactory;
import mol.dao.IPeriodoDAO;
import mol.model.curso.Periodo;

public class PeriodoConverter implements Converter<String, Periodo> {
	
	@Override
	public Periodo convert(String source) {
		Integer id = Integer.valueOf(source);
		IPeriodoDAO pDAO = DAOFactory.getPeriodoDAO();
		Periodo p = pDAO.consultarPorId(id);
		return p;
	}
}
