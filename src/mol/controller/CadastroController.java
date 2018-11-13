package mol.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import mol.model.user.Usuario;
import mol.util.MailSender;
import mol.util.PasswordGenerator;

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
		aluno.setStatus(StatusEntidade.INATIVO);
		aluno.setTipo(TipoUsuario.ALUNO);
		String senha = PasswordGenerator.generatePassword(6);
		aluno.setSenha(senha);
		aluno.setSenha(aluno.senhaSHA());
		IAlunoDAO aDAO = DAOFactory.getAlunoDAO();
		aDAO.inserir(aluno);
		MailSender.enviarEmail(aluno.getEmail(), "Você está cadastrado na Monitoria Online do IFAL!",
				"Sua senha é: " + senha);
		
		return new ModelAndView("index");
	}
	
	@RequestMapping("cadastraProfessor")
	public ModelAndView insereProfessor(@Valid @ModelAttribute("professor") Professor professor, BindingResult bindingResult,
			Model model, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("formProfessor", "professor", professor);
		}
		professor.setStatus(StatusEntidade.INATIVO);
		professor.setTipo(TipoUsuario.PROFESSOR);
		String senha = PasswordGenerator.generatePassword(6);
		professor.setSenha(senha);
		professor.setSenha(professor.senhaSHA());
		IProfessorDAO pDAO = DAOFactory.getProfessorDAO();
		pDAO.inserir(professor);
		MailSender.enviarEmail(professor.getEmail(), "Você está cadastrado na Monitoria Online do IFAL!",
				"Sua senha é :" + senha);
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

	@RequestMapping("editarDados")
	@ResponseBody
	public String editaDados(String nome, String email, String senha, String confirmacao, @RequestParam Usuario usuario, HttpSession session) {
		
		IUsuarioDAO uDAO = DAOFactory.getUsuarioDAO();
		if(nome.length()>=10 && nome.length()<=40 && !nome.trim().isEmpty())
			usuario.setNome(nome);
		if(email.length()<=50 && !email.trim().isEmpty() && uDAO.verificarPorEmail(email))
			usuario.setEmail(email);
		if(!senha.trim().isEmpty() && !confirmacao.trim().isEmpty() && senha.equals(confirmacao)) {
			usuario.setSenha(senha);
			usuario.setSenha(usuario.senhaSHA());
		}
		
		if(usuario.getTipo() == TipoUsuario.ADMINISTRADOR) {
			uDAO.alterar(usuario);
		}
		else if(usuario.getTipo() == TipoUsuario.ALUNO || usuario.getTipo() == TipoUsuario.MONITOR) {
			IAlunoDAO aDAO = DAOFactory.getAlunoDAO();
			Aluno a = (Aluno) usuario;
			aDAO.alterar(a);
		}
		else if(usuario.getTipo() == TipoUsuario.PROFESSOR) {
			IProfessorDAO pDAO = DAOFactory.getProfessorDAO();
			Professor p = (Professor) usuario;
			pDAO.alterar(p);
		}
		
		session.setAttribute("usuarioLogado", usuario);
		
		return "sucesso";
			
	}
}
