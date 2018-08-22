package mol.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import mol.dao.DAOFactory;
import mol.dao.IAlunoDAO;
import mol.dao.IDisciplinaDAO;
import mol.dao.IProfessorDAO;
import mol.dao.IUsuarioDAO;
import mol.model.StatusEntidade;
import mol.model.user.Aluno;
import mol.model.user.Professor;
import mol.model.user.TipoUsuario;

@Controller
public class CadastroController {
	
	@RequestMapping("cadastroAluno")
	public ModelAndView cadastroAluno() {
		return new ModelAndView("formAluno", "aluno", new Aluno());
	}
	
	@RequestMapping("cadastroProfessor")
	public ModelAndView cadastroProfessor() {
		return new ModelAndView("formProfessor", "professor", new Professor());
	}
	
	@RequestMapping("cadastraAluno")
	public ModelAndView insereAlnuo(@Valid @ModelAttribute("aluno") Aluno aluno, BindingResult bindingResult,
			Model model, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("formAluno", "aluno", aluno);
		}
		aluno.setStatus(StatusEntidade.ATIVO);
		aluno.setTipo(TipoUsuario.ALUNO);
		aluno.setSenha(aluno.senhaSHA());
		IAlunoDAO aDAO = DAOFactory.getAlunoDAO();
		aDAO.inserir(aluno);
		return new ModelAndView("index");
	}
	
	@RequestMapping("cadastraProfessor")
	public ModelAndView insereProfessor(@Valid @ModelAttribute("professor") Professor professor, BindingResult bindingResult,
			Model model, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("formProfessor", "professor", professor);
		}
		professor.setStatus(StatusEntidade.ATIVO);
		professor.setTipo(TipoUsuario.PROFESSOR);
		professor.setSenha(professor.senhaSHA());
		IProfessorDAO pDAO = DAOFactory.getProfessorDAO();
		pDAO.inserir(professor);
		return new ModelAndView("index");
	}
	
	@RequestMapping("verificaEmail")
	@ResponseBody
	public String verificaEmail(String email) {
		IUsuarioDAO uDAO = DAOFactory.getUsuarioDAO();
		
		return uDAO.verificarPorEmail(email).toString();
	}
	
	@RequestMapping("verificaMatAluno")
	@ResponseBody
	public String verificaMatAluno(String matricula) {
		IAlunoDAO aDAO = DAOFactory.getAlunoDAO();
		
		return aDAO.verificarPorMatricula(matricula).toString();
	}
	
	@RequestMapping("verificaMatProf")
	@ResponseBody
	public String verificaMatProf(String matricula) {
		IProfessorDAO pDAO = DAOFactory.getProfessorDAO();
		
		return pDAO.verificarPorMatricula(matricula).toString();
	}
	
	@RequestMapping("verificaSigla")
	@ResponseBody
	public String verificaSigla(String sigla) {
		IDisciplinaDAO dDAO = DAOFactory.getDisciplinaDAO();
		
		return dDAO.verificarPorSigla(sigla).toString();
	}
}
