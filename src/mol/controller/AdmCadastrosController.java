package mol.controller;

import java.util.Arrays;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mol.dao.DAOFactory;
import mol.dao.IAlunoDAO;
import mol.dao.ICursoDAO;
import mol.dao.IDisciplinaDAO;
import mol.dao.IMonitorDAO;
import mol.dao.IPeriodoDAO;
import mol.dao.IProfessorDAO;
import mol.dao.ITurmaDAO;
import mol.dao.IUsuarioDAO;
import mol.model.StatusEntidade;
import mol.model.curso.Curso;
import mol.model.curso.EntradaPeriodo;
import mol.model.curso.ModalidadeCurso;
import mol.model.curso.Periodo;
import mol.model.curso.disciplina.Disciplina;
import mol.model.curso.turma.Turma;
import mol.model.curso.turma.TurnoTurma;
import mol.model.user.Aluno;
import mol.model.user.Monitor;
import mol.model.user.Professor;
import mol.model.user.TipoUsuario;
import mol.model.user.Usuario;
import mol.util.MailSender;
import mol.util.PasswordGenerator;

@Controller
public class AdmCadastrosController {
	
	@RequestMapping("cadastrarCurso")
	public ModelAndView formCurso() {

		ModelAndView mav = new ModelAndView("adm/formularioCurso");
		mav.addObject("modalidades", Arrays.asList(ModalidadeCurso.values()));
		mav.addObject("curso", new Curso());

		return mav;
	}
	
	@RequestMapping("insereCurso")
	public ModelAndView insereCurso(@Valid @ModelAttribute("curso") Curso curso, BindingResult bindingResult,
			Model model, HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			return new ModelAndView("adm/formularioCurso", "curso", curso);
		}
				
		Usuario user = (Usuario) session.getAttribute("usuarioLogado");
		curso.setUsuarioLogado(user);
		curso.setStatus(StatusEntidade.ATIVO);

		ICursoDAO cDAO = DAOFactory.getCursoDAO();
		cDAO.inserir(curso);
				
