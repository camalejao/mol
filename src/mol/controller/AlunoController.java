package mol.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mol.dao.DAOFactory;
import mol.dao.IAtividadeDAO;
import mol.dao.IRespostaDAO;
import mol.dao.ITurmaDisciplinaAlunoDAO;
import mol.model.StatusEntidade;
import mol.model.curso.turma.Atividade;
import mol.model.curso.turma.Resposta;
import mol.model.curso.turma.TurmaDisciplinaAluno;
import mol.model.user.Aluno;
import mol.model.user.Usuario;

@Controller
public class AlunoController {

	@RequestMapping("homeAluno")
	public ModelAndView inicio(HttpSession session) {
		ModelAndView mav = new ModelAndView("aluno/index");

		Aluno aluno = (Aluno) session.getAttribute("usuarioLogado");

		ITurmaDisciplinaAlunoDAO tdaDAO = DAOFactory.getTurmaDisciplinaAlunoDAO();
		IAtividadeDAO atvDAO = DAOFactory.getAtividadeDAO();
		List<Atividade> listAtv = new ArrayList<>();
		List<TurmaDisciplinaAluno> turmasDisc = tdaDAO.consultarPorAluno(aluno);
		
		for (TurmaDisciplinaAluno tda : turmasDisc) {
			List<Atividade> la = atvDAO.consultarPorTurmaDisciplina(tda.getTurmaDisciplina());
			if (!la.isEmpty()) {
				listAtv.addAll(la);
			}
		}

		mav.addObject("turmasDisc", turmasDisc);
		mav.addObject("atividades", listAtv);

		return mav;
	}
	
	@RequestMapping("responderAtividade-{id}")
	public ModelAndView responder(@PathVariable Integer id, HttpSession session) {
		ModelAndView mav = new ModelAndView("aluno/submeterResposta");
		IAtividadeDAO atvDAO = DAOFactory.getAtividadeDAO();
		session.setAttribute("idAtv", id);
		mav.addObject("atividade", atvDAO.consultarPorId(id));
		return mav;
	}
	
	@RequestMapping("enviarResposta")
	public String enviar(Resposta resposta, HttpSession session) {
		
		Aluno a = (Aluno)session.getAttribute("usuarioLogado");
		Integer id = (Integer)session.getAttribute("idAtv");
		session.removeAttribute("idAtv");
		
		IRespostaDAO rDAO = DAOFactory.getRespostaDAO();
		IAtividadeDAO atvDAO = DAOFactory.getAtividadeDAO();
		
		resposta.setAluno(a);
		resposta.setAtividade(atvDAO.consultarPorId(id));
		resposta.setStatus(StatusEntidade.ATIVO);
		resposta.setUsuarioLogado(a);
		
		rDAO.inserir(resposta);
		
		return "redirect:index";
	}
}
