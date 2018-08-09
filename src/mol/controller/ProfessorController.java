package mol.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;

import mol.dao.DAOFactory;
import mol.dao.IAtividadeDAO;
import mol.dao.IItemAtividadeDAO;
import mol.dao.IMaterialDidaticoDAO;
import mol.dao.IRespostaDAO;
import mol.dao.ITopicoDAO;
import mol.dao.ITurmaDisciplinaDAO;
import mol.model.StatusEntidade;
import mol.model.curso.disciplina.Topico;
import mol.model.curso.turma.Atividade;
import mol.model.curso.turma.ItemAtividade;
import mol.model.curso.turma.NivelAprendizagem;
import mol.model.curso.turma.Resposta;
import mol.model.curso.turma.StatusResposta;
import mol.model.curso.turma.TipoItem;
import mol.model.curso.turma.TurmaDisciplina;
import mol.model.curso.turma.Unidades;
import mol.model.materialDidatico.MaterialDidatico;
import mol.model.materialDidatico.TipoMaterialDidatico;
import mol.model.user.Professor;
import mol.model.user.Usuario;

@Controller
public class ProfessorController {

	@RequestMapping("homeProfessor")
	public String inicio() {
		return "professor/index";
	}

	@RequestMapping("gerenciarAtividades")
	public ModelAndView atividades(HttpSession session) {
		ModelAndView mav = new ModelAndView("professor/atividades");
		Professor p = (Professor) session.getAttribute("usuarioLogado");
		IAtividadeDAO aDAO = DAOFactory.getAtividadeDAO();
		ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
		mav.addObject("novaAtividade", new Atividade());
		mav.addObject("atividades", aDAO.consultarPorProfessor(p));
		mav.addObject("turmaDisciplinas", tdDAO.consultarPorProfessor(p));
		mav.addObject("unidades", Arrays.asList(Unidades.values()));
		mav.addObject("niveis", Arrays.asList(NivelAprendizagem.values()));
		mav.addObject("status", Arrays.asList(StatusEntidade.values()));

		return mav;
	}

	@RequestMapping("adicionarAtividade-{id}")
	public ModelAndView adicionarAtividade(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("professor/formAtividade");
		ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();

		mav.addObject("novaAtividade", new Atividade());
		mav.addObject("turmaDisciplina", tdDAO.consultarPorId(id));
		mav.addObject("unidades", Arrays.asList(Unidades.values()));
		mav.addObject("niveis", Arrays.asList(NivelAprendizagem.values()));
		mav.addObject("status", Arrays.asList(StatusEntidade.values()));
		mav.addObject("tiposItem", Arrays.asList(TipoItem.values()));

		return mav;
	}

	@RequestMapping("cadastraAtividade")
	public ModelAndView salvaAtividade(@Valid @ModelAttribute("novaAtividade") Atividade atividade,
			BindingResult bindingResult, Model model, HttpSession session) {
		if (bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("professor/formAtividade");
			ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
			mav.addObject("novaAtividade", atividade);
			mav.addObject("turmaDisciplina", tdDAO.consultarPorId(atividade.getTurmaDisciplina().getId()));
			mav.addObject("unidades", Arrays.asList(Unidades.values()));
			mav.addObject("niveis", Arrays.asList(NivelAprendizagem.values()));
			mav.addObject("status", Arrays.asList(StatusEntidade.values()));

			return mav;
		} else {
			Usuario u = (Usuario) session.getAttribute("usuarioLogado");

			IAtividadeDAO aDAO = DAOFactory.getAtividadeDAO();

			atividade.setUsuarioLogado(u);
			atividade.setStatus(StatusEntidade.ATIVO);
			atividade.setDocumento(atividade.getUpload().getBytes());
			atividade.setTipoDocumento(atividade.getUpload().getContentType());
			atividade.setNomeDocumento(atividade.getUpload().getOriginalFilename());

			aDAO.inserir(atividade);
		}
		ModelAndView mav = new ModelAndView("redirect:listarAtividades-" + atividade.getTurmaDisciplina().getId());
		return mav;
	}

