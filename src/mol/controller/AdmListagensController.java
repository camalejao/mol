package mol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mol.dao.DAOFactory;
import mol.dao.ICursoDAO;
import mol.dao.IDisciplinaDAO;
import mol.dao.IPeriodoDAO;
import mol.dao.ITurmaDAO;
import mol.dao.ITurmaDisciplinaDAO;
import mol.dao.IUsuarioDAO;

@Controller
public class AdmListagensController {
	
	@RequestMapping("listarUsuarios")
	public String listaUsuarios(Model model) {

		IUsuarioDAO uDAO = DAOFactory.getUsuarioDAO();

		model.addAttribute("usuarios", uDAO.consultarTodos());

		return "adm/listaUsuarios";
	}

	@RequestMapping("listarDisciplinas")
	public String listaDisciplinas(Model model) {

		IDisciplinaDAO dDAO = DAOFactory.getDisciplinaDAO();

		model.addAttribute("disciplinas", dDAO.consultarTodos());

		return "adm/listaDisciplinas";
	}
	
	@RequestMapping("listarCursos")
	public String listaCursos(Model model) {

		ICursoDAO cDAO = DAOFactory.getCursoDAO();

		model.addAttribute("cursos", cDAO.consultarTodos());

		return "adm/listaCursos";
	}
	
	@RequestMapping("listarPeriodos")
	public String listaPeriodos(Model model) {

		IPeriodoDAO pDAO = DAOFactory.getPeriodoDAO();

		model.addAttribute("periodos", pDAO.consultarTodos());

		return "adm/listaPeriodos";
	}
	
	@RequestMapping("listaTurmas")
	public String listaTurmas(Model model) {

		ITurmaDAO tDAO = DAOFactory.getTurmaDAO();

		model.addAttribute("turmas", tDAO.consultarTodos());

		return "adm/listaTurmas";
	}
	
	@RequestMapping("turmasDisciplinas")
	public ModelAndView turmasDisciplinas() {
		
		ModelAndView mav = new ModelAndView("adm/listaTurmasDisciplinas");
		ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
		mav.addObject("turmasDisciplinas", tdDAO.consultarTodos());
				
		return mav;
	}
	
}
