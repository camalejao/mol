package mol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mol.dao.DAOFactory;
import mol.dao.IDisciplinaDAO;
import mol.model.curso.disciplina.Disciplina;

@Controller
public class DisciplinaController {
	
	@RequestMapping("excluirDisciplina")
	public String excluiDisciplina(@RequestParam("disciplina") Disciplina d) {
		IDisciplinaDAO dDAO = DAOFactory.getDisciplinaDAO();
		dDAO.remover(d);
		return "redirect:listarDisciplinas";
	}
}
