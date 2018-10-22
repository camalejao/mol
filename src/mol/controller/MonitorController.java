package mol.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mol.dao.DAOFactory;
import mol.dao.IDuvidaDAO;
import mol.dao.IMonitorDAO;
import mol.dao.ITurmaDisciplinaDAO;
import mol.model.curso.turma.TurmaDisciplina;
import mol.model.user.Aluno;
import mol.model.user.Monitor;


@Controller
public class MonitorController {
	
	@RequestMapping("homeMonitor")
	public String inicio() {
        return "monitor/index";
    }
	
	@RequestMapping("turmasDisciplina")
	public ModelAndView turmas(HttpSession session) {
		Aluno a = (Aluno) session.getAttribute("usuarioLogado");
		IMonitorDAO mDAO = DAOFactory.getMonitorDAO();
		Monitor m = mDAO.consultarPorAluno(a);
		ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
		List<TurmaDisciplina> ltd = tdDAO.consultarPorDisciplina(m.getDisciplina());
        return new ModelAndView("monitor/listaTurmas", "turmasDisc", ltd);
    }
	
	@RequestMapping("acompanharDuvidas")
	public ModelAndView duvidas() {
		ModelAndView mav = new ModelAndView("monitor/listaDuvidas");
		IDuvidaDAO dDAO = DAOFactory.getDuvidaDAO();
		mav.addObject("duvidas", dDAO.consultarTodos());
		return mav;
	}
	
}
