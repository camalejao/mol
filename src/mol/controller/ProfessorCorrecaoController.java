package mol.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mol.dao.DAOFactory;
import mol.dao.IAtividadeDAO;
import mol.dao.IDesempenhoAlunoDAO;
import mol.dao.IItemRespostaDAO;
import mol.dao.IRespostaDAO;
import mol.dao.ITurmaDisciplinaAlunoDAO;
import mol.model.StatusEntidade;
import mol.model.curso.atividade.Atividade;
import mol.model.curso.atividade.ItemResposta;
import mol.model.curso.atividade.Resposta;
import mol.model.curso.atividade.StatusResposta;
import mol.model.curso.atividade.analiseDesempenho.DesempenhoAluno;
import mol.model.curso.turma.TurmaDisciplinaAluno;
import mol.model.user.Professor;
import mol.util.ParametrosGlobais;

@Controller
public class ProfessorCorrecaoController {
	
	@RequestMapping("respostasAtividade-{id}")
	public ModelAndView listaRespostas(@PathVariable Integer id, HttpSession session) {
		
		ModelAndView mav;
		IRespostaDAO rDAO = DAOFactory.getRespostaDAO();
		IAtividadeDAO aDAO = DAOFactory.getAtividadeDAO();
		Professor p = (Professor) session.getAttribute("usuarioLogado");
		Atividade atv = aDAO.consultarPorId(id);
		
		if(atv!=null && atv.getTurmaDisciplina().getProfessor().getId() == p.getId()) {
			mav = new ModelAndView("professor/respostas");
			mav.addObject("respostas", rDAO.consultarPorAtividade(atv));
			mav.addObject("atividade", atv);
			return mav;
		}
		
		mav = new ModelAndView("redirect:listarTurmas");
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
	
	@RequestMapping("avaliarItem")
	public String avaliarItem(@RequestParam ItemResposta item, double nota, @RequestParam Resposta resposta) {
		
		IRespostaDAO rDAO = DAOFactory.getRespostaDAO();
		IItemRespostaDAO irDAO = DAOFactory.getItemRespostaDAO();
		
		if(nota < 0)
			item.setNota(0);
		else if(nota > item.getItem().getValor())
			item.setNota(item.getItem().getValor());
		else
			item.setNota(nota);
		
		irDAO.alterar(item);
		
		
		List<ItemResposta> listaIR = irDAO.consultarEnviadosPorAlunoAtividade(resposta.getAluno(), resposta.getAtividade());
		double n=0;
		for(ItemResposta ir : listaIR) {
			n += ir.getNota();
		}
		resposta.setNota(n);
		rDAO.alterar(resposta);
		
		return "redirect:visualizarResposta-"+resposta.getId();
	}
	
	@RequestMapping("avaliarArquivo")
	public String avaliarArquivo(double nota, @RequestParam Resposta resposta) {
		
		IRespostaDAO rDAO = DAOFactory.getRespostaDAO();
				
		if(nota < 0)
			resposta.setNota(0);
		else if(nota > resposta.getAtividade().getValorMaximo())
			resposta.setNota(resposta.getAtividade().getValorMaximo());
		else
			resposta.setNota(nota);
		
		rDAO.alterar(resposta);
		
		
		return "redirect:visualizarResposta-"+resposta.getId();
	}
	
	@RequestMapping("avaliarResposta")
	public String avaliarResposta(@RequestParam Resposta resposta, String observacoes) {
		//se o prazo para envio ainda n�o estiver expirado, volta para a home
		if(resposta.getAtividade().verificaExpiracao())
			return "redirect:verResposta-" + resposta.getId();
		
		IRespostaDAO rDAO = DAOFactory.getRespostaDAO();
		ITurmaDisciplinaAlunoDAO tdaDAO = DAOFactory.getTurmaDisciplinaAlunoDAO();  
		IDesempenhoAlunoDAO daDAO = DAOFactory.getDesempenhoAlunoDAO();
		
		TurmaDisciplinaAluno tda = tdaDAO.consultarPorAlunoETurmaDisciplina(
				resposta.getAluno(), resposta.getAtividade().getTurmaDisciplina());
		
		resposta.setObservacoesProfessor(observacoes);
		resposta.setStatusResposta(StatusResposta.CORRIGIDA);
		
		rDAO.alterar(resposta);
		
		if(resposta.getAtividade().isMudancaNivel() && resposta.getAtividade().getNivelAprendizagem() == tda.getNivelAtual()) {
			
			double percentual = (resposta.getNota() / resposta.getAtividade().getValorMaximo()) * 100;
			DesempenhoAluno da = new DesempenhoAluno();
			da.setStatus(StatusEntidade.ATIVO);
			da.setPercentual(percentual);
			da.setNivel(resposta.getAtividade().getNivelAprendizagem());
			da.setTurmaDisciplinaAluno(tda);
			daDAO.inserir(da);
			
			if(percentual >= ParametrosGlobais.PERCENTUAL_APRENDIZAGEM_MINIMO) {
				Integer nivel = tda.getNivelAtual();
				nivel += 1;
				tda.setNivelAtual(nivel);
				tdaDAO.alterar(tda);
			}
				
		}
		
		return "redirect:respostasAtividade-" + resposta.getAtividade().getId();
	}
}
