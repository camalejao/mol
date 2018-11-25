package mol.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import mol.dao.DAOFactory;
import mol.dao.IAtividadeDAO;
import mol.dao.IItemAtividadeDAO;
import mol.dao.INivelAprendizagemDAO;
import mol.dao.IRespostaDAO;
import mol.dao.ITurmaDisciplinaAlunoDAO;
import mol.dao.ITurmaDisciplinaDAO;
import mol.model.StatusEntidade;
import mol.model.curso.atividade.Alternativa;
import mol.model.curso.atividade.Atividade;
import mol.model.curso.atividade.ItemAtividade;
import mol.model.curso.atividade.Resposta;
import mol.model.curso.atividade.StatusAtividade;
import mol.model.curso.atividade.TipoItem;
import mol.model.curso.atividade.TipoSubmissao;
import mol.model.curso.atividade.Unidades;
import mol.model.curso.atividade.analiseDesempenho.Data;
import mol.model.curso.atividade.analiseDesempenho.Dataset;
import mol.model.curso.atividade.analiseDesempenho.Grafico;
import mol.model.curso.atividade.analiseDesempenho.Options;
import mol.model.curso.atividade.analiseDesempenho.Scales;
import mol.model.curso.atividade.analiseDesempenho.Ticks;
import mol.model.curso.atividade.analiseDesempenho.XAxes;
import mol.model.curso.atividade.analiseDesempenho.YAxes;
import mol.model.curso.turma.TurmaDisciplina;
import mol.model.curso.turma.TurmaDisciplinaAluno;
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
	
	@RequestMapping("graficoAtividade-{id}")
	public ModelAndView geraGrafico(@PathVariable Integer id, HttpSession session) {
		
		if(id != null) {
			IAtividadeDAO atvDAO = DAOFactory.getAtividadeDAO();
			Atividade atv = atvDAO.consultarPorId(id);
			Professor prof = (Professor) session.getAttribute("usuarioLogado");
			
			if(atv.getTurmaDisciplina().getProfessor().getId() == prof.getId()) {
				ModelAndView mav = new ModelAndView("professor/grafico");
				mav.addObject("atividade", atv);
				return mav;
			} else
				return new ModelAndView("redirect:listarTurmas");
		}
		return new ModelAndView("redirect:listarTurmas");
	}
	
	@RequestMapping("getDadosSubmissoes")
	@ResponseBody
	public String getDados(Integer idAtv) {
		
		Grafico grafico = new Grafico();
		Data data = new Data();
		Dataset dataset = new Dataset();
		Options options = new Options();
		Scales scales = new Scales();
		YAxes yAxes = new YAxes();
		XAxes xAxes = new XAxes();
		Ticks ticks = new Ticks();
		
		IAtividadeDAO atvDAO = DAOFactory.getAtividadeDAO();
		IRespostaDAO respDAO = DAOFactory.getRespostaDAO();
		ITurmaDisciplinaAlunoDAO tdaDAO = DAOFactory.getTurmaDisciplinaAlunoDAO();
		Atividade atividade = atvDAO.consultarPorId(idAtv);
		List<Resposta> listaTodasRespostas = respDAO.consultarPorAtividade(atividade);
		List<Resposta> listaRespostasCorrigidas = respDAO.consultarCorrigidasPorAtividade(atividade);
		List<TurmaDisciplinaAluno> listaAlunos = tdaDAO.consultarPorTurmaDisciplina(atividade.getTurmaDisciplina());
		
		if(listaRespostasCorrigidas!=null) {
			double[] dados = {listaAlunos.size(), listaTodasRespostas.size(), listaRespostasCorrigidas.size()};
			dataset.setData(dados);
		} 
		else if(listaTodasRespostas!=null && listaRespostasCorrigidas==null) {
			double[] dados = {listaAlunos.size(), listaTodasRespostas.size(), 0};
			dataset.setData(dados);
		}
		else if(listaAlunos!=null && listaTodasRespostas==null && listaRespostasCorrigidas==null){
			double[] dados = {listaAlunos.size(), 0, 0};
			dataset.setData(dados);
		}
		else {
			double[] dados = {0, 0, 0};
			dataset.setData(dados);
		}
		
		dataset.setBackgroundColor("rgba(2,117,216,1)");
		dataset.setBorderColor("rgba(2,117,216,1)");
		dataset.setLabel(atividade.getTitulo());
		String[] labels = {"Número de Alunos", "Respostas Submetidas", "Respostas Analisadas"};
		
		ticks.setBeginAtZero(true);
		ticks.setMax(listaAlunos.size());
		ticks.setMaxTicksLimit(5);
		yAxes.setTicks(ticks);
		xAxes.setCategoryPercentage(0.5);
		xAxes.setBarPercentage(0.8);
		xAxes.setMaxBarThickness(120);
		scales.getyAxes().add(yAxes);
		scales.getxAxes().add(xAxes);
		options.setScales(scales);
		
		data.getDatasets().add(dataset);
		data.setLabels(labels);
		grafico.setOptions(options);
		grafico.setData(data);
		
		Gson gson = new Gson();
		String json = gson.toJson(grafico);
		
		System.out.println(json);
		return json;
	}
	
	@RequestMapping("getDadosNotas")
	@ResponseBody
	public String getDadosNotas(Integer idAtv) {
		
		Grafico grafico = new Grafico();
		Data data = new Data();
		Dataset dataset = new Dataset();
		Options options = new Options();
		Scales scales = new Scales();
		YAxes yAxes = new YAxes();
		XAxes xAxes = new XAxes();
		Ticks ticks = new Ticks();
		
		IAtividadeDAO atvDAO = DAOFactory.getAtividadeDAO();
		IRespostaDAO respDAO = DAOFactory.getRespostaDAO();
		Atividade atividade = atvDAO.consultarPorId(idAtv);
		List<Resposta> listaRespostasCorrigidas = respDAO.consultarCorrigidasPorAtividade(atividade);
		double media = 0.0;
		
		if(listaRespostasCorrigidas.size() > 0) {
			for(Resposta r : listaRespostasCorrigidas) {
				media += r.getNota();
			}
			media = media/listaRespostasCorrigidas.size();
			double[] dados = {atividade.getValorMaximo(), media};
			dataset.setData(dados);
		}
		else {
			double[] dados = {atividade.getValorMaximo(), 0};
			dataset.setData(dados);
		}
		
		dataset.setBackgroundColor("rgba(2,117,216,1)");
		dataset.setBorderColor("rgba(2,117,216,1)");
		dataset.setLabel(atividade.getTitulo());
		String[] labels = {"Valor da Atividade", "Média da Turma"};
		
		ticks.setBeginAtZero(true);
		ticks.setMax(atividade.getValorMaximo());
		ticks.setMaxTicksLimit(5);
		yAxes.setTicks(ticks);
		xAxes.setCategoryPercentage(0.5);
		xAxes.setBarPercentage(0.8);
		xAxes.setMaxBarThickness(120);
		scales.getyAxes().add(yAxes);
		scales.getxAxes().add(xAxes);
		options.setScales(scales);
		
		data.getDatasets().add(dataset);
		data.setLabels(labels);
		grafico.setOptions(options);
		grafico.setData(data);
		
		Gson gson = new Gson();
		String json = gson.toJson(grafico);
		
		System.out.println(json);
		return json;
	}
}
