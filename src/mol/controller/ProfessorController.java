package mol.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.util.FileCopyUtils;

import mol.dao.DAOFactory;
import mol.dao.IAtividadeDAO;
import mol.dao.ITurmaDisciplinaDAO;
import mol.model.StatusEntidade;
import mol.model.curso.turma.Atividade;
import mol.model.curso.turma.NivelAprendizagem;
import mol.model.curso.turma.Unidades;
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

		return mav;
	}

	@RequestMapping("cadastraAtividade")
	public String salvaAtividade(Atividade atividade, HttpSession session) {
		Usuario u = (Usuario) session.getAttribute("usuarioLogado");
		// Professor p = (Professor)session.getAttribute("usuarioLogado");

		IAtividadeDAO aDAO = DAOFactory.getAtividadeDAO();
		// ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();

		atividade.setUsuarioLogado(u);
		atividade.setStatus(StatusEntidade.ATIVO);
		atividade.setDocumento(atividade.getUpload().getBytes());
		atividade.setTipoDocumento(atividade.getUpload().getContentType());
		atividade.setNomeDocumento(atividade.getUpload().getOriginalFilename());

		aDAO.inserir(atividade);

		return "professor/gerenciarAtividades";
	}

	@RequestMapping(value = { "downloadDocumento-{id}" }, method = RequestMethod.GET)
	public String downloadDocument(@PathVariable Integer id, HttpServletResponse resp) throws IOException {

		if (id != null && id > 0) {
			IAtividadeDAO aDAO = DAOFactory.getAtividadeDAO();
			Atividade atividade = aDAO.consultarPorId(id);
			resp.setContentType(atividade.getTipoDocumento());
			resp.setContentLength(atividade.getDocumento().length);
			resp.setHeader("Content-Disposition", "attachment; filename=\"" + atividade.getNomeDocumento() + "\"");
			FileCopyUtils.copy(atividade.getDocumento(), resp.getOutputStream());
		}

		return "professor/gerenciarAtividades";
	}

	@RequestMapping("editarAtividade-{id}")
	public ModelAndView editarAtividade(@PathVariable Integer id) {
		ModelAndView mav;
		if (id != null && id > 0) {
			IAtividadeDAO aDAO = DAOFactory.getAtividadeDAO();
			mav = new ModelAndView("professor/editarAtividade");
			mav.addObject("atividade", aDAO.consultarPorId(id));
			return mav;
		}
		mav = new ModelAndView("professor/gerenciarAtividades");
		return mav;
	}

	@RequestMapping("editaAtividade-{id}")
	public ModelAndView editaAtividade(@PathVariable Integer id, Atividade atividade) {
		ModelAndView mav = new ModelAndView("professor/gerenciarAtividades");
		if (id != null && id > 0) {
			IAtividadeDAO aDAO = DAOFactory.getAtividadeDAO();
			ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
			Atividade antiga = aDAO.consultarPorId(id);
			antiga.setTitulo(atividade.getTitulo());
			antiga.setDescricao(atividade.getDescricao());
			antiga.setValorMaximo(atividade.getValorMaximo());
			antiga.setPeso(atividade.getPeso());
			antiga.setUnidade(atividade.getUnidade());
			antiga.setNivel(atividade.getNivel());
			antiga.setTurmaDisciplina(tdDAO.consultarPorId(atividade.getTurmaDisciplina().getId()));
			
			aDAO.alterar(antiga);
		}
		return mav;
	}

}
