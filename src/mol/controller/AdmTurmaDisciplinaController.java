package mol.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mol.dao.DAOFactory;
import mol.dao.IAlunoDAO;
import mol.dao.IDisciplinaDAO;
import mol.dao.IProfessorDAO;
import mol.dao.ITurmaDAO;
import mol.dao.ITurmaDisciplinaAlunoDAO;
import mol.dao.ITurmaDisciplinaDAO;
import mol.model.StatusEntidade;
import mol.model.curso.disciplina.Sumario;
import mol.model.curso.turma.TipoCalculo;
import mol.model.curso.turma.TurmaDisciplina;
import mol.model.curso.turma.TurmaDisciplinaAluno;
import mol.model.user.Aluno;
import mol.model.user.Usuario;

@Controller
public class AdmTurmaDisciplinaController {
		
	@RequestMapping("alunosTurma-{id}")
	public ModelAndView alunosTurmaDisciplina(@PathVariable Integer id) {
		
		ModelAndView mav = new ModelAndView("adm/listaTurmaDisciplinaAluno");
		ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
		ITurmaDisciplinaAlunoDAO tdaDAO = DAOFactory.getTurmaDisciplinaAlunoDAO();
		IAlunoDAO aDAO = DAOFactory.getAlunoDAO();
		TurmaDisciplina td = tdDAO.consultarPorId(id);
		
		if(td!=null) {
			mav.addObject("turmaDisciplina", td);
			mav.addObject("alunos", tdaDAO.consultarAtivosEInativosPorTurmaDisciplina(td));
			mav.addObject("novosAlunos", aDAO.consultarAlunosNaoInseridosNaTurma(td));
			return mav;
		} else
			return new ModelAndView("redirect:turmasDisciplinas");
		
	}
	
	@RequestMapping("adicionarTurmaDisciplinaAluno")
	public ModelAndView adicionaTurmaDiscAluno(@RequestParam TurmaDisciplina turmaDisciplina,
			@RequestParam List<Aluno> alunos, HttpSession session) {
		
		ModelAndView mav = new ModelAndView("redirect:alunosTurma-"+turmaDisciplina.getId());
		Usuario u = (Usuario) session.getAttribute("usuarioLogado");
		ITurmaDisciplinaAlunoDAO tdaDAO = DAOFactory.getTurmaDisciplinaAlunoDAO();
		for(Aluno a : alunos) {
			TurmaDisciplinaAluno tda = new TurmaDisciplinaAluno();
			tda.setTurmaDisciplina(turmaDisciplina);
			tda.setAluno(a);
			tda.setNivelAtual(1);
			tda.setUsuarioLogado(u);
			tda.setStatus(StatusEntidade.ATIVO);
			tdaDAO.inserir(tda);
		}
		return mav;
	}
	
	@RequestMapping("inativarTurmaDisciplinaAluno")
	public ModelAndView inativaTurmaDiscAluno(@RequestParam TurmaDisciplinaAluno turmaDisciplinaAluno, HttpSession session) {
		
		ModelAndView mav = new ModelAndView("redirect:alunosTurma-"+turmaDisciplinaAluno.getTurmaDisciplina().getId());
		Usuario u = (Usuario) session.getAttribute("usuarioLogado");
		ITurmaDisciplinaAlunoDAO tdaDAO = DAOFactory.getTurmaDisciplinaAlunoDAO();
		turmaDisciplinaAluno.setStatus(StatusEntidade.INATIVO);
		turmaDisciplinaAluno.setUsuarioLogado(u);
		tdaDAO.alterar(turmaDisciplinaAluno);
		
		return mav;
	}
	
	@RequestMapping("reativarTurmaDisciplinaAluno")
	public ModelAndView reativaTurmaDiscAluno(@RequestParam TurmaDisciplinaAluno turmaDisciplinaAluno, HttpSession session) {
		
		ModelAndView mav = new ModelAndView("redirect:alunosTurma-"+turmaDisciplinaAluno.getTurmaDisciplina().getId());
		Usuario u = (Usuario) session.getAttribute("usuarioLogado");
		ITurmaDisciplinaAlunoDAO tdaDAO = DAOFactory.getTurmaDisciplinaAlunoDAO();
		turmaDisciplinaAluno.setStatus(StatusEntidade.ATIVO);
		turmaDisciplinaAluno.setUsuarioLogado(u);
		tdaDAO.alterar(turmaDisciplinaAluno);
		
		return mav;
	}
	
	@RequestMapping("cadastroTurmaDisciplina")
	public ModelAndView cadastroTD() {
		
		ModelAndView mav = new ModelAndView("adm/formularioTurmaDisciplina");
		IDisciplinaDAO dDAO = DAOFactory.getDisciplinaDAO();
		ITurmaDAO tDAO = DAOFactory.getTurmaDAO();
		IProfessorDAO pDAO = DAOFactory.getProfessorDAO();
		mav.addObject("disciplinas", dDAO.consultarTodos());
		mav.addObject("turmas", tDAO.consultarTodos());
		mav.addObject("professores", pDAO.consultarTodos());
		mav.addObject("tiposCalculo", Arrays.asList(TipoCalculo.values()));
		mav.addObject("turmaDisciplina", new TurmaDisciplina());
				
		return mav;
	}
	
	@RequestMapping("insereTurmaDisciplina")
	public ModelAndView insereTurmaDisciplina(@Valid @ModelAttribute("turmaDisciplina") TurmaDisciplina turmaDisciplina,
			HttpSession session) {
				
		ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
		Usuario u = (Usuario) session.getAttribute("usuarioLogado");
		Sumario sumarioTurma = new Sumario();
		
		sumarioTurma.setTurmaDisciplina(turmaDisciplina);
		sumarioTurma.setUsuarioLogado(u);
		sumarioTurma.setStatus(StatusEntidade.ATIVO);
		
		turmaDisciplina.setSumarioTurma(sumarioTurma);
		turmaDisciplina.setUsuarioLogado(u);
		turmaDisciplina.setStatus(StatusEntidade.ATIVO);
		
		tdDAO.inserir(turmaDisciplina);
		
		return new ModelAndView("redirect:turmasDisciplinas");
	}
}
