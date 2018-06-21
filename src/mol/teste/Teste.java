package mol.teste;

import java.util.List;

import mol.dao.*;
import mol.model.user.*;
import mol.model.*;
import mol.model.curso.disciplina.*;

public class Teste {

	public static void main(String[] args) {
		/*
		Disciplina d1 = new Disciplina();
		d1.setNome("Algoritmos");
		d1.setSigla("ALGO");
		
		Disciplina d2 = new Disciplina();
		d2.setNome("Estrutura de Dados");
		d2.setSigla("ESTD");
		
		IDisciplinaDAO discDAO = DAOFactory.getDisciplinaDAO();
		//discDAO.inserir(d1);
		//discDAO.inserir(d2);
		
		Aluno a1 = new Aluno();
		a1.setNome("Afonso");
		a1.setEmail("afonso@ig.com");
		a1.setSenha("12345");
		a1.setSenha(a1.senhaSHA());
		a1.setTipo(TipoUsuario.MONITOR);
		a1.setStatus(StatusEntidade.ATIVO);
		a1.setMatricula("123456999");
		
		IAlunoDAO aDAO = DAOFactory.getAlunoDAO();
		//aDAO.inserir(a1);
				
		List<Aluno> a = aDAO.consultarPorNome("Afonso");
		System.out.println(a.get(0).getNome());
		
		Monitor m1 = new Monitor();
		m1.setAluno(aDAO.consultarPorId(a.get(0).getId()));
		m1.setDisciplina(discDAO.consultarPorSigla("ESTD"));
		m1.setStatus(StatusEntidade.ATIVO); */
				
		IMonitorDAO mDAO = DAOFactory.getMonitorDAO();
		//mDAO.inserir(m1);
		
		IUsuarioDAO uDAO = DAOFactory.getUsuarioDAO();
		/*
		Usuario u = uDAO.consultarPorId(1);
		u.setNome("Afonso Jorge");
		uDAO.alterar(u); */
		
		/* Usuario u = new Usuario();
		u.setEmail("adm");
		u.setNome("adm");
		u.setTipo(TipoUsuario.ADMINISTRADOR);
		u.setStatus(StatusEntidade.ATIVO);
		u.setSenha("123");
		u.setSenha(u.senhaSHA());
		uDAO.inserir(u); */
		IDisciplinaDAO discDAO = DAOFactory.getDisciplinaDAO();
		IAlunoDAO aDAO = DAOFactory.getAlunoDAO();
		
		Monitor m = new Monitor();
		m.setAluno(aDAO.consultarPorId(1));
		m.setDisciplina(discDAO.consultarPorSigla("ESTD"));
		m.setStatus(StatusEntidade.ATIVO);
		m.getAluno().setTipo(TipoUsuario.MONITOR);
		mDAO.inserir(m);
		
	}

}
