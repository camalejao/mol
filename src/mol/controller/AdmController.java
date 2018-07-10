package mol.controller;

import java.util.Arrays;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mol.dao.DAOFactory;
import mol.dao.IDisciplinaDAO;
import mol.dao.IUsuarioDAO;
import mol.model.user.TipoUsuario;
import mol.model.user.Usuario;
import mol.dao.IMonitorDAO;
import mol.model.user.Monitor;
import mol.dao.IProfessorDAO;
import mol.model.user.Professor;
import mol.dao.IAlunoDAO;
import mol.model.user.Aluno;
import mol.model.StatusEntidade;
import mol.model.curso.disciplina.Disciplina;

@Controller
public class AdmController {
	
	@RequestMapping("homeAdm")
	public String inicio() {
        return "adm/index";
    }
	
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
	
	@RequestMapping("cadastrarUsuario")
	public ModelAndView seleciona() {
		
		ModelAndView mav = new ModelAndView("adm/selecaoTipoUsuario");
		mav.addObject("tipos", Arrays.asList(TipoUsuario.values()));
		mav.addObject("usuario", new Usuario());
		
		return mav;
	}

	@RequestMapping("selecionarTipoUsuario")
	public String encaminhaCadastro(@ModelAttribute("usuario") Usuario u) {
		
		switch(u.getTipo()) {
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
	
	@RequestMapping("cadastrarDisciplina")
	public String formDisciplina() {
		return "adm/formularioDisciplina";
	}
	
	@RequestMapping("insereDisciplina")
	public String insereDisciplina(Disciplina disciplina, HttpSession session) {
		
		Usuario user = (Usuario) session.getAttribute("usuarioLogado");
		
		IUsuarioDAO uDAO = DAOFactory.getUsuarioDAO();
		disciplina.setUsuarioLogado(uDAO.consultarPorId(user.getId()));
		disciplina.setStatus(StatusEntidade.ATIVO);
		
		IDisciplinaDAO discDAO = DAOFactory.getDisciplinaDAO();
		discDAO.inserir(disciplina);
		
		return "adm/sucesso";
	}
	
	@RequestMapping("cadastrarAluno")
	public ModelAndView formAluno() {

		ModelAndView mav = new ModelAndView("adm/formularioAluno");
		mav.addObject("aluno", new Aluno());

		return mav;
	}

	@RequestMapping("insereAluno")
	public String insereAluno(@ModelAttribute("aluno") Aluno aluno) {

		aluno.setSenha(aluno.senhaSHA());
		aluno.setTipo(TipoUsuario.ALUNO);
		aluno.setStatus(StatusEntidade.ATIVO);

		IAlunoDAO alunoDAO = DAOFactory.getAlunoDAO();
		alunoDAO.inserir(aluno);

		return "adm/sucesso";
	}
	
	@RequestMapping("cadastrarAdm")
	public ModelAndView formAdm() {

		ModelAndView mav = new ModelAndView("adm/formularioAdm");
		mav.addObject("adm", new Usuario());

		return mav;
	}

	@RequestMapping("insereAdm")
	public String insereAdm(@ModelAttribute("adm") Usuario adm) {

		adm.setSenha(adm.senhaSHA());
		adm.setTipo(TipoUsuario.ADMINISTRADOR);
		adm.setStatus(StatusEntidade.ATIVO);

		IUsuarioDAO uDAO = DAOFactory.getUsuarioDAO();
		uDAO.inserir(adm);

		return "adm/sucesso";
	}
	
	@RequestMapping("cadastrarProfessor")
	public ModelAndView formProfessor() {

		ModelAndView mav = new ModelAndView("adm/formularioProfessor");
		mav.addObject("professor", new Professor());

		return mav;
	}

	@RequestMapping("insereProfessor")
	public String insereProfessor(@ModelAttribute("professor") Professor professor) {

		professor.setSenha(professor.senhaSHA());
		professor.setTipo(TipoUsuario.PROFESSOR);
		professor.setStatus(StatusEntidade.ATIVO);

		IProfessorDAO professorDAO = DAOFactory.getProfessorDAO();
		professorDAO.inserir(professor);

		return "adm/sucesso";
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
		
		IUsuarioDAO uDAO = DAOFactory.getUsuarioDAO();
		IAlunoDAO alunoDAO = DAOFactory.getAlunoDAO();
		//IDisciplinaDAO discDAO = DAOFactory.getDisciplinaDAO();
		IMonitorDAO monitorDAO = DAOFactory.getMonitorDAO();
		
		Usuario user = (Usuario) session.getAttribute("usuarioLogado");
		Aluno aluno = alunoDAO.consultarPorMatricula(monitor.getAluno().getMatricula());
				
		monitor.setAluno(aluno);
		//monitor.setDisciplina(discDAO.consultarPorSigla(monitor.getDisciplina().getSigla()));
		monitor.setUsuarioLogado(uDAO.consultarPorId(user.getId()));
		monitorDAO.inserir(monitor);
		
		aluno.setTipo(TipoUsuario.MONITOR);
		alunoDAO.alterar(aluno);
				
		return "adm/sucesso";
	}
	
}
