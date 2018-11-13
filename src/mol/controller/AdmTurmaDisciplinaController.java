package mol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mol.dao.DAOFactory;
import mol.dao.ITurmaDisciplinaDAO;

@Controller
public class AdmTurmaDisciplinaController {
	
	@RequestMapping("turmasDisciplinas")
	public ModelAndView turmasDisciplinas() {
		
		ModelAndView mav = new ModelAndView("adm/listaTurmasDisciplinas");
		ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
		mav.addObject("turmasDisciplinas", tdDAO.consultarTodos());
				
		return mav;
	}
	
}
