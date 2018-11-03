package mol.controller;

import java.util.Arrays;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mol.dao.DAOFactory;
import mol.dao.IAtividadeDAO;
import mol.dao.IItemAtividadeDAO;
import mol.dao.INivelAprendizagemDAO;
import mol.dao.ITurmaDisciplinaDAO;
import mol.model.StatusEntidade;
import mol.model.curso.atividade.Alternativa;
import mol.model.curso.atividade.Atividade;
import mol.model.curso.atividade.ItemAtividade;
import mol.model.curso.atividade.StatusAtividade;
import mol.model.curso.atividade.TipoItem;
import mol.model.curso.atividade.TipoSubmissao;
import mol.model.curso.atividade.Unidades;
import mol.model.curso.turma.TurmaDisciplina;
import mol.model.user.Professor;
import mol.model.user.Usuario;

@Controller
public class ProfessorAtividadeController {
	
	@RequestMapping("listarAtividades-{id}")
	public ModelAndView listaAtividades(@PathVariable Integer id, HttpSession session) {
		ModelAndView mav = new ModelAndView("professor/listaAtividades");
		
		IAtividadeDAO aDAO = DAOFactory.getAtividadeDAO();
		ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
		TurmaDisciplina td =  tdDAO.consultarPorId(id);
		Professor p = (Professor) session.getAttribute("usuarioLogado");
		
		if(td != null) {
			if(td.getProfessor().getId() != p.getId())
				return new ModelAndView("redirect:listarTurmas");
		} else if(td == null)
			return new ModelAndView("redirect:listarTurmas");
		
		mav.addObject("atividades", aDAO.consultarPorTurmaDisciplina(td));
		mav.addObject("td", td);
		
		return mav;
	}
	
	@RequestMapping("adicionarAtividade-{id}")
	public ModelAndView adicionarAtividade(@PathVariable Integer id, HttpSession session) {
		
		ModelAndView mav;
		ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
		INivelAprendizagemDAO naDAO = DAOFactory.getNivelAprendizagemDAO();
		Professor p = (Professor) session.getAttribute("usuarioLogado");
		TurmaDisciplina td = tdDAO.consultarPorId(id);
		
		if(td != null && td.getProfessor().getId() == p.getId()) {
			mav = new ModelAndView("professor/formAtividade");
			mav.addObject("novaAtividade", new Atividade());
			mav.addObject("turmaDisciplina", td);
			mav.addObject("unidades", Arrays.asList(Unidades.values()));
			mav.addObject("niveis", naDAO.consultarPorTurmaDisciplina(td));
			mav.addObject("status", Arrays.asList(StatusEntidade.values()));
			mav.addObject("statusAtividade", Arrays.asList(StatusAtividade.values()));
			mav.addObject("tipoSubmissao", Arrays.asList(TipoSubmissao.values()));
			mav.addObject("tiposItem", Arrays.asList(TipoItem.values()));
			return mav;
		}
		
		mav = new ModelAndView("redirect:listarTurmas");
		return mav;

	}

	@RequestMapping("cadastraAtividade")
	public ModelAndView salvaAtividade(@Valid @ModelAttribute("novaAtividade") Atividade atividade,
			BindingResult bindingResult, Model model, HttpSession session) {
		
		IAtividadeDAO aDAO = DAOFactory.getAtividadeDAO();
		if (bindingResult.hasErrors() || !aDAO.verfificarAtividadesNoNivelAnterior(atividade.getNivelAprendizagem(), atividade.getTurmaDisciplina())) {
			ModelAndView mav = new ModelAndView("professor/formAtividade");
			ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
			INivelAprendizagemDAO naDAO = DAOFactory.getNivelAprendizagemDAO();
			mav.addObject("novaAtividade", atividade);
			mav.addObject("turmaDisciplina", tdDAO.consultarPorId(atividade.getTurmaDisciplina().getId()));
			mav.addObject("unidades", Arrays.asList(Unidades.values()));
			mav.addObject("niveis", naDAO.consultarPorTurmaDisciplina(atividade.getTurmaDisciplina()));
			mav.addObject("status", Arrays.asList(StatusEntidade.values()));
			mav.addObject("statusAtividade", Arrays.asList(StatusAtividade.values()));
			mav.addObject("tipoSubmissao", Arrays.asList(TipoSubmissao.values()));

			return mav;
		} else {
			ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
			Usuario u = (Usuario) session.getAttribute("usuarioLogado");
			
			atividade.setUsuarioLogado(u);
			atividade.setStatusAtividade(StatusAtividade.CONSTRUCAO);
			atividade.setDocumento(atividade.getUpload().getBytes());
			atividade.setTipoDocumento(atividade.getUpload().getContentType());
			atividade.setNomeDocumento(atividade.getUpload().getOriginalFilename());
			
			aDAO.inserir(atividade);
			
			Integer quantidadeNiveis = atividade.getTurmaDisciplina().getQuantidadeNiveis();
			if(atividade.getNivelAprendizagem() > quantidadeNiveis)
				atividade.getTurmaDisciplina().setQuantidadeNiveis(quantidadeNiveis + 1);
			
			tdDAO.alterar(atividade.getTurmaDisciplina());
		}
		ModelAndView mav = new ModelAndView("redirect:editarAtividade-" + atividade.getId());
		return mav;
	}

