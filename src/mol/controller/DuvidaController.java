package mol.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mol.dao.DAOFactory;
import mol.dao.IRespostaDuvidaDAO;
import mol.model.StatusEntidade;
import mol.model.curso.atividade.duvida.Duvida;
import mol.model.curso.atividade.duvida.RespostaDuvida;
import mol.model.user.Usuario;

@Controller
public class DuvidaController {
	
	@RequestMapping("responderDuvida")
	@ResponseBody
	public String responderDuvida(String resposta, Duvida duvida, HttpSession session) {
		
		if(resposta.length()<=400) {
			IRespostaDuvidaDAO rdDAO = DAOFactory.getRespostaDuvidaDAO();
			Usuario autor = (Usuario) session.getAttribute("usuarioLogado");
			RespostaDuvida r = new RespostaDuvida();
			r.setResposta(resposta);
			r.setDuvida(duvida);
			r.setAutor(autor);
			r.setUsuarioLogado(autor);
			r.setStatus(StatusEntidade.ATIVO);
			rdDAO.inserir(r);
			return "sucesso";
		}
		else if(resposta.length() > 400)
			return "A resposta deve ter no máximo 400 caracteres";
		else
			return "Ocorreu um erro inesperado";
	}
	
	// <script src="resources/scripts/responderDuvida.js"></script> 
}