	@RequestMapping("editarAtividade-{id}")
	public ModelAndView editarAtividade(@PathVariable Integer id, HttpSession session) {
		ModelAndView mav;
		if (id != null && id > 0) {
			IAtividadeDAO aDAO = DAOFactory.getAtividadeDAO();
			ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
			Professor p = (Professor) session.getAttribute("usuarioLogado");
			mav = new ModelAndView("professor/edicaoAtividade");
			mav.addObject("atividade", aDAO.consultarPorId(id));
			mav.addObject("turmaDisciplinas", tdDAO.consultarPorProfessor(p));
			mav.addObject("unidades", Arrays.asList(Unidades.values()));
			mav.addObject("niveis", Arrays.asList(NivelAprendizagem.values()));
			mav.addObject("status", Arrays.asList(StatusEntidade.values()));
			mav.addObject("item", new ItemAtividade());
			return mav;
		}
		mav = new ModelAndView("redirect:gerenciarAtividades");
		return mav;
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
			antiga.setValorMaximo(atividade.getValorMaximo());
			antiga.setPeso(atividade.getPeso());
			antiga.setUnidade(atividade.getUnidade());
			antiga.setNivel(atividade.getNivel());
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

	@RequestMapping("listarTurmas")
	public ModelAndView listaTurmas(HttpSession session) {
		ModelAndView mav = new ModelAndView("professor/listaTurmas");
		Professor p = (Professor) session.getAttribute("usuarioLogado");
		ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
		mav.addObject("turmasDisciplinas", tdDAO.consultarPorProfessor(p));
		return mav;
	}

	@RequestMapping("listarAtividades-{id}")
	public ModelAndView listaAtividades(@PathVariable Integer id, HttpSession session) {
		ModelAndView mav = new ModelAndView("professor/listaAtividades");
		IAtividadeDAO aDAO = DAOFactory.getAtividadeDAO();
		ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
		mav.addObject("atividades", aDAO.consultarPorIdTurmaDisciplina(id));
		mav.addObject("td", tdDAO.consultarPorId(id));
		return mav;
	}

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
		mav.addObject("resposta", rDAO.consultarPorId(id));

		return mav;
	}

	@RequestMapping("avaliarResposta")
	public ModelAndView avaliarResposta(Resposta avaliacao) {
		IRespostaDAO rDAO = DAOFactory.getRespostaDAO();
		Resposta resposta = rDAO.consultarPorId(avaliacao.getId());
		ModelAndView mav = new ModelAndView("redirect:respostasAtividade-" + resposta.getAtividade().getId());
		resposta.setNota(avaliacao.getNota());
		resposta.setObservacoesProfessor(avaliacao.getObservacoesProfessor());
		resposta.setStatusResposta(StatusResposta.CORRIGIDA);
		rDAO.alterar(resposta);
		return mav;
	}

	@RequestMapping("editarSumario-{id}")
	public ModelAndView sumario(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("professor/sumario");
		ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
		ITopicoDAO tDAO = DAOFactory.getTopicoDAO();
		TurmaDisciplina turmaDisc = tdDAO.consultarPorId(id);
		List<Topico> topicos = tDAO.consultarPorTurma(turmaDisc);
		mav.addObject("turmaDisc", turmaDisc);
		mav.addObject("topicos", topicos);
		mav.addObject("novoTopico", new Topico());
		mav.addObject("novoMaterialDidatico", new MaterialDidatico());
		mav.addObject("status", Arrays.asList(StatusEntidade.values()));
		mav.addObject("tiposMaterial", Arrays.asList(TipoMaterialDidatico.values()));

		return mav;
	}

	@RequestMapping("adicionaTopico")
	public String adiconaTopico(@ModelAttribute("novoTopico") Topico topico, HttpSession session) {
		ITopicoDAO tDAO = DAOFactory.getTopicoDAO();
		Usuario u = (Usuario) session.getAttribute("usuarioLogado");
		topico.setUsuarioLogado(u);
		topico.setStatus(StatusEntidade.ATIVO);
		tDAO.inserir(topico);
		return "redirect:editarSumario-" + topico.getSumario().getTurmaDisciplina().getId();
	}

