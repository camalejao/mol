package mol.dao;

import java.util.List;

import mol.model.curso.atividade.NivelAprendizagem;
import mol.model.curso.turma.TurmaDisciplina;

public interface INivelAprendizagemDAO extends IDAOGenerico<NivelAprendizagem> {
	
	List<NivelAprendizagem> consultarPorTurmaDisciplina(TurmaDisciplina td);
}
