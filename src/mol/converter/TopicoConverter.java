package mol.converter;

import org.springframework.core.convert.converter.Converter;

import mol.dao.DAOFactory;
import mol.dao.ITopicoDAO;
import mol.model.curso.disciplina.Topico;

public class TopicoConverter  implements Converter<String, Topico> {

	@Override
	public Topico convert(String source) {
		Integer id = Integer.valueOf(source);
		ITopicoDAO tDAO = DAOFactory.getTopicoDAO();
		Topico t = tDAO.consultarPorId(id);
		return t;
	}

}
