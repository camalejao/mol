package mol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mol.dao.DAOFactory;
import mol.dao.IAtividadeDAO;
import mol.dao.IRespostaDAO;
import mol.model.curso.atividade.Atividade;
import mol.model.curso.atividade.Resposta;
import mol.model.curso.atividade.StatusResposta;

@Controller
public class ProfessorCorrecaoController {
	
	@RequestMapping("respostasAtividade-{id}")
	public ModelAndView listaRespostas(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("professor/respostas");
		IRespostaDAO rDAO = DAOFactory.getRespostaDAO();
		IAtividadeDAO aDAO = DAOFactory.getAtividadeDAO();
		Atividade atv = aDAO.consultarPorId(id);
		mav.addObject("respostas", rDAO.consultarPorAtividade(atv));
		mav.addObject("atividade", atv);

		return mav;
	}

	@RequestMapping("visualizarResposta-{id}")
	public ModelAndView verResposta(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("professor/verResposta");
		IRespostaDAO rDAO = DAOFactory.getRespostaDAO();
		Resposta resposta = rDAO.consultarPorId(id);
		mav.addObject("resposta", resposta);
		mav.addObject("itensResp", resposta.getItens());

		return mav;
	}

	@RequestMapping("avaliarResposta")
	public ModelAndView avaliarResposta(Resposta avaliacao) {
		//se o prazo para envio ainda não estiver expirado, volta para a home
		if(avaliacao.getAtividade().verificaExpiracao())
			return new ModelAndView("redirect:home");
		
		IRespostaDAO rDAO = DAOFactory.getRespostaDAO();
		Resposta resposta = rDAO.consultarPorId(avaliacao.getId());
		ModelAndView mav = new ModelAndView("redirect:respostasAtividade-" + resposta.getAtividade().getId());
		resposta.setNota(avaliacao.getNota());
		resposta.setObservacoesProfessor(avaliacao.getObservacoesProfessor());
		resposta.setStatusResposta(StatusResposta.CORRIGIDA);
		rDAO.alterar(resposta);
		return mav;
	}
}
