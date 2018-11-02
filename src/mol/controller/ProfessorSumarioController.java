package mol.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mol.dao.DAOFactory;
import mol.dao.IMaterialDidaticoDAO;
import mol.dao.ITopicoDAO;
import mol.dao.ITurmaDisciplinaDAO;
import mol.model.StatusEntidade;
import mol.model.curso.disciplina.Topico;
import mol.model.curso.turma.TurmaDisciplina;
import mol.model.materialDidatico.MaterialDidatico;
import mol.model.materialDidatico.TipoMaterialDidatico;
import mol.model.user.Professor;
import mol.model.user.Usuario;

@Controller
public class ProfessorSumarioController {
	
	@RequestMapping("editarSumario-{id}")
	public ModelAndView sumario(@PathVariable Integer id, HttpSession session) {
		
		ModelAndView mav;
		ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
		ITopicoDAO tDAO = DAOFactory.getTopicoDAO();
		TurmaDisciplina turmaDisc = tdDAO.consultarPorId(id);
		Professor p = (Professor) session.getAttribute("usuarioLogado");
		
		if(turmaDisc != null && turmaDisc.getProfessor().getId() == p.getId()) {
			mav = new ModelAndView("professor/sumario");
			List<Topico> topicos = tDAO.consultarPorTurma(turmaDisc);
			mav.addObject("turmaDisc", turmaDisc);
			mav.addObject("topicos", topicos);
			mav.addObject("novoTopico", new Topico());
			mav.addObject("novoMaterialDidatico", new MaterialDidatico());
			mav.addObject("status", Arrays.asList(StatusEntidade.values()));
			mav.addObject("tiposMaterial", Arrays.asList(TipoMaterialDidatico.values()));

			return mav;
		}
		mav = new ModelAndView("redirect:listarTurmas");
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
}
