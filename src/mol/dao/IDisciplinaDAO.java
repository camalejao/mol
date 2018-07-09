package mol.dao;

import mol.model.curso.disciplina.Disciplina;

public interface IDisciplinaDAO extends IDAOGenerico<Disciplina> {
	
	public Disciplina consultarPorSigla(String sigla);

	public Boolean verificarPorSigla(String sigla);
}