	@RequestMapping("editaTopico")
	public String editaTopico(@RequestParam("topico") Topico topico, @RequestParam("status") StatusEntidade status,
			@RequestParam("descricao") String descricao, HttpSession session) {
		ITopicoDAO tDAO = DAOFactory.getTopicoDAO();
		Usuario u = (Usuario) session.getAttribute("usuarioLogado");
		topico.setUsuarioLogado(u);
		topico.setStatus(status);
		topico.setDescricao(descricao);
		tDAO.alterar(topico);
		return "redirect:editarSumario-" + topico.getSumario().getTurmaDisciplina().getId();
	}

	@RequestMapping("excluirTopico")
	public String excluiTopico(@RequestParam("topico") Topico topico) {
		ITopicoDAO tDAO = DAOFactory.getTopicoDAO();
		tDAO.remover(topico);
		return "redirect:editarSumario-" + topico.getSumario().getTurmaDisciplina().getId();
	}

	@RequestMapping("adicionaMaterialDidatico")
	public String adicionaMaterial(@ModelAttribute("novoMaterial") MaterialDidatico md, HttpSession session) {
		Usuario u = (Usuario) session.getAttribute("usuarioLogado");
		IMaterialDidaticoDAO mdDAO = DAOFactory.getMaterialDidaticoDAO();
		md.setStatus(StatusEntidade.ATIVO);
		md.setUsuarioLogado(u);
		md.setNomeMaterial(md.getUpload().getOriginalFilename());
		md.setTipoArquivo(md.getUpload().getContentType());
		md.setArquivo(md.getUpload().getBytes());
		mdDAO.inserir(md);
		return "redirect:editarSumario-" + md.getTopico().getSumario().getTurmaDisciplina().getId();
	}

	@RequestMapping("editaMaterialDidatico")
	public String editaMaterial(@ModelAttribute("novoMaterial") MaterialDidatico md, HttpSession session) {
		Usuario u = (Usuario) session.getAttribute("usuarioLogado");
		IMaterialDidaticoDAO mdDAO = DAOFactory.getMaterialDidaticoDAO();
		MaterialDidatico antigo = mdDAO.consultarPorId(md.getId());
		antigo.setUsuarioLogado(u);
		antigo.setStatus(md.getStatus());
		antigo.setTitulo(md.getTitulo());
		antigo.setDescricao(md.getDescricao());
		antigo.setTipo(md.getTipo());
		if(md.getLink()!=null) {
			antigo.setLink(md.getLink());
		}
		if(md.getUpload()!=null) {
			antigo.setNomeMaterial(md.getUpload().getOriginalFilename());
			antigo.setTipoArquivo(md.getUpload().getContentType());
			antigo.setArquivo(md.getUpload().getBytes());
		}
		mdDAO.alterar(antigo);
		return "redirect:editarSumario-" + md.getTopico().getSumario().getTurmaDisciplina().getId();
	}
	
	@RequestMapping("excluirMaterialDidatico")
	public String excluiMaterial(@RequestParam("material") MaterialDidatico md, HttpSession session) {
		IMaterialDidaticoDAO mdDAO = DAOFactory.getMaterialDidaticoDAO();
		mdDAO.remover(md);
		return "redirect:editarSumario-" + md.getTopico().getSumario().getTurmaDisciplina().getId();
	}
	
	@RequestMapping("adicionarItemDiscursivo")
	public String addItemDiscursivo(@ModelAttribute("item") ItemAtividade item, HttpSession session) {
		Usuario u = (Usuario) session.getAttribute("usuarioLogado");
		IItemAtividadeDAO iaDAO = DAOFactory.getItemAtividadeDAO();
		item.setUsuarioLogado(u);
		item.setStatus(StatusEntidade.ATIVO);
		iaDAO.inserir(item);
		return "redirect:editarAtividade-"+item.getAtividade().getId();
	}
	
}
