package mol.teste;

import java.util.ArrayList;
import java.util.List;

import mol.dao.DAOFactory;
import mol.dao.IPermissoesDAO;
import mol.model.user.TipoUsuario;
import mol.interceptor.Permissoes;

public class AdicionaPermissoes {
	
	public static void main(String[] args) {
		IPermissoesDAO pDAO = DAOFactory.getPermissoesDAO();
		List<Permissoes> lp = new ArrayList<>();
		lp.add(new Permissoes("homeAdm", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("listarUsuarios", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("listarDisciplinas", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("cadastrarUsuario", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("cadastrarDisciplina", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("selecionarTipoUsuario", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("insereDisciplina", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("insereAluno", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("cadastrarAdm", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("insereAdm", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("cadastrarProfessor", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("insereProfessor", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("cadastrarMonitor", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("insereMonitor", TipoUsuario.ADMINISTRADOR));
		lp.add(new Permissoes("cadastrarDisciplina", TipoUsuario.ADMINISTRADOR));
		
		for(Permissoes p : lp) {
			pDAO.inserir(p);
		}
	}
}
