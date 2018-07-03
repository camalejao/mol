package mol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mol.dao.DAOFactory;
import mol.dao.IAlunoDAO;
import mol.dao.IProfessorDAO;
import mol.model.StatusEntidade;
import mol.model.user.Aluno;
import mol.model.user.Professor;
import mol.model.user.TipoUsuario;

@Controller
public class CadastroController {
	
	@RequestMapping("cadastroAluno")
	public String cadastroAluno() {
		return "formAluno";
	}
	
	@RequestMapping("cadastroProfessor")
	public String cadastroProfessor() {
		return "formProfessor";
	}
	
	@RequestMapping("cadastraAluno")
	public String insereAlnuo(Aluno aluno) {
		aluno.setStatus(StatusEntidade.ATIVO);
		aluno.setTipo(TipoUsuario.ALUNO);
		aluno.setSenha(aluno.senhaSHA());
		IAlunoDAO aDAO = DAOFactory.getAlunoDAO();
		aDAO.inserir(aluno);
		return "redirect:login";
	}
	
	@RequestMapping("cadastraProfessor")
	public String insereProfessor(Professor professor) {
		professor.setStatus(StatusEntidade.ATIVO);
		professor.setTipo(TipoUsuario.PROFESSOR);
		professor.setSenha(professor.senhaSHA());
		IProfessorDAO pDAO = DAOFactory.getProfessorDAO();
		pDAO.inserir(professor);
		return "redirect:login";
	}
}
