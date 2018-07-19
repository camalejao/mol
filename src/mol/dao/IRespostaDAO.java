package mol.dao;

import java.util.List;

import mol.model.curso.turma.Atividade;
import mol.model.curso.turma.Resposta;

public interface IRespostaDAO extends IDAOGenerico<Resposta> {
	
	public List<Resposta> consultarPorAtividade(Atividade atv);
	
	public List<Resposta> consultarPorIdAtividade(Integer id);
}
