package mol.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mol.dao.DAOFactory;
import mol.dao.IUsuarioDAO;
import mol.model.user.Usuario;

@Controller
public class LoginController {
	
	@RequestMapping("/")
	public String inicio() {
        return "index";
    }
	
	@RequestMapping("login")
	public String telaLogin() {
        return "index";
    }
	
	@RequestMapping("efetuaLogin")
    public String efetuaLogin(Usuario usuario, HttpSession session) {
        System.out.println(usuario.getEmail());
		IUsuarioDAO uDAO = DAOFactory.getUsuarioDAO();
		Usuario controle = uDAO.consultarPorEmail(usuario.getEmail());
		
		if((controle != null)&&(controle.getSenha().equals(usuario.senhaSHA()))) {
			
            session.setAttribute("usuarioLogado", controle);
            
            switch(controle.getTipo()) {
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
		
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
        return "redirect:login";
	}
}
