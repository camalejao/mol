package mol.dao;

import java.util.List;

import mol.model.user.Aluno;

public interface IAlunoDAO extends IDAOGenerico<Aluno> {
	
	 public Aluno consultarPorMatricula(String mat);
	 
	 public List<Aluno> consultarPorNome(String nome);
	 
	 public List<Aluno> consultarPossiveisMonitores();
}
