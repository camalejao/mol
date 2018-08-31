package mol.interceptor;

import java.util.ArrayList;
import java.util.List;

import mol.dao.DAOFactory;
import mol.dao.IPermissoesDAO;
import mol.model.user.TipoUsuario;

public class AdicionaPermissoes {
	
	public static void main(String[] args) {
		IPermissoesDAO pDAO = DAOFactory.getPermissoesDAO();
		List<Permissoes> lp = new ArrayList<>();
		lp.add(new Permissoes("homeAdm", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("listarUsuarios", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("listarDisciplinas", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("cadastrarUsuario", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("cadastrarDisciplina", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("cadastrarAluno", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("selecionarTipoUsuario", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("insereDisciplina", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("insereAluno", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("cadastrarAdm", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("insereAdm", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("cadastrarProfessor", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("insereProfessor", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("cadastrarMonitor", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("insereMonitor", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("homeProfessor", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("gerenciarAtividades", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("cadastraAtividade", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("downloadDocumento-{id}", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("editarAtividade-{id}", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("editaAtividade-{id}", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("homeAluno", TipoUsuario.ALUNO));
		lp.add(new Permissoes("homeMonitor", TipoUsuario.MONITOR));
		lp.add(new Permissoes("responderAtividade-{id}", TipoUsuario.ALUNO));
		lp.add(new Permissoes("enviarResposta", TipoUsuario.ALUNO));
		lp.add(new Permissoes("downloadResposta-{id}", TipoUsuario.ALUNO));
		lp.add(new Permissoes("downloadAtividade-{id}", TipoUsuario.ALUNO));
		lp.add(new Permissoes("downloadResposta-{id}", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("listarTurmas-{id}", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("listarAtividades-{id}", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("respostasAtividade-{id}", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("adicionarAtividade-{id}", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("visualizarResposta-{id}", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("avaliarResposta-{id}", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("disciplinas", TipoUsuario.ALUNO));
		lp.add(new Permissoes("verCorrecoes-{id}", TipoUsuario.ALUNO));
		lp.add(new Permissoes("sumario-{id}", TipoUsuario.ALUNO));
		lp.add(new Permissoes("editarSumario-{id}", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("downloadMd", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("downloadMd", TipoUsuario.ALUNO));
		lp.add(new Permissoes("adicionaTopico", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("editaTopico", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("excluirTopico", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("adicionaMaterialDidatico", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("editaMaterialDidatico", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("excluirMaterialDidatico", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("adicionarItemDiscursivo", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("removerItemDiscursivo", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("adicionarItemMultiplaEscolha", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("removerItemMultiplaEscolha", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("excluirItem", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("editarItem", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("editarItemME", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("editarAlternativa", TipoUsuario.PROFESSOR));
		lp.add(new Permissoes("turmasDisciplina", TipoUsuario.MONITOR));
		lp.add(new Permissoes("requisitaItem", TipoUsuario.ALUNO));
		lp.add(new Permissoes("requisitaItemResposta", TipoUsuario.ALUNO));
		lp.add(new Permissoes("salvarRespostaItem", TipoUsuario.ALUNO));

		
		for(Permissoes p : lp) {
			pDAO.inserir(p);
		}
	}
}
