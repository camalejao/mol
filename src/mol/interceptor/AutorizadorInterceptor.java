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
		if (uri.endsWith("login") || uri.endsWith("efetuaLogin") || uri.endsWith("logout") || uri.contains("resources")) {
			return true;
		}
		
		if (uri.endsWith("cadastroAluno") || uri.endsWith("cadastroProfessor") || uri.endsWith("cadastraAluno") || uri.endsWith("cadastraProfessor")) {
			return true;
		}
		
		if (request.getSession().getAttribute("usuarioLogado") != null) {
			Usuario u = (Usuario)request.getSession().getAttribute("usuarioLogado");
			IPermissoesDAO pDAO = DAOFactory.getPermissoesDAO();
			for(Permissoes p : pDAO.consultarPorTipoUsuario(u.getTipo())) {
				if(uri.endsWith(p.getServico())) {
					return true;
				}
			}
		}
		
		response.sendRedirect("acessoNaoAutorizado");
		return false;
	}
} 