package mol.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mol.dao.DAOFactory;
import mol.dao.IDisciplinaDAO;
import mol.model.curso.disciplina.Disciplina;
import mol.model.user.Usuario;

@Controller
public class DisciplinaController {
	
	@RequestMapping("excluirDisciplina")
	public String excluiDisciplina(@RequestParam("disciplina") Disciplina d) {
		IDisciplinaDAO dDAO = DAOFactory.getDisciplinaDAO();
		dDAO.remover(d);
		return "redirect:listarDisciplinas";
	}
	
	@RequestMapping("editaDisciplina")
	public String editaDisciplina(@RequestParam("nome") String nome, @RequestParam("sigla") String sigla,
			@RequestParam("id") Integer id, HttpSession session) {
		Usuario u = (Usuario) session.getAttribute("usuarioLogado");
		IDisciplinaDAO dDAO = DAOFactory.getDisciplinaDAO();
		Disciplina d = dDAO.consultarPorId(id);
		if(nome.length()>=6 && nome.length()<=40)
			d.setNome(nome);
		if(sigla.length()>=3 && sigla.length()<=5 && dDAO.verificarPorSigla(sigla))
			d.setSigla(sigla);
		d.setUsuarioLogado(u);
		dDAO.alterar(d);
		return "redirect:listarDisciplinas";
	}
}
