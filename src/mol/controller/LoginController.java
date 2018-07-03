package mol.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mol.dao.DAOFactory;
import mol.dao.IUsuarioDAO;
import mol.model.StatusEntidade;
import mol.model.user.Usuario;

@Controller
public class LoginController {

	@RequestMapping("/")
	public String inicio(HttpSession session) {
		if (session.getAttribute("usuarioLogado") != null) {
			return "redirect:home";
		} else
			return "index";
	}

	@RequestMapping("login")
	public String telaLogin() {
		return "index";
	}
	
	@RequestMapping("acessoNaoAutorizado")
	public String naoAutorizado() {
		return "naoAutorizado";
	}

	@RequestMapping("efetuaLogin")
	public String efetuaLogin(Usuario usuario, HttpSession session) {

		IUsuarioDAO uDAO = DAOFactory.getUsuarioDAO();
		Usuario controle = uDAO.consultarPorEmail(usuario.getEmail());

		if ((controle != null) && (controle.getStatus()==StatusEntidade.ATIVO) && (controle.getSenha().equals(usuario.senhaSHA()))) {

			session.setAttribute("usuarioLogado", controle);

			switch (controle.getTipo()) {
			case ADMINISTRADOR:
				return "redirect:homeAdm";
			case ALUNO:
				return "redirect:homeAluno";
			case PROFESSOR:
				return "redirect:homeProfessor";
			case MONITOR:
				return "redirect:homeMonitor";
			}

		}
		return "redirect:login";
	}

	@RequestMapping("home")
	public String redirecionaHome(HttpSession session) {
		
		if (session.getAttribute("usuarioLogado")!=null) {
			Usuario controle = (Usuario) session.getAttribute("usuarioLogado");
			switch (controle.getTipo()) {
			case ADMINISTRADOR:
				return "redirect:homeAdm";
			case ALUNO:
				return "redirect:homeAluno";
			case PROFESSOR:
				return "redirect:homeProfessor";
			case MONITOR:
				return "redirect:homeMonitor";
			default:
				return "redirect:login";
			}
		}
		else
			return "redirect:login";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {

		session.invalidate();

		return "redirect:login";
	}
}
