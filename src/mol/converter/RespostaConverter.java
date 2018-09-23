package mol.converter;

import org.springframework.core.convert.converter.Converter;

import mol.dao.DAOFactory;
import mol.dao.IRespostaDAO;
import mol.model.curso.atividade.Resposta;

public class RespostaConverter implements Converter<String, Resposta> {

	@Override
	public Resposta convert(String source) {
		Integer id = Integer.valueOf(source);
		IRespostaDAO rDAO = DAOFactory.getRespostaDAO();
		Resposta resposta = rDAO.consultarPorId(id);
		return resposta;
	}

}
