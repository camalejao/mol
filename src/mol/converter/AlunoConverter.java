package mol.converter;

import org.springframework.core.convert.converter.Converter;

import mol.dao.DAOFactory;
import mol.dao.IAlunoDAO;
import mol.model.user.Aluno;

public class AlunoConverter implements Converter<String, Aluno> {
	
	@Override
	public Aluno convert(String mat) {
		IAlunoDAO aDAO = DAOFactory.getAlunoDAO();
		Aluno a = aDAO.consultarPorMatricula(mat);
		return a;
	}
}