	@RequestMapping("editarAtividade-{id}")
	public ModelAndView editarAtividade(@PathVariable Integer id, HttpSession session) {
		
		IAtividadeDAO aDAO = DAOFactory.getAtividadeDAO();
		ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
		IItemAtividadeDAO iDAO = DAOFactory.getItemAtividadeDAO();
		INivelAprendizagemDAO naDAO = DAOFactory.getNivelAprendizagemDAO();
		
		ModelAndView mav;
		Professor p = (Professor) session.getAttribute("usuarioLogado");
		Atividade atv = aDAO.consultarPorId(id);
		
		if (atv != null && atv.getTurmaDisciplina().getProfessor().getId() == p.getId()) {
			if(atv.getStatusAtividade() == StatusAtividade.CONSTRUCAO)
				mav = new ModelAndView("professor/edicaoAtividade");
			else
				mav = new ModelAndView("professor/atividadeLiberada");

			mav.addObject("atividade", atv);
			mav.addObject("turmaDisciplinas", tdDAO.consultarPorProfessor(p));
			mav.addObject("unidades", Arrays.asList(Unidades.values()));
			mav.addObject("niveis",  naDAO.consultarPorTurmaDisciplina(atv.getTurmaDisciplina()));
			mav.addObject("status", Arrays.asList(StatusEntidade.values()));
			mav.addObject("statusAtividade", Arrays.asList(StatusAtividade.values()));
			mav.addObject("tipoSubmissao", Arrays.asList(TipoSubmissao.values()));
			mav.addObject("itens", iDAO.consultarPorAtividade(atv));
			mav.addObject("item", new ItemAtividade());
			mav.addObject("alternativa", new Alternativa());
			
			return mav;
		} else {
			mav = new ModelAndView("redirect:listarTurmas");
			return mav;
		}
	}

	@RequestMapping("editaAtividade-{id}")
	public ModelAndView editaAtividade(@PathVariable Integer id, Atividade atividade, HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:listarAtividades-" + atividade.getTurmaDisciplina().getId());
		if (id != null && id > 0) {
			IAtividadeDAO aDAO = DAOFactory.getAtividadeDAO();
			ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
			Atividade antiga = aDAO.consultarPorId(id);
			Usuario u = (Usuario) session.getAttribute("usuarioLogado");
			antiga.setTitulo(atividade.getTitulo());
			antiga.setDescricao(atividade.getDescricao());
			antiga.setUnidade(atividade.getUnidade());
			antiga.setStatus(atividade.getStatus());
			antiga.setDataExpiracao(atividade.getDataExpiracao());
			antiga.setTurmaDisciplina(tdDAO.consultarPorId(atividade.getTurmaDisciplina().getId()));
			antiga.setUsuarioLogado(u);

			if (!atividade.getUpload().isEmpty()) {
				antiga.setDocumento(atividade.getUpload().getBytes());
				antiga.setTipoDocumento(atividade.getUpload().getContentType());
				antiga.setNomeDocumento(atividade.getUpload().getOriginalFilename());
			}

			aDAO.alterar(antiga);
		}
		return mav;
	}
	
	@RequestMapping("valorArquivo")
	public String defineValorArquivo(double valor, Atividade atividade) {
		if(valor > 0) {
			IAtividadeDAO aDAO = DAOFactory.getAtividadeDAO();
			atividade.setValorMaximo(valor);
			aDAO.alterar(atividade);
		}
		return "redirect:editarAtividade-"+atividade.getId();
	}
	
	@RequestMapping("liberarAtividade")
	public String liberaAtividade(Atividade atividade) {
		if(atividade.getValorMaximo() > 0) {
			IAtividadeDAO aDAO = DAOFactory.getAtividadeDAO();
			atividade.setStatusAtividade(StatusAtividade.LIBERADA);
			aDAO.alterar(atividade);
		}
		return "redirect:editarAtividade-"+atividade.getId();
	}
}
