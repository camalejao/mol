package mol.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mol.dao.DAOFactory;
import mol.dao.IAlunoDAO;
import mol.dao.ITurmaDisciplinaAlunoDAO;
import mol.dao.ITurmaDisciplinaDAO;
import mol.model.StatusEntidade;
import mol.model.curso.turma.TurmaDisciplina;
import mol.model.curso.turma.TurmaDisciplinaAluno;
import mol.model.user.Aluno;
import mol.model.user.Usuario;

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
	
}