		return new ModelAndView("redirect:listarCursos");
	}
	
	@RequestMapping("cadastrarPeriodo")
	public ModelAndView formPeriodo() {

		ModelAndView mav = new ModelAndView("adm/formularioPeriodo");
		mav.addObject("entrada", Arrays.asList(EntradaPeriodo.values()));
		mav.addObject("periodo", new Periodo());

		return mav;
	}
	
	@RequestMapping("inserePeriodo")
	public ModelAndView inserePeriodo(@ModelAttribute("periodo") Periodo periodo, HttpSession session) {
		
		Usuario user = (Usuario) session.getAttribute("usuarioLogado");
		periodo.setUsuarioLogado(user);
		periodo.setStatus(StatusEntidade.ATIVO);

		IPeriodoDAO pDAO = DAOFactory.getPeriodoDAO();
		pDAO.inserir(periodo);
				
		return new ModelAndView("redirect:listarPeriodos");
	}
	
	@RequestMapping("cadastrarDisciplina")
	public ModelAndView formDisciplina() {
		ModelAndView mav = new ModelAndView("adm/formularioDisciplina", "disciplina", new Disciplina());
		return mav;
	}

	@RequestMapping("insereDisciplina")
	public ModelAndView insereDisciplina(@Valid @ModelAttribute("disciplina") Disciplina disciplina,
			BindingResult bindingResult, Model model, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("adm/formularioDisciplina", "disciplina", disciplina);
		}
		Usuario user = (Usuario) session.getAttribute("usuarioLogado");
		disciplina.setUsuarioLogado(user);
		disciplina.setStatus(StatusEntidade.ATIVO);

		IDisciplinaDAO discDAO = DAOFactory.getDisciplinaDAO();
		discDAO.inserir(disciplina);

		return new ModelAndView("adm/sucesso");
	}
	
	@RequestMapping("cadastrarUsuario")
	public ModelAndView seleciona() {

		ModelAndView mav = new ModelAndView("adm/selecaoTipoUsuario");
		mav.addObject("tipos", Arrays.asList(TipoUsuario.values()));
		mav.addObject("usuario", new Usuario());

		return mav;
	}

	@RequestMapping("selecionarTipoUsuario")
	public String encaminhaCadastro(@ModelAttribute("usuario") Usuario u) {

		switch (u.getTipo()) {
		case PROFESSOR:
			return "redirect:cadastrarProfessor";
		case ALUNO:
			return "redirect:cadastrarAluno";
		case MONITOR:
			return "redirect:cadastrarMonitor";
		case ADMINISTRADOR:
			return "redirect:cadastrarAdm";
		default:
			return "redirect:homeAdm";
		}
	}
	
	@RequestMapping("cadastrarAluno")
	public ModelAndView formAluno() {

		ModelAndView mav = new ModelAndView("adm/formularioAluno");
		mav.addObject("aluno", new Aluno());
		
		return mav;
	}

	@RequestMapping("insereAluno")
	public ModelAndView insereAluno(@Valid @ModelAttribute("aluno") Aluno aluno, BindingResult bindingResult,
			Model model, HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			return new ModelAndView("adm/formularioAluno", "aluno", aluno);
		}
		
		Usuario user = (Usuario) session.getAttribute("usuarioLogado");
		aluno.setUsuarioLogado(user);
		String senha = PasswordGenerator.generatePassword(6);
		aluno.setSenha(senha);
		aluno.setSenha(aluno.senhaSHA());
		aluno.setTipo(TipoUsuario.ALUNO);
		aluno.setStatus(StatusEntidade.INATIVO);

		IAlunoDAO alunoDAO = DAOFactory.getAlunoDAO();
		alunoDAO.inserir(aluno);
		
		MailSender.enviarEmail(aluno.getEmail(), "Você está cadastrado na Monitoria Online do IFAL!",
				"Sua senha é: " + senha);

		return new ModelAndView("adm/sucesso");
	}

	@RequestMapping("cadastrarAdm")
	public ModelAndView formAdm() {

		ModelAndView mav = new ModelAndView("adm/formularioAdm");
		mav.addObject("adm", new Usuario());

		return mav;
	}

	@RequestMapping("insereAdm")
	public ModelAndView insereAdm(@Valid @ModelAttribute("adm") Usuario adm, BindingResult bindingResult,
			Model model, HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			return new ModelAndView("adm/formularioAdm", "adm", adm);
		}
		
		
		Usuario user = (Usuario) session.getAttribute("usuarioLogado");
		String senha = PasswordGenerator.generatePassword(6);
		adm.setUsuarioLogado(user);
		adm.setSenha(senha);
		adm.setSenha(adm.senhaSHA());
		adm.setTipo(TipoUsuario.ADMINISTRADOR);
		adm.setStatus(StatusEntidade.INATIVO);

		IUsuarioDAO uDAO = DAOFactory.getUsuarioDAO();
		uDAO.inserir(adm);
		
		MailSender.enviarEmail(adm.getEmail(), "Você está cadastrado na Monitoria Online do IFAL!",
				"Sua senha é: " + senha);
		
		return new ModelAndView("adm/sucesso");
	}

	@RequestMapping("cadastrarProfessor")
	public ModelAndView formProfessor() {

		ModelAndView mav = new ModelAndView("adm/formularioProfessor");
		mav.addObject("professor", new Professor());

		return mav;
	}

	@RequestMapping("insereProfessor")
	public ModelAndView insereProfessor(@Valid @ModelAttribute("professor") Professor professor, BindingResult bindingResult,
			Model model, HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			return new ModelAndView("adm/formularioProfessor", "professor", professor);
		}
		
		Usuario user = (Usuario) session.getAttribute("usuarioLogado");
		professor.setUsuarioLogado(user);
		String senha = PasswordGenerator.generatePassword(6);
		professor.setSenha(senha);
		professor.setSenha(professor.senhaSHA());
		professor.setTipo(TipoUsuario.PROFESSOR);
		professor.setStatus(StatusEntidade.INATIVO);

		IProfessorDAO professorDAO = DAOFactory.getProfessorDAO();
		professorDAO.inserir(professor);
		
		MailSender.enviarEmail(professor.getEmail(), "Você está cadastrado na Monitoria Online do IFAL!",
				"Sua senha é: " + senha);

		return new ModelAndView("adm/sucesso");
	}
	
	@RequestMapping("cadastrarMonitor")
	public ModelAndView formMonitor() {

		IAlunoDAO alunoDAO = DAOFactory.getAlunoDAO();
		IDisciplinaDAO discDAO = DAOFactory.getDisciplinaDAO();

		ModelAndView mav = new ModelAndView("adm/formularioMonitor");
		mav.addObject("alunos", alunoDAO.consultarPossiveisMonitores());
		mav.addObject("disciplinas", discDAO.consultarTodos());
		mav.addObject("monitor", new Monitor());

		return mav;
	}

	@RequestMapping("insereMonitor")
	public String insereMonitor(@ModelAttribute("monitor") Monitor monitor, HttpSession session) {

		IMonitorDAO monitorDAO = DAOFactory.getMonitorDAO();
		IAlunoDAO alunoDAO = DAOFactory.getAlunoDAO();

		Usuario user = (Usuario) session.getAttribute("usuarioLogado");
		monitor.setUsuarioLogado(user);
		monitor.setStatus(StatusEntidade.ATIVO);
		monitorDAO.inserir(monitor);

		monitor.getAluno().setTipo(TipoUsuario.MONITOR);
		alunoDAO.alterar(monitor.getAluno());

		return "adm/sucesso";
	}
	
	@RequestMapping("cadastroTurma")
	public ModelAndView formTurma() {

		IPeriodoDAO pDAO = DAOFactory.getPeriodoDAO();
		ICursoDAO cDAO = DAOFactory.getCursoDAO();

		ModelAndView mav = new ModelAndView("adm/formularioTurma");
		mav.addObject("periodos", pDAO.consultarTodos());
		mav.addObject("cursos", cDAO.consultarTodos());
		mav.addObject("turnos", Arrays.asList(TurnoTurma.values()));
		mav.addObject("turma", new Turma());

		return mav;
	}
	
	@RequestMapping("insereTurma")
	public ModelAndView insereTurma(@ModelAttribute("turma") Turma turma, HttpSession session) {
		
		Usuario user = (Usuario) session.getAttribute("usuarioLogado");
		turma.setUsuarioLogado(user);
		turma.setStatus(StatusEntidade.ATIVO);

		ITurmaDAO tDAO = DAOFactory.getTurmaDAO();
		tDAO.inserir(turma);
				
		return new ModelAndView("redirect:listaTurmas");
	}
}
