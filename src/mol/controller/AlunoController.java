package mol.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import mol.dao.DAOFactory;
import mol.dao.IAtividadeDAO;
import mol.dao.IDuvidaDAO;
import mol.dao.IItemAtividadeDAO;
import mol.dao.IItemRespostaDAO;
import mol.dao.IRespostaDAO;
import mol.dao.IRespostaDuvidaDAO;
import mol.dao.ITopicoDAO;
import mol.dao.ITurmaDisciplinaAlunoDAO;
import mol.dao.ITurmaDisciplinaDAO;
import mol.model.StatusEntidade;
import mol.model.curso.atividade.Atividade;
import mol.model.curso.atividade.ItemAtividade;
import mol.model.curso.atividade.ItemResposta;
import mol.model.curso.atividade.Resposta;
import mol.model.curso.atividade.StatusResposta;
import mol.model.curso.atividade.TipoItem;
import mol.model.curso.atividade.TipoSubmissao;
import mol.model.curso.atividade.duvida.Duvida;
import mol.model.curso.atividade.duvida.RespostaDuvida;
import mol.model.curso.atividade.duvida.VisibilidadeDuvida;
import mol.model.curso.disciplina.Topico;
import mol.model.curso.turma.TurmaDisciplina;
import mol.model.curso.turma.TurmaDisciplinaAluno;
import mol.model.user.Aluno;
import mol.model.user.Usuario;
import mol.util.MailSender;

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
		List<TurmaDisciplinaAluno> turmasDiscAluno = tdaDAO.consultarPorAluno(aluno);

		for (TurmaDisciplinaAluno tda : turmasDiscAluno) {
			List<Atividade> lr = atvDAO.consultarRespondidas(tda);
			if (!lr.isEmpty()) {
				listResp.addAll(lr);
			}
			List<Atividade> lnr = atvDAO.consultarNaoRespondidas(tda);
			if (!lnr.isEmpty()) {
				listNaoResp.addAll(lnr);
			}
		}

		mav.addObject("turmasDisc", turmasDiscAluno);
		mav.addObject("respondidas", listResp);
		mav.addObject("naoRespondidas", listNaoResp);

		return mav;
	}

	@RequestMapping("responderAtividade-{id}")
	public ModelAndView responderAtividade(@PathVariable Integer id, HttpSession session) {
		
		ModelAndView mav = new ModelAndView("aluno/submeterResposta");
		
		IAtividadeDAO atvDAO = DAOFactory.getAtividadeDAO();
		IItemAtividadeDAO iaDAO = DAOFactory.getItemAtividadeDAO();
		IItemRespostaDAO irDAO = DAOFactory.getItemRespostaDAO();
		ITurmaDisciplinaAlunoDAO tdaDAO = DAOFactory.getTurmaDisciplinaAlunoDAO();
		
		session.setAttribute("idAtv", id);
		
		Atividade atv = atvDAO.consultarPorId(id);
		Aluno a = (Aluno)session.getAttribute("usuarioLogado");
		TurmaDisciplinaAluno tda = tdaDAO.consultarPorAlunoETurmaDisciplina(a, atv.getTurmaDisciplina());		
		
		//verifica se o aluno é da turma da atividade e se é adequada para o nível dele
		if(tda==null || tda.getNivelAtual()<atv.getNivelAprendizagem())
			return new ModelAndView("redirect:home");
		
		mav.addObject("atividade", atv);
		mav.addObject("resposta", new Resposta());
		mav.addObject("duvida", new Duvida());
		mav.addObject("aluno", a);
		mav.addObject("itens", iaDAO.consultarPorAtividade(atv));
		mav.addObject("respostaItens", irDAO.consultarNaoEnviadosPorAlunoAtividade(a, atv));
		mav.addObject("visibilidade", Arrays.asList(VisibilidadeDuvida.values()));

		return mav;
	}

	@RequestMapping("enviarResposta")
	public String enviar(Resposta resposta, HttpSession session) {	
		
		Aluno a = (Aluno) session.getAttribute("usuarioLogado");
		Integer id = (Integer) session.getAttribute("idAtv");
		session.removeAttribute("idAtv");

		IRespostaDAO rDAO = DAOFactory.getRespostaDAO();
		IAtividadeDAO atvDAO = DAOFactory.getAtividadeDAO();
		IItemAtividadeDAO iaDAO = DAOFactory.getItemAtividadeDAO();
		IItemRespostaDAO irDAO = DAOFactory.getItemRespostaDAO();
		Atividade atv = atvDAO.consultarPorId(id);
		Resposta controle = rDAO.consultarPorAtividadeAluno(atv, a);
		List<ItemResposta> listaIR = irDAO.consultarPorAlunoAtividade(a, atv);
		List<ItemAtividade> listaIA = iaDAO.consultarPorAtividade(atv);
		
		//se a atividade estiver expirada, volta para a home
		if((controle!=null) && (controle.getAtividade().verificaExpiracao()==false))
			return "redirect:home";
		
		if(listaIA!=null) {
			if(listaIA.size() != irDAO.consultarNaoEnviadosPorAlunoAtividade(a, atv).size())
				return "redirect:responderAtividade-" + id;
		}
		
		//em caso de nova resposta, exclui a submissao anterior (e seus itens)
		if(controle != null && controle.getStatus()==StatusEntidade.ATIVO){
			
			rDAO.remover(controle);
			
			List<ItemResposta> listaControle = new ArrayList<>();
			listaControle.addAll(listaIR);
			
			for(ItemResposta ir : listaControle) {
				if(ir.isEnviado()) {
					irDAO.remover(ir);
					listaIR.remove(ir);
				}
			}
		}
		
		if(atv.getTipoSubmissao() == TipoSubmissao.ARQUIVO)
			resposta.setNota(0);
		else if(atv.getTipoSubmissao() == TipoSubmissao.ITENS) {
			double nota = 0;
			for(ItemResposta ir : listaIR) {
				nota += ir.getNota();
			}
			resposta.setNota(nota);
		}
		
		resposta.setAluno(a);
		resposta.setAtividade(atv);
		resposta.setStatus(StatusEntidade.ATIVO);
		resposta.setUsuarioLogado(a);
		if(atv.getTipoSubmissao() == TipoSubmissao.ARQUIVO) {
			if(resposta.getUpload()!=null) {
				resposta.setDocumentoResposta(resposta.getUpload().getBytes());
				resposta.setTipoDocumentoResposta(resposta.getUpload().getContentType());
				resposta.setNomeDocumentoResposta(resposta.getUpload().getOriginalFilename());
			}
			else
				return "redirect:home";
		}
		resposta.setStatusResposta(StatusResposta.NAO_CORRIGIDA);
		for(ItemResposta ir : listaIR) {
			ir.setEnviado(true);
			irDAO.alterar(ir);
		}
		resposta.setItens(listaIR);
		
		rDAO.inserir(resposta);

		MailSender.enviarEmail(a.getEmail(), "Resposta submetida com sucesso!",
				"Sua resposta para a atividade '" + resposta.getAtividade().getTitulo() + "' foi enviada com sucesso.");

		return "redirect:index";
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
		
	@RequestMapping("requisitaItem")
	@ResponseBody
	public ItemAtividade retornaItem(Integer idItem) {
		IItemAtividadeDAO iaDAO = DAOFactory.getItemAtividadeDAO();
		ItemAtividade item = iaDAO.consultarPorId(idItem);
		return item;
	}
	
	@RequestMapping("requisitaItemResposta")
	@ResponseBody
	public ItemResposta retornaItemResposta(Integer idItem, Integer idAluno) {
		IItemRespostaDAO irDAO = DAOFactory.getItemRespostaDAO();
		ItemResposta itemResp = irDAO.consultarNaoEnviadoPorIdItemIdAluno(idItem, idAluno);
		return itemResp;
	}
	
	@RequestMapping("salvarRespostaItem")
	public String salvaRespostaItem(ItemResposta itemResposta, HttpSession session) {
		//verifica se o item foi preenchido
		if(itemResposta.getItem().getTipoItem()==TipoItem.DISCURSIVO) {
			if(itemResposta.getTexto()==null || itemResposta.getTexto().isEmpty())
			return "redirect:responderAtividade-" + itemResposta.getItem().getAtividade().getId();
		}
		else if (itemResposta.getItem().getTipoItem()==TipoItem.MULTIPLA_ESCOLHA) {
			if(itemResposta.getAlternativa()==null) 
				return "redirect:responderAtividade-" + itemResposta.getItem().getAtividade().getId();
		}
		
		Aluno a = (Aluno)session.getAttribute("usuarioLogado");
		IItemRespostaDAO irDAO = DAOFactory.getItemRespostaDAO();
		ItemResposta controle = irDAO.consultarNaoEnviadoPorIdItemIdAluno(itemResposta.getItem().getId(), a.getId());
		if(controle!=null) {
			if(itemResposta.getItem().getTipoItem()==TipoItem.DISCURSIVO)
				controle.setTexto(itemResposta.getTexto());
			else if (itemResposta.getItem().getTipoItem()==TipoItem.MULTIPLA_ESCOLHA) {
				controle.setAlternativa(itemResposta.getAlternativa());
				if(itemResposta.getAlternativa().getCorreta())
					controle.setNota(itemResposta.getItem().getValor());
				else
					controle.setNota(0);
			}
			irDAO.alterar(controle);
		}else {
			itemResposta.setStatus(StatusEntidade.ATIVO);
			itemResposta.setUsuarioLogado(a);
			itemResposta.setAluno(a);
			itemResposta.setEnviado(false);
			if(itemResposta.getItem().getTipoItem()==TipoItem.DISCURSIVO)
				itemResposta.setNota(0);
			else if (itemResposta.getItem().getTipoItem()==TipoItem.MULTIPLA_ESCOLHA) {
				if(itemResposta.getAlternativa().getCorreta())
					itemResposta.setNota(itemResposta.getItem().getValor());
				else
					itemResposta.setNota(0);
			}
				
			irDAO.inserir(itemResposta);
		}		
		return "redirect:responderAtividade-" + itemResposta.getItem().getAtividade().getId();
	}
	
	@RequestMapping("adicionarDuvida")
	public String adicionarDuvida(@ModelAttribute("duvida") Duvida duvida, HttpSession session) {
		IDuvidaDAO dDAO = DAOFactory.getDuvidaDAO();
		Aluno a = (Aluno) session.getAttribute("usuarioLogado");
		duvida.setStatus(StatusEntidade.ATIVO);
		duvida.setUsuarioLogado(a);
		duvida.setAluno(a);
		dDAO.inserir(duvida);
		return "redirect:responderAtividade-"+duvida.getItem().getAtividade().getId();
	}
	
	@RequestMapping("minhasDuvidas")
	public ModelAndView minhasDuvidas(HttpSession session) {
		ModelAndView mav = new ModelAndView("aluno/duvidasAluno");
		
		IDuvidaDAO dDAO = DAOFactory.getDuvidaDAO();
		IRespostaDuvidaDAO rdDAO = DAOFactory.getRespostaDuvidaDAO();
		Aluno a = (Aluno) session.getAttribute("usuarioLogado");
		
		mav.addObject("duvidas", dDAO.consultarDuvidasPorAluno(a));
		mav.addObject("respostas", rdDAO.consultarPorAluno(a));
		mav.addObject("resposta", new RespostaDuvida());
		
		return mav;
	}
	
	@RequestMapping("verDuvidas-{id}")
	public ModelAndView listaDuvidas(@PathVariable Integer id, HttpSession session) {
		ModelAndView mav = new ModelAndView("aluno/duvidasAtividade");
		IAtividadeDAO aDAO = DAOFactory.getAtividadeDAO();
		IDuvidaDAO dDAO = DAOFactory.getDuvidaDAO();
		IRespostaDuvidaDAO rdDAO = DAOFactory.getRespostaDuvidaDAO();
		Atividade atv = aDAO.consultarPorId(id);
		mav.addObject("atividade", atv);
		mav.addObject("duvidas", dDAO.consultarDuvidasPublicasPorAtividade(atv));
		mav.addObject("respostas", rdDAO.consultarPorAtividade(atv));
		mav.addObject("resposta", new RespostaDuvida());
		
		return mav;
	}
}
