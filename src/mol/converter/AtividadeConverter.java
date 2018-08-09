package mol.converter;

import org.springframework.core.convert.converter.Converter;

import mol.dao.DAOFactory;
import mol.dao.IAtividadeDAO;
import mol.model.curso.turma.Atividade;

public class AtividadeConverter  implements Converter<String, Atividade>{
	
	@Override
	public Atividade convert(String source) {
		Integer id = Integer.valueOf(source);
		IAtividadeDAO aDAO = DAOFactory.getAtividadeDAO();
		Atividade atv = aDAO.consultarPorId(id);
		return atv;
	}
}
