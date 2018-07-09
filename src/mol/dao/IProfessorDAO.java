package mol.dao;

import java.util.List;

import mol.model.user.Professor;

public interface IProfessorDAO extends IDAOGenerico<Professor> {
	
	public Professor consultarPorMatricula(String mat);
	
	public List<Professor> consultarPorNome(String nome);

	public Boolean verificarPorMatricula(String mat);
}
