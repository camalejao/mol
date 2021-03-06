package mol.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import mol.dao.*;
import mol.model.user.*;
import mol.model.*;
import mol.model.curso.*;
import mol.model.curso.atividade.Atividade;
import mol.model.curso.atividade.Resposta;
import mol.model.curso.atividade.Unidades;
import mol.model.curso.disciplina.*;
import mol.model.curso.turma.TipoCalculo;
import mol.model.curso.turma.Turma;
import mol.model.curso.turma.TurmaDisciplina;
import mol.model.curso.turma.TurmaDisciplinaAluno;
import mol.model.curso.turma.TurnoTurma;
import mol.model.materialDidatico.MaterialDidatico;
import mol.model.materialDidatico.TipoMaterialDidatico;

public class Inserts {

	public static void main(String[] args) {
		
		IDisciplinaDAO discDAO = DAOFactory.getDisciplinaDAO();
		IAlunoDAO aDAO = DAOFactory.getAlunoDAO();		
		IMonitorDAO mDAO = DAOFactory.getMonitorDAO();
		IUsuarioDAO uDAO = DAOFactory.getUsuarioDAO();
		IProfessorDAO pDAO = DAOFactory.getProfessorDAO();
		ISumarioDAO sDAO = DAOFactory.getSumarioDAO();
		ITopicoDAO topDAO = DAOFactory.getTopicoDAO();
		ITurmaDAO turmaDAO = DAOFactory.getTurmaDAO();
		ITurmaDisciplinaDAO tdDAO = DAOFactory.getTurmaDisciplinaDAO();
		ITurmaDisciplinaAlunoDAO tdaDAO = DAOFactory.getTurmaDisciplinaAlunoDAO();
		IPeriodoDAO peDAO = DAOFactory.getPeriodoDAO();
		ICursoDAO cDAO = DAOFactory.getCursoDAO();
		
		Sumario s1 = new Sumario();
		s1.setStatus(StatusEntidade.ATIVO);
		Sumario s2 = new Sumario();
		s2.setStatus(StatusEntidade.ATIVO);
		Sumario s3 = new Sumario();
		s3.setStatus(StatusEntidade.ATIVO);
		Sumario s4 = new Sumario();
		s4.setStatus(StatusEntidade.ATIVO);
		Sumario s5 = new Sumario();
		s5.setStatus(StatusEntidade.ATIVO);
		Sumario s6 = new Sumario();
		s6.setStatus(StatusEntidade.ATIVO);
		
		Topico t1d1 = new Topico();
		t1d1.setDescricao("Estruturas de decis�o");
		t1d1.setSumario(s1);
		t1d1.setStatus(StatusEntidade.ATIVO);
		Topico t2d1 = new Topico();
		t2d1.setDescricao("La�os de repeti��o");
		t2d1.setSumario(s1);
		t2d1.setStatus(StatusEntidade.ATIVO);
		Topico t1d2 = new Topico();
		t1d2.setDescricao("Lista");
		t1d2.setSumario(s2);
		t1d2.setStatus(StatusEntidade.ATIVO);
		Topico t2d2 = new Topico();
		t2d2.setDescricao("Fila");
		t2d2.setSumario(s2);
		t2d2.setStatus(StatusEntidade.ATIVO);
		Topico t3d2 = new Topico();
		t3d2.setDescricao("Pilha");
		t3d2.setSumario(s2);
		t3d2.setStatus(StatusEntidade.ATIVO);
		Topico t1d3 = new Topico();
		t1d3.setDescricao("Modelo ISO/OSI");
		t1d3.setSumario(s3);
		t1d3.setStatus(StatusEntidade.ATIVO);
		Topico t2d3 = new Topico();
		t2d3.setDescricao("Modelo TCP/IP");
		t2d3.setSumario(s3);
		t2d3.setStatus(StatusEntidade.ATIVO);
		
		Disciplina d1 = new Disciplina();
		d1.setNome("Algoritmos");
		d1.setSigla("ALGO");
		d1.setStatus(StatusEntidade.ATIVO);
		d1.setSumario(s4);
		Disciplina d2 = new Disciplina();
		d2.setNome("Estrutura de Dados");
		d2.setSigla("ESTD");
		d2.setStatus(StatusEntidade.ATIVO);
		d1.setSumario(s5);
		Disciplina d3 = new Disciplina();
		d3.setNome("Redes de Computadores");
		d3.setSigla("RECO");
		d3.setStatus(StatusEntidade.ATIVO);
		d1.setSumario(s6);
		
		Aluno a = new Aluno();
		a.setNome("Pedro Santos");
		a.setEmail("pedro@gmail.com");
		a.setSenha("mol123");
		a.setSenha(a.senhaSHA());
		a.setStatus(StatusEntidade.ATIVO);
		a.setMatricula("15126586");
		a.setTipo(TipoUsuario.ALUNO);
		
		Aluno a2 = new Aluno();
		a2.setNome("Afonso Alves");
		a2.setEmail("afonso@gmail.com");
		a2.setSenha("mol123");
		a2.setSenha(a2.senhaSHA());
		a2.setStatus(StatusEntidade.ATIVO);
		a2.setMatricula("15115520");
		a2.setTipo(TipoUsuario.ALUNO);
		
		Professor p = new Professor();
		p.setNome("Julia Silva");
		p.setEmail("julia@gmail.com");
		p.setSenha("mol123");
		p.setSenha(p.senhaSHA());
		p.setMatricula("10102020");
		p.setStatus(StatusEntidade.ATIVO);
		p.setTipo(TipoUsuario.PROFESSOR);
		
		Professor p2 = new Professor();
		p2.setNome("Jos� Ara�jo");
		p2.setEmail("jose@gmail.com");
		p2.setMatricula("11102220");
		p2.setSenha("mol123");
		p2.setSenha(p2.senhaSHA());
		p2.setStatus(StatusEntidade.ATIVO);
		p2.setTipo(TipoUsuario.PROFESSOR);
		
		Aluno a3 = new Aluno();
		a3.setNome("Jorge Souza");
		a3.setEmail("jorge@gmail.com");
		a3.setSenha("mol123");
		a3.setSenha(a3.senhaSHA());
		a3.setMatricula("15109319");
		a3.setStatus(StatusEntidade.ATIVO);
		a3.setTipo(TipoUsuario.ALUNO);
		
		Aluno a4 = new Aluno();
		a4.setNome("Jo�o Victor");
		a4.setEmail("joao.falcao@icloud.com");
		a4.setSenha("mol123");
		a4.setSenha(a4.senhaSHA());
		a4.setStatus(StatusEntidade.ATIVO);
		a4.setMatricula("15106586");
		a4.setTipo(TipoUsuario.ALUNO);
		
		Aluno a5 = new Aluno();
		a5.setNome("Alexandre Costa");
		a5.setEmail("joao.falcao@icloud.com");
		a5.setSenha("mol123");
		a5.setSenha(a5.senhaSHA());
		a5.setStatus(StatusEntidade.ATIVO);
		a5.setMatricula("15150037");
		a5.setTipo(TipoUsuario.ALUNO);
		
		Usuario u = new Usuario();
		u.setNome("Administrador");
		u.setSenha("mol123");
		u.setSenha(u.senhaSHA());
		u.setEmail("adm@mol.com");
		u.setStatus(StatusEntidade.ATIVO);
		u.setTipo(TipoUsuario.ADMINISTRADOR);
		
		Monitor m1 = new Monitor();
		m1.setAluno(a);
		m1.setDisciplina(d1);
		m1.setStatus(StatusEntidade.ATIVO);
		Monitor m2 = new Monitor();
		m2.setAluno(a2);
		m2.setDisciplina(d2);
		m2.setStatus(StatusEntidade.ATIVO);
		
		Curso c1 = new Curso();
		c1.setCargaHoraria(1500);
		c1.setNome("Inform�tica");
		c1.setModalidade(ModalidadeCurso.TECNICO);
		c1.setStatus(StatusEntidade.ATIVO);
		Curso c2 = new Curso();
		c2.setCargaHoraria(3000);
		c2.setNome("Sistemas de Informa��o");
		c2.setModalidade(ModalidadeCurso.SUPERIOR);
		c2.setStatus(StatusEntidade.ATIVO);
		Curso c3 = new Curso();
		c3.setCargaHoraria(1800);
		c3.setNome("Inform�tica para a Internet");
		c3.setModalidade(ModalidadeCurso.SUBSEQUENTE);
		c3.setStatus(StatusEntidade.ATIVO);
		
		Periodo pe1 = new Periodo();
		pe1.setAno(2018);
		pe1.setEntrada(EntradaPeriodo.PRIMEIRO_SEMESTRE);
		pe1.setStatus(StatusEntidade.ATIVO);
		Periodo pe2 = new Periodo();
		pe2.setAno(2018);
		pe2.setEntrada(EntradaPeriodo.SEGUNDO_SEMESTRE);
		pe2.setStatus(StatusEntidade.ATIVO);
		
		Turma t1 = new Turma();
		t1.setCurso(c1);
		t1.setIdentificacao("914-A");
		t1.setTurno(TurnoTurma.MATUTINO);
		t1.setPeriodo(pe1);
		t1.setStatus(StatusEntidade.ATIVO);
		
		Turma t2 = new Turma();
		t2.setCurso(c1);
		t2.setIdentificacao("921-A");
		t2.setTurno(TurnoTurma.VESPERTINO);
		t2.setPeriodo(pe1);
		t2.setStatus(StatusEntidade.ATIVO);
		
		Turma t3 = new Turma();
		t3.setCurso(c2);
		t3.setIdentificacao("BSI-3");
		t3.setTurno(TurnoTurma.NOTURNO);
		t3.setPeriodo(pe2);
		t3.setStatus(StatusEntidade.ATIVO);
		
		TurmaDisciplina td1 = new TurmaDisciplina();
		td1.setCargaHoraria(80);
		td1.setTurma(t1);
		td1.setProfessor(p);
		td1.setDisciplina(d1);
		td1.setSumarioTurma(s1);
		td1.setQuantidadeNiveis(0);
		td1.setTipoCalculo(TipoCalculo.ARITMETICA);
		td1.setStatus(StatusEntidade.ATIVO);
		
		TurmaDisciplina td2 = new TurmaDisciplina();
		td2.setCargaHoraria(80);
		td2.setTurma(t1);
		td2.setProfessor(p2);
		td2.setDisciplina(d2);
		td2.setSumarioTurma(s2);
		td2.setQuantidadeNiveis(0);
		td2.setTipoCalculo(TipoCalculo.SOMA);
		td2.setStatus(StatusEntidade.ATIVO);
		
		TurmaDisciplina td3 = new TurmaDisciplina();
		td3.setCargaHoraria(160);
		td3.setTurma(t3);
		td3.setProfessor(p2);
		td3.setDisciplina(d3);
		td3.setSumarioTurma(s3);
		td3.setQuantidadeNiveis(0);
		td3.setTipoCalculo(TipoCalculo.PONDERADA);
		td3.setStatus(StatusEntidade.ATIVO);
		
		TurmaDisciplinaAluno tda1 = new TurmaDisciplinaAluno();
		tda1.setAluno(a3);
		tda1.setTurmaDisciplina(td3);
		tda1.setNivelAtual(1);
		tda1.setStatus(StatusEntidade.ATIVO);
		TurmaDisciplinaAluno tda2 = new TurmaDisciplinaAluno();
		tda2.setAluno(a2);
		tda2.setTurmaDisciplina(td1);
		tda2.setNivelAtual(1);
		tda2.setStatus(StatusEntidade.ATIVO);
		TurmaDisciplinaAluno tda3 = new TurmaDisciplinaAluno();
		tda3.setAluno(a);
		tda3.setTurmaDisciplina(td1);
		tda3.setNivelAtual(1);
		tda3.setStatus(StatusEntidade.ATIVO);
		TurmaDisciplinaAluno tda4 = new TurmaDisciplinaAluno();
		tda4.setAluno(a4);
		tda4.setTurmaDisciplina(td3);
		tda4.setNivelAtual(1);
		tda4.setStatus(StatusEntidade.ATIVO);
		TurmaDisciplinaAluno tda5 = new TurmaDisciplinaAluno();
		tda5.setAluno(a5);
		tda5.setTurmaDisciplina(td2);
		tda5.setNivelAtual(1);
		tda5.setStatus(StatusEntidade.ATIVO);
			
		
		sDAO.inserir(s1);
		sDAO.inserir(s2);
		sDAO.inserir(s3);
		sDAO.inserir(s4);
		sDAO.inserir(s5);
		sDAO.inserir(s6);
				
		topDAO.inserir(t1d1);
		topDAO.inserir(t2d1);
		topDAO.inserir(t1d2);
		topDAO.inserir(t2d2);
		topDAO.inserir(t3d2);
		topDAO.inserir(t1d3);
		topDAO.inserir(t2d3);
				
		discDAO.inserir(d1);
		discDAO.inserir(d2);
		discDAO.inserir(d3); 
		
		aDAO.inserir(a);
		aDAO.inserir(a2);
		aDAO.inserir(a3);
		aDAO.inserir(a4);
		
		pDAO.inserir(p);
		pDAO.inserir(p2);
		
		mDAO.inserir(m1);
		a.setTipo(TipoUsuario.MONITOR);
		aDAO.alterar(a);
		mDAO.inserir(m2);
		a2.setTipo(TipoUsuario.MONITOR);
		aDAO.alterar(a2);
		
		uDAO.inserir(u);
		
		cDAO.inserir(c1);
		cDAO.inserir(c2);
		cDAO.inserir(c3);
		
		peDAO.inserir(pe1);
		peDAO.inserir(pe2);
		
		turmaDAO.inserir(t1);
		turmaDAO.inserir(t2);
		turmaDAO.inserir(t3);
		
		tdDAO.inserir(td1);
		tdDAO.inserir(td2);
		tdDAO.inserir(td3);
		
		tdaDAO.inserir(tda1);
		tdaDAO.inserir(tda2);
		tdaDAO.inserir(tda3);
		tdaDAO.inserir(tda4); 

	}

}
