package mol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mol.dao.DAOFactory;
import mol.dao.ITurmaDisciplinaAlunoDAO;
import mol.dao.ITurmaDisciplinaDAO;
import mol.model.curso.turma.TurmaDisciplina;

@Controller
public class AdmTurmaDisciplinaController {
	
	@RequestMapping("turmasDisciplinas")
	public ModelAndView turmasDisciplinas() {
		
		ModelAndView mav = new ModelAndView("adm/listaTurmasDisciplinas");
		ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
		mav.addObject("turmasDisciplinas", tdDAO.consultarTodos());
				
		return mav;
	}
	
	@RequestMapping("alunosTurma-{id}")
	public ModelAndView alunosTurmaDisciplina(@PathVariable Integer id) {
		
		ModelAndView mav = new ModelAndView("adm/listaTurmaDisciplinaAluno");
		ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
		ITurmaDisciplinaAlunoDAO tdaDAO = DAOFactory.getTurmaDisciplinaAlunoDAO();
		TurmaDisciplina td = tdDAO.consultarPorId(id);
		
		if(td!=null) {
			mav.addObject("turmaDisciplina", td);
			mav.addObject("alunos", tdaDAO.consultarPorTurmaDisciplina(td));
			return mav;
		} else
			return new ModelAndView("redirect:turmasDisciplinas");
		
	}
	
}
