package mol.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAOFactory {
	private static final EntityManagerFactory emf;
    private static IDisciplinaDAO disciplinaDAO;
    private static IUsuarioDAO usuarioDAO;
    private static IProfessorDAO professorDAO;
    private static IAlunoDAO alunoDAO;
    private static IMonitorDAO monitorDAO;
 
    static {
        emf = Persistence.createEntityManagerFactory("mol");
    }
    
    public static IDisciplinaDAO getDisciplinaDAO() {
    	disciplinaDAO = new DisciplinaDAO(emf.createEntityManager());
        return disciplinaDAO;
    }
    
    public static IUsuarioDAO getUsuarioDAO() {
    	usuarioDAO = new UsuarioDAO(emf.createEntityManager());
        return usuarioDAO;
    }
    
    public static IProfessorDAO getProfessorDAO() {
    	professorDAO = new ProfessorDAO(emf.createEntityManager());
        return professorDAO;
    }
    
    public static IAlunoDAO getAlunoDAO() {
    	alunoDAO = new AlunoDAO(emf.createEntityManager());
        return alunoDAO;
    }
    
    public static IMonitorDAO getMonitorDAO() {
    	monitorDAO = new MonitorDAO(emf.createEntityManager());
        return monitorDAO;
    }
    
    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
