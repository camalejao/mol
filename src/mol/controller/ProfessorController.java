package mol.controller;

import java.util.Arrays;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mol.dao.DAOFactory;
import mol.dao.IAtividadeDAO;
import mol.model.curso.turma.NivelAprendizagem;
import mol.model.curso.turma.Unidades;
import mol.model.user.Professor;
import mol.model.user.TipoUsuario;

@Controller
public class ProfessorController {
	
	@RequestMapping("homeProfessor")
	public String inicio() {
        return "professor/index";
    }
	
	@RequestMapping("gerenciarAtividades")
	public ModelAndView atividades(HttpSession session) {
		ModelAndView mav = new ModelAndView("professor/atividades");
		Professor p = (Professor)session.getAttribute("usuarioLogado");
		IAtividadeDAO aDAO = DAOFactory.getAtividadeDAO();
		mav.addObject("atividades", aDAO.consultarPorProfessor(p));
		mav.addObject("unidades", Arrays.asList(Unidades.values()));
		mav.addObject("niveis", Arrays.asList(NivelAprendizagem.values()));
		
        return mav;
    }
	
	@RequestMapping("cadastraAtividade")
	public ModelAndView salvaAtividade() {
		
	}
	
}
