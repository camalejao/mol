package mol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
