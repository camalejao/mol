package mol.dao;

import java.util.List;

import mol.model.curso.atividade.Atividade;
import mol.model.curso.atividade.Resposta;
import mol.model.curso.turma.TurmaDisciplina;
import mol.model.user.Aluno;

public interface IRespostaDAO extends IDAOGenerico<Resposta> {
	
	public List<Resposta> consultarPorAtividade(Atividade atv);
	
	public List<Resposta> consultarPorIdAtividade(Integer id);

	List<Resposta> consultarCorrigidas(TurmaDisciplina td, Aluno a);

	public Resposta consultarPorAtividadeAluno(Atividade atv, Aluno a);
}
