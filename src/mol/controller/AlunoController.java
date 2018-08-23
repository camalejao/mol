package mol.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mol.dao.DAOFactory;
import mol.dao.IAtividadeDAO;
import mol.dao.IItemAtividadeDAO;
import mol.dao.IRespostaDAO;
import mol.dao.ITopicoDAO;
import mol.dao.ITurmaDisciplinaAlunoDAO;
import mol.dao.ITurmaDisciplinaDAO;
import mol.model.StatusEntidade;
import mol.model.curso.atividade.Atividade;
import mol.model.curso.atividade.Resposta;
import mol.model.curso.atividade.StatusResposta;
import mol.model.curso.disciplina.Topico;
import mol.model.curso.turma.TurmaDisciplina;
import mol.model.curso.turma.TurmaDisciplinaAluno;
import mol.model.user.Aluno;
import mol.model.user.Usuario;
import mol.teste.MailSender;

@Controller
public class AlunoController {

	@RequestMapping("homeAluno")
	public ModelAndView inicio(HttpSession session) {
		ModelAndView mav = new ModelAndView("aluno/index");

		Aluno aluno = (Aluno) session.getAttribute("usuarioLogado");

		ITurmaDisciplinaAlunoDAO tdaDAO = DAOFactory.getTurmaDisciplinaAlunoDAO();
		IAtividadeDAO atvDAO = DAOFactory.getAtividadeDAO();
		List<Atividade> listResp = new ArrayList<>();
		List<Atividade> listNaoResp = new ArrayList<>();
		List<TurmaDisciplinaAluno> turmasDisc = tdaDAO.consultarPorAluno(aluno);

		for (TurmaDisciplinaAluno tda : turmasDisc) {
			List<Atividade> lr = atvDAO.consultarRespondidas(tda.getTurmaDisciplina(), aluno);
			if (!lr.isEmpty()) {
				listResp.addAll(lr);
			}
			List<Atividade> lnr = atvDAO.consultarNaoRespondidas(tda.getTurmaDisciplina(), aluno);
			if (!lnr.isEmpty()) {
				listNaoResp.addAll(lnr);
			}
		}

		mav.addObject("turmasDisc", turmasDisc);
		mav.addObject("respondidas", listResp);
		mav.addObject("naoRespondidas", listNaoResp);

		return mav;
	}

	@RequestMapping("responderAtividade-{id}")
	public ModelAndView responder(@PathVariable Integer id, HttpSession session) {
		ModelAndView mav = new ModelAndView("aluno/submeterResposta");
		IAtividadeDAO atvDAO = DAOFactory.getAtividadeDAO();
		IItemAtividadeDAO iDAO = DAOFactory.getItemAtividadeDAO();
		session.setAttribute("idAtv", id);
		mav.addObject("atividade", atvDAO.consultarPorId(id));
		mav.addObject("itens", iDAO.consultarPorIdAtividade(id));
		mav.addObject("resposta", new Resposta());

		return mav;
	}

	@RequestMapping("enviarResposta")
	public String enviar(Resposta resposta, HttpSession session) {	
		Aluno a = (Aluno) session.getAttribute("usuarioLogado");
		Integer id = (Integer) session.getAttribute("idAtv");
		session.removeAttribute("idAtv");

		IRespostaDAO rDAO = DAOFactory.getRespostaDAO();
		IAtividadeDAO atvDAO = DAOFactory.getAtividadeDAO();
		Atividade atv = atvDAO.consultarPorId(id);
		Resposta controle = rDAO.consultarPorAtividadeAluno(atv, a);
		
		//se a atividade estiver expirada, volta para a home
		if(!controle.getAtividade().verificaExpiracao())
			return "redirect:home";
		
		//em caso de nova resposta, exclui a submissao anterior
		if(controle != null){
			rDAO.remover(controle);
		}
		
		resposta.setAluno(a);
		resposta.setAtividade(atv);
		resposta.setStatus(StatusEntidade.ATIVO);
		resposta.setUsuarioLogado(a);
		resposta.setDocumentoResposta(resposta.getUpload().getBytes());
		resposta.setTipoDocumentoResposta(resposta.getUpload().getContentType());
		resposta.setNomeDocumentoResposta(resposta.getUpload().getOriginalFilename());
		resposta.setStatusResposta(StatusResposta.NAO_CORRIGIDA);

		rDAO.inserir(resposta);

		MailSender.enviarEmail(a.getEmail(), "Resposta submetida com sucesso!",
				"Sua resposta para a atividade '" + resposta.getAtividade().getTitulo() + "' foi enviada com sucesso.");

		return "redirect:index";
	}

	@RequestMapping(value = { "downloadAtividade-{id}" }, method = RequestMethod.GET)
	public void downloadAtividade(@PathVariable Integer id, HttpServletResponse resp) throws IOException {

		if (id != null && id > 0) {
			IAtividadeDAO aDAO = DAOFactory.getAtividadeDAO();
			Atividade atividade = aDAO.consultarPorId(id);
			resp.setContentType(atividade.getTipoDocumento());
			resp.setContentLength(atividade.getDocumento().length);
			resp.setHeader("Content-Disposition", "attachment; filename=\"" + atividade.getNomeDocumento() + "\"");
			FileCopyUtils.copy(atividade.getDocumento(), resp.getOutputStream());
		}
	}
	
	@RequestMapping("disciplinas")
	public ModelAndView telaDisciplinas(HttpSession session) {
		ModelAndView mav = new ModelAndView("aluno/disciplinas");
		
		Aluno aluno = (Aluno) session.getAttribute("usuarioLogado");
		
		ITurmaDisciplinaAlunoDAO tdaDAO = DAOFactory.getTurmaDisciplinaAlunoDAO();
		List<TurmaDisciplinaAluno> turmasDisc = tdaDAO.consultarPorAluno(aluno);
		
		mav.addObject("turmasDisc", turmasDisc);
		
		return mav;
	}
	
	@RequestMapping("verCorrecoes-{id}")
	public ModelAndView telaCorrecoes(@PathVariable Integer id, HttpSession session) {
		ModelAndView mav = new ModelAndView("aluno/correcoes");
		
		Aluno aluno = (Aluno) session.getAttribute("usuarioLogado");
		
		ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
		TurmaDisciplina turmaDisc = tdDAO.consultarPorId(id);
		
		IRespostaDAO rDAO = DAOFactory.getRespostaDAO();
		List<Resposta> respostas = rDAO.consultarCorrigidas(turmaDisc, aluno);
				
		mav.addObject("turmaDisc", turmaDisc);
		mav.addObject("respostas", respostas);	
		
		return mav;
	}

	@RequestMapping("sumario-{id}")
	public ModelAndView sumario(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("aluno/sumario");
		ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
		ITopicoDAO tDAO = DAOFactory.getTopicoDAO();
		TurmaDisciplina turmaDisc = tdDAO.consultarPorId(id);
		List<Topico> topicos = tDAO.consultarPorTurma(turmaDisc);
		mav.addObject("turmaDisc", turmaDisc);
		mav.addObject("topicos", topicos);
		return mav;
	}

}
