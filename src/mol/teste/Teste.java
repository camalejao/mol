package mol.teste;

import java.time.LocalDate;
import java.util.List;

import mol.dao.*;
import mol.model.user.*;
import mol.model.*;
import mol.model.curso.disciplina.*;

public class Teste {

	public static void main(String[] args) {
		
		Disciplina d1 = new Disciplina();
		d1.setNome("Algoritmos");
		d1.setSigla("ALGO");
		
		Disciplina d2 = new Disciplina();
		d2.setNome("Estrutura de Dados");
		d2.setSigla("ESTD");
		
		IDisciplinaDAO discDAO = DAOFactory.getDisciplinaDAO();
		discDAO.inserir(d1);
		discDAO.inserir(d2);
		
		Aluno a1 = new Aluno();
		a1.setNome("Pedro");
		a1.setEmail("pedro@ig.com");
		a1.setSenha("12345");
		a1.setSenha(a1.senhaSHA());
		a1.setTipo(TipoUsuario.MONITOR);
		a1.setStatus(StatusEntidade.ATIVO);
		a1.setMatricula("123456789");
		
		IAlunoDAO aDAO = DAOFactory.getAlunoDAO();
		aDAO.inserir(a1);
				
		List<Aluno> a = aDAO.consultarPorNome("Pedro");
		System.out.println(a.get(0).getNome());
		
		Monitor m1 = new Monitor();
		m1.setAluno(aDAO.consultarPorId(a.get(0).getId()));
		m1.setDisciplina(discDAO.consultarPorSigla("ALGO"));
		m1.setStatus(StatusEntidade.ATIVO);
		m1.setDataUltimaAlteracao(LocalDate.now());
		
		IMonitorDAO mDAO = DAOFactory.getMonitorDAO();
		mDAO.inserir(m1);
		
		
	}

}
