package mol.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mol.dao.DAOFactory;
import mol.dao.IAlternativaDAO;
import mol.dao.IAtividadeDAO;
import mol.dao.IItemAtividadeDAO;
import mol.model.StatusEntidade;
import mol.model.curso.atividade.Alternativa;
import mol.model.curso.atividade.Atividade;
import mol.model.curso.atividade.ItemAtividade;
import mol.model.curso.atividade.TipoItem;
import mol.model.user.Usuario;

@Controller
public class ProfessorItensController {
	
	@RequestMapping("adicionarItemDiscursivo")
	public String addItemDiscursivo(@ModelAttribute("item") ItemAtividade item, HttpSession session) {
		Usuario u = (Usuario) session.getAttribute("usuarioLogado");
		IItemAtividadeDAO iaDAO = DAOFactory.getItemAtividadeDAO();
		IAtividadeDAO atvDAO = DAOFactory.getAtividadeDAO();
		item.setUsuarioLogado(u);
		item.setTipoItem(TipoItem.DISCURSIVO);
		item.setStatus(StatusEntidade.ATIVO);
		iaDAO.inserir(item);
		Atividade atv = item.getAtividade();
		atv.setValorMaximo(0);
		for(ItemAtividade i : iaDAO.consultarPorAtividade(atv)) {
			atv.setValorMaximo(atv.getValorMaximo()+i.getValor());
		}
		atvDAO.alterar(atv);
		return "redirect:editarAtividade-"+item.getAtividade().getId();
	}
	
	@RequestMapping("adicionarItemMultiplaEscolha")
	public String addItemME(@ModelAttribute("item") ItemAtividade item, HttpSession session) {
		Usuario u = (Usuario) session.getAttribute("usuarioLogado");
		IItemAtividadeDAO iaDAO = DAOFactory.getItemAtividadeDAO();
		IAtividadeDAO atvDAO = DAOFactory.getAtividadeDAO();
		item.setUsuarioLogado(u);
		item.setTipoItem(TipoItem.MULTIPLA_ESCOLHA);
		item.setStatus(StatusEntidade.ATIVO);
		for(Alternativa a : item.getAlternativas()) {
			a.setStatus(StatusEntidade.ATIVO);
			a.setUsuarioLogado(u);
			a.setItem(item);
		}
		iaDAO.inserir(item);
		Atividade atv = item.getAtividade();
		atv.setValorMaximo(0);
		for(ItemAtividade i : iaDAO.consultarPorAtividade(atv)) {
			atv.setValorMaximo(atv.getValorMaximo()+i.getValor());
		}
		atvDAO.alterar(atv);
		return "redirect:editarAtividade-"+item.getAtividade().getId();
	}
	
	@RequestMapping("editarItem")
	public String editaItemDiscursivo(@ModelAttribute("item") ItemAtividade item, HttpSession session) {
		IItemAtividadeDAO iaDAO = DAOFactory.getItemAtividadeDAO();
		IAtividadeDAO atvDAO = DAOFactory.getAtividadeDAO();
		ItemAtividade itemAntigo = iaDAO.consultarPorId(item.getId());
		itemAntigo.setEnunciado(item.getEnunciado());
		itemAntigo.setValor(item.getValor());
		iaDAO.alterar(itemAntigo);
		Atividade atv = item.getAtividade();
		atv.setValorMaximo(0);
		for(ItemAtividade i : iaDAO.consultarPorAtividade(atv)) {
			atv.setValorMaximo(atv.getValorMaximo()+i.getValor());
		}
		atvDAO.alterar(atv);
		return "redirect:editarAtividade-"+item.getAtividade().getId();
	}
	
	@RequestMapping("editarItemME")
	public String editaItemME(@ModelAttribute("item") ItemAtividade item, HttpSession session) {
		IItemAtividadeDAO iaDAO = DAOFactory.getItemAtividadeDAO();
		IAtividadeDAO atvDAO = DAOFactory.getAtividadeDAO();
		ItemAtividade antigo = iaDAO.consultarPorId(item.getId());
		antigo.setEnunciado(item.getEnunciado());
		antigo.setValor(item.getValor());
		iaDAO.alterar(antigo);
		Atividade atv = item.getAtividade();
		atv.setValorMaximo(0);
		for(ItemAtividade i : iaDAO.consultarPorAtividade(atv)) {
			atv.setValorMaximo(atv.getValorMaximo()+i.getValor());
		}
		atvDAO.alterar(atv);
		return "redirect:editarAtividade-"+item.getAtividade().getId();
	}
	
	@RequestMapping("excluirItem")
	public String excluiItem(@RequestParam("item") ItemAtividade item) {
		IItemAtividadeDAO iaDAO = DAOFactory.getItemAtividadeDAO();
		IAtividadeDAO atvDAO = DAOFactory.getAtividadeDAO();
		iaDAO.remover(item);
		Atividade atv = item.getAtividade();
		atv.setValorMaximo(0);
		for(ItemAtividade i : iaDAO.consultarPorAtividade(atv)) {
			atv.setValorMaximo(atv.getValorMaximo()+i.getValor());
		}
		atvDAO.alterar(atv);
		return "redirect:editarAtividade-"+item.getAtividade().getId();
	}
	
	@RequestMapping("editarAlternativa")
	public String editaAlternativa(@ModelAttribute("alternativa") Alternativa alt, HttpSession session) {
		IAlternativaDAO aDAO = DAOFactory.getAlternativaDAO();
		Alternativa antiga = aDAO.consultarPorId(alt.getId());
		antiga.setCorreta(alt.getCorreta());
		antiga.setEnunciado(alt.getEnunciado());
		aDAO.alterar(antiga);
		return "redirect:editarAtividade-"+alt.getItem().getAtividade().getId();
	}
}
