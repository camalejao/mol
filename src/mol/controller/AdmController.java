package mol.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mol.dao.DAOFactory;
import mol.dao.IUsuarioDAO;
import mol.model.user.TipoUsuario;
import mol.model.user.Usuario;
import mol.dao.IMonitorDAO;
import mol.model.user.Monitor;
import mol.dao.IProfessorDAO;
import mol.model.user.Professor;
import mol.dao.IAlunoDAO;
import mol.model.user.Aluno;
import mol.model.StatusEntidade;

@Controller
public class AdmController {

	@RequestMapping("homeAdm")
	public String inicio() {
		return "adm/index";
	}
	
	@RequestMapping("excluirUsuario")
	public String excluiUsuario(@RequestParam("usuario") Usuario u) {
		IUsuarioDAO uDAO = DAOFactory.getUsuarioDAO();
		uDAO.remover(u);
		return "redirect:listarUsuarios";
	}
	
	@RequestMapping("editarUsuario")
	public String editarUsuario(@RequestParam("usuario") Usuario u, @RequestParam("nome") String nome,
			@RequestParam("email") String email, @RequestParam("matricula") String matricula, HttpSession session) {
		
		IUsuarioDAO uDAO = DAOFactory.getUsuarioDAO();
		if(nome.length()>=10 && nome.length()<=40)
			u.setNome(nome);
		if(email.length()<=50 && uDAO.verificarPorEmail(email))
			u.setEmail(email);
		if(u.getTipo() == TipoUsuario.ADMINISTRADOR) {
			uDAO.alterar(u);
		}
		else if(u.getTipo() == TipoUsuario.ALUNO || u.getTipo() == TipoUsuario.MONITOR) {
			IAlunoDAO aDAO = DAOFactory.getAlunoDAO();
			Aluno a = (Aluno) u;
			if(matricula.length() == 8 && aDAO.verificarPorMatricula(matricula)) {
				a.setMatricula(matricula);
			}
			aDAO.alterar(a);
		}
		else if(u.getTipo() == TipoUsuario.PROFESSOR) {
			IProfessorDAO pDAO = DAOFactory.getProfessorDAO();
			Professor p = (Professor) u;
			if(matricula.length() == 8 && pDAO.verificarPorMatricula(matricula)) {
				p.setMatricula(matricula);
			}
			pDAO.alterar(p);
		}
		
		return "redirect:listarUsuarios";
	}
	
	@RequestMapping("removerMonitor")
	public String removerMonitor(@RequestParam("usuario") Usuario u) {
		IUsuarioDAO uDAO = DAOFactory.getUsuarioDAO();
		IMonitorDAO mDAO = DAOFactory.getMonitorDAO();
		Monitor m = mDAO.consultarPorAluno((Aluno)u);
		u.setTipo(TipoUsuario.ALUNO);
		uDAO.alterar(u);
		m.setStatus(StatusEntidade.INATIVO);
		mDAO.alterar(m);
		
		return "redirect:listarUsuarios";
	}
	
}
