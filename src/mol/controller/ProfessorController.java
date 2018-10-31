package mol.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;

import mol.dao.DAOFactory;
import mol.dao.IAlternativaDAO;
import mol.dao.IAtividadeDAO;
import mol.dao.IDuvidaDAO;
import mol.dao.IItemAtividadeDAO;
import mol.dao.IMaterialDidaticoDAO;
import mol.dao.INivelAprendizagemDAO;
import mol.dao.IRespostaDAO;
import mol.dao.IRespostaDuvidaDAO;
import mol.dao.ITopicoDAO;
import mol.dao.ITurmaDisciplinaDAO;
import mol.model.StatusEntidade;
import mol.model.curso.atividade.Alternativa;
import mol.model.curso.atividade.Atividade;
import mol.model.curso.atividade.ItemAtividade;
import mol.model.curso.atividade.Resposta;
import mol.model.curso.atividade.StatusAtividade;
import mol.model.curso.atividade.StatusResposta;
import mol.model.curso.atividade.TipoItem;
import mol.model.curso.atividade.TipoSubmissao;
import mol.model.curso.atividade.Unidades;
import mol.model.curso.atividade.duvida.RespostaDuvida;
import mol.model.curso.disciplina.Topico;
import mol.model.curso.turma.TurmaDisciplina;
import mol.model.materialDidatico.MaterialDidatico;
import mol.model.materialDidatico.TipoMaterialDidatico;
import mol.model.user.Professor;
import mol.model.user.Usuario;

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
	
	@RequestMapping("responderDuvida")
	public ModelAndView responderDuvida(@Valid @ModelAttribute("resposta") RespostaDuvida resposta,
			BindingResult bindingResult, Model model, HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("professor/listaDuvidas");
			IDuvidaDAO dDAO = DAOFactory.getDuvidaDAO();
			mav.addObject("duvidas", dDAO.consultarTodos());
			mav.addObject("resposta", new RespostaDuvida());
			return mav;
		} else {
			IRespostaDuvidaDAO rdDAO = DAOFactory.getRespostaDuvidaDAO();
			Usuario autor = (Usuario) session.getAttribute("usuarioLogado");
			resposta.setAutor(autor);
			resposta.setUsuarioLogado(autor);
			resposta.setStatus(StatusEntidade.ATIVO);
			rdDAO.inserir(resposta);
		}
		return new ModelAndView("redirect:verDuvidas");
	}
	
	@RequestMapping("duvidasAtividade-{id}")
	public ModelAndView duvidasAtividade(@PathVariable Integer id, HttpSession session) {
		ModelAndView mav = new ModelAndView("professor/duvidasAtividade");
		IAtividadeDAO aDAO = DAOFactory.getAtividadeDAO();
		IDuvidaDAO dDAO = DAOFactory.getDuvidaDAO();
		Atividade atv = aDAO.consultarPorId(id);
		mav.addObject("atividade", atv);
		mav.addObject("duvidas", dDAO.consultarDuvidasPorAtividade(atv));
		mav.addObject("resposta", new RespostaDuvida());
		return mav;
	}
	
	@RequestMapping("duvidasTurmaDisciplina-{id}")
	public ModelAndView duvidasTurmaDisciplina(@PathVariable Integer id, HttpSession session) {
		ModelAndView mav = new ModelAndView("professor/listaDuvidas");
		ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
		IDuvidaDAO dDAO = DAOFactory.getDuvidaDAO();
		TurmaDisciplina turmaDisc = tdDAO.consultarPorId(id);
		mav.addObject("turmaDisc", turmaDisc);
		mav.addObject("duvidas", dDAO.consultarDuvidasPorTurmaDisciplina(turmaDisc));
		mav.addObject("resposta", new RespostaDuvida());
		return mav;
	}
}
