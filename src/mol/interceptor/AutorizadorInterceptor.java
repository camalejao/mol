package mol.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import mol.dao.DAOFactory;
import mol.dao.IPermissoesDAO;
import mol.model.user.Usuario;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {

		String uri = request.getRequestURI();
		if (uri.endsWith("login") || uri.endsWith("efetuaLogin") || uri.endsWith("logout") || uri.contains("resources")
				|| uri.contains("webjars")) {
			return true;
		}

		if (uri.endsWith("/") || uri.endsWith("home") || uri.endsWith("index") || uri.endsWith("acessoNaoAutorizado")) {
			return true;
		}

		if (uri.endsWith("cadastroAluno") || uri.endsWith("cadastroProfessor") || uri.endsWith("cadastraAluno")
				|| uri.endsWith("cadastraProfessor")) {
			return true;
		}

		if (uri.endsWith("verificaEmail") || uri.endsWith("verificaMatAluno") || uri.endsWith("verificaMatProf")
				|| uri.endsWith("verificaSigla")) {
			return true;
		}

		if (request.getSession().getAttribute("usuarioLogado") != null) {

			Usuario u = (Usuario) request.getSession().getAttribute("usuarioLogado");
			IPermissoesDAO pDAO = DAOFactory.getPermissoesDAO();

			for (Permissoes p : pDAO.consultarPorTipoUsuario(u.getTipo())) {
				if (uri.endsWith(p.getServico())) {
					return true;

				}
				if ((uri.contains("downloadDocumento") && p.getServico().contains("downloadDocumento"))
						|| (uri.contains("editaAtividade") && p.getServico().contains("editaAtividade"))
						|| (uri.contains("editarAtividade") && p.getServico().contains("editarAtividade"))
						|| (uri.contains("responderAtividade") && p.getServico().contains("responderAtividade"))
						|| (uri.contains("listarAtividades") && p.getServico().contains("listarAtividades"))
						|| (uri.contains("listarTurmas") && p.getServico().contains("listarTurmas"))
						|| (uri.contains("respostasAtividade") && p.getServico().contains("respostasAtividade"))
						|| (uri.contains("adicionarAtividade") && p.getServico().contains("adicionarAtividade"))
						|| (uri.contains("visualizarResposta") && p.getServico().contains("visualizarResposta"))
						|| (uri.contains("avaliarResposta") && p.getServico().contains("avaliarResposta"))
						|| (uri.contains("downloadResposta") && p.getServico().contains("downloadResposta"))
						|| (uri.contains("downloadAtividade") && p.getServico().contains("downloadAtividade"))){
					return true;
				}
			}
		}

		response.sendRedirect("acessoNaoAutorizado");
		return false;
	}
}