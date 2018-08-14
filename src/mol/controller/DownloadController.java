package mol.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mol.dao.DAOFactory;
import mol.dao.IAtividadeDAO;
import mol.dao.IRespostaDAO;
import mol.model.curso.atividade.Atividade;
import mol.model.curso.atividade.Resposta;
import mol.model.materialDidatico.MaterialDidatico;

@Controller
public class DownloadController {
	
	@RequestMapping("downloadMd")
	public void downloadMaterialDidatico(@RequestParam("md") MaterialDidatico md, HttpServletResponse resp) throws IOException {
		System.out.println(md.getId());
		resp.setContentType(md.getTipoArquivo());
		resp.setContentLength(md.getArquivo().length);
		resp.setHeader("Content-Disposition",
				"attachment; filename=\"" + md.getNomeMaterial() + "\"");
		FileCopyUtils.copy(md.getArquivo(), resp.getOutputStream());
	}
	
	//download resposta
	@RequestMapping(value = { "downloadResposta-{id}" }, method = RequestMethod.GET)
	public void downloadResp(@PathVariable Integer id, HttpServletResponse resp) throws IOException {

		if (id != null && id > 0) {
			IRespostaDAO rDAO = DAOFactory.getRespostaDAO();
			Resposta resposta = rDAO.consultarPorId(id);
			resp.setContentType(resposta.getTipoDocumentoResposta());
			resp.setContentLength(resposta.getDocumentoResposta().length);
			resp.setHeader("Content-Disposition",
					"attachment; filename=\"" + resposta.getNomeDocumentoResposta() + "\"");
			FileCopyUtils.copy(resposta.getDocumentoResposta(), resp.getOutputStream());
		}
	}
	
	//download atividade
	@RequestMapping(value = { "downloadDocumento-{id}" }, method = RequestMethod.GET)
	public void downloadDocument(@PathVariable Integer id, HttpServletResponse resp) throws IOException {

		if (id != null && id > 0) {
			IAtividadeDAO aDAO = DAOFactory.getAtividadeDAO();
			Atividade atividade = aDAO.consultarPorId(id);
			resp.setContentType(atividade.getTipoDocumento());
			resp.setContentLength(atividade.getDocumento().length);
			resp.setHeader("Content-Disposition", "attachment; filename=\"" + atividade.getNomeDocumento() + "\"");
			FileCopyUtils.copy(atividade.getDocumento(), resp.getOutputStream());
		}
	}
	
}
