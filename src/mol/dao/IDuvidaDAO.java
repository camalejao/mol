package mol.dao;

import java.util.List;

import mol.model.curso.atividade.duvida.Duvida;
import mol.model.user.Aluno;

public interface IDuvidaDAO extends IDAOGenerico<Duvida> {
	
	List<Duvida> consultarDuvidasPublicas();
	
	List<Duvida> consultarDuvidasPorAluno(Aluno a);
}
