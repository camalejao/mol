package mol.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mol.dao.DAOFactory;
import mol.dao.IAtividadeDAO;
import mol.dao.IDuvidaDAO;
import mol.dao.IRespostaDuvidaDAO;
import mol.dao.ITurmaDisciplinaAlunoDAO;
import mol.dao.ITurmaDisciplinaDAO;
import mol.model.curso.atividade.Atividade;
import mol.model.curso.atividade.duvida.RespostaDuvida;
import mol.model.curso.turma.TurmaDisciplina;
import mol.model.user.Professor;

@Controller
public class ProfessorController {

	@RequestMapping("homeProfessor")
	public String inicio() {
		return "professor/index";
	}

	@RequestMapping("listarTurmas")
	public ModelAndView listaTurmas(HttpSession session) {
		ModelAndView mav = new ModelAndView("professor/listaTurmas");
		Professor p = (Professor) session.getAttribute("usuarioLogado");
		ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
		mav.addObject("turmasDisciplinas", tdDAO.consultarPorProfessor(p));
		return mav;
	}
	
	@RequestMapping("verDuvidas")
	public ModelAndView duvidas(HttpSession session) {
		ModelAndView mav = new ModelAndView("professor/selecionarTurma");
		ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
		Professor p = (Professor) session.getAttribute("usuarioLogado");
		mav.addObject("turmasDisc", tdDAO.consultarPorProfessor(p));
		return mav;
	}
		
	@RequestMapping("duvidasAtividade-{id}")
	public ModelAndView duvidasAtividade(@PathVariable Integer id, HttpSession session) {
		
		ModelAndView mav;
		IAtividadeDAO aDAO = DAOFactory.getAtividadeDAO();
		IDuvidaDAO dDAO = DAOFactory.getDuvidaDAO();
		IRespostaDuvidaDAO rdDAO = DAOFactory.getRespostaDuvidaDAO();
		
		Professor p = (Professor) session.getAttribute("usuarioLogado");
		Atividade atv = aDAO.consultarPorId(id);
		
		if(atv!=null && atv.getTurmaDisciplina().getProfessor().getId() == p.getId()) {
			mav = new ModelAndView("professor/duvidasAtividade");
			mav.addObject("atividade", atv);
			mav.addObject("duvidas", dDAO.consultarDuvidasPorAtividade(atv));
			mav.addObject("respostas", rdDAO.consultarPorAtividade(atv));
			return mav;
		}
		
		mav = new ModelAndView("redirect:listarTurmas");
		return mav;
	}
	
	@RequestMapping("duvidasTurmaDisciplina-{id}")
	public ModelAndView duvidasTurmaDisciplina(@PathVariable Integer id, HttpSession session) {
		
		ModelAndView mav;
		ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
		IDuvidaDAO dDAO = DAOFactory.getDuvidaDAO();
		IRespostaDuvidaDAO rdDAO = DAOFactory.getRespostaDuvidaDAO(); 
		
		Professor p = (Professor) session.getAttribute("usuarioLogado");
		TurmaDisciplina turmaDisc = tdDAO.consultarPorId(id);
		
		if(turmaDisc!=null && turmaDisc.getProfessor().getId() == p.getId()) {
			mav = new ModelAndView("professor/listaDuvidas");
			mav.addObject("turmaDisc", turmaDisc);
			mav.addObject("duvidas", dDAO.consultarDuvidasPorTurmaDisciplina(turmaDisc));
			mav.addObject("respostas", rdDAO.consultarPorTurmaDisciplina(turmaDisc));
			
			return mav;
		}
		
		mav = new ModelAndView("redirect:listarTurmas");
		return mav;
	}
	
	@RequestMapping("verAlunos-{id}")
	public ModelAndView listaAlunos(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("professor/listaAlunosTurma");
		ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
		ITurmaDisciplinaAlunoDAO tdaDAO = DAOFactory.getTurmaDisciplinaAlunoDAO();
		TurmaDisciplina td = tdDAO.consultarPorId(id);
		
		if(td!=null) {
			mav.addObject("turmaDisciplina", td);
			mav.addObject("alunos", tdaDAO.consultarPorTurmaDisciplina(td));
			return mav;
		} else
			return new ModelAndView("redirect:listarTurmas");
	}
}
