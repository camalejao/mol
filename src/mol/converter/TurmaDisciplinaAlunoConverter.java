package mol.converter;

import org.springframework.core.convert.converter.Converter;

import mol.dao.DAOFactory;
import mol.dao.ITurmaDisciplinaAlunoDAO;
import mol.model.curso.turma.TurmaDisciplinaAluno;

public class TurmaDisciplinaAlunoConverter implements Converter<String, TurmaDisciplinaAluno> {
	@Override
	public TurmaDisciplinaAluno convert(String source) {
		Integer id = Integer.valueOf(source);
		ITurmaDisciplinaAlunoDAO tdaDAO = DAOFactory.getTurmaDisciplinaAlunoDAO();
		TurmaDisciplinaAluno tda = tdaDAO.consultarPorId(id);
		return tda;
	}
}
