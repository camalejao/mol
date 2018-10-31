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
    private static ICursoDAO cursoDAO;
    private static IPeriodoDAO periodoDAO;
    private static ISumarioDAO sumarioDAO;
    private static ITopicoDAO topicoDAO;
    private static IAtividadeDAO atividadeDAO;
    private static IRespostaDAO respostaDAO;
    private static ITurmaDAO turmaDAO;
    private static ITurmaDisciplinaDAO turmaDisciplinaDAO;
    private static ITurmaDisciplinaAlunoDAO turmaDisciplinaAlunoDAO;
    private static IMaterialDidaticoDAO materialDidaticoDAO;
    private static IPermissoesDAO permissoesDAO;
    private static IItemAtividadeDAO itemAtividadeDAO;
    private static IAlternativaDAO alternativaDAO;
    private static IItemRespostaDAO itemRespostaDAO;
    private static INivelAprendizagemDAO nivelAprendizagemDAO;
    private static IDesempenhoAlunoDAO desempenhoAlunoDAO;
    private static IDuvidaDAO duvidaDAO;
    private static IRespostaDuvidaDAO respostaDuvidaDAO;
    
    static {
        emf = Persistence.createEntityManagerFactory("mol_local");
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
    
    public static ICursoDAO getCursoDAO() {
		cursoDAO = new CursoDAO(emf.createEntityManager());
    	return cursoDAO;
	}

	public static IPeriodoDAO getPeriodoDAO() {
		periodoDAO = new PeriodoDAO(emf.createEntityManager());
		return periodoDAO;
	}


	public static ISumarioDAO getSumarioDAO() {
		sumarioDAO = new SumarioDAO(emf.createEntityManager());
		return sumarioDAO;
	}


	public static ITopicoDAO getTopicoDAO() {
		topicoDAO = new TopicoDAO(emf.createEntityManager());
		return topicoDAO;
	}

	public static IAtividadeDAO getAtividadeDAO() {
		atividadeDAO = new AtividadeDAO(emf.createEntityManager());
		return atividadeDAO;
	}
	
	public static IRespostaDAO getRespostaDAO() {
		respostaDAO = new RespostaDAO(emf.createEntityManager());
		return respostaDAO;
	}
	
	public static ITurmaDAO getTurmaDAO() {
		turmaDAO = new TurmaDAO(emf.createEntityManager());
		return turmaDAO;
	}

	public static ITurmaDisciplinaDAO getTurmaDisciplinaDAO() {
		turmaDisciplinaDAO = new TurmaDisciplinaDAO(emf.createEntityManager());
		return turmaDisciplinaDAO;
	}


	public static ITurmaDisciplinaAlunoDAO getTurmaDisciplinaAlunoDAO() {
		turmaDisciplinaAlunoDAO = new TurmaDisciplinaAlunoDAO(emf.createEntityManager());
		return turmaDisciplinaAlunoDAO;
	}

	public static IMaterialDidaticoDAO getMaterialDidaticoDAO() {
		materialDidaticoDAO = new MaterialDidaticoDAO(emf.createEntityManager());
		return materialDidaticoDAO;
	}

	public static IPermissoesDAO getPermissoesDAO() {
		permissoesDAO = new PermissoesDAO(emf.createEntityManager());
		return permissoesDAO;
	}
	
	public static IItemAtividadeDAO getItemAtividadeDAO() {
		itemAtividadeDAO = new ItemAtividadeDAO(emf.createEntityManager());
		return itemAtividadeDAO;
	}
	
	public static IAlternativaDAO getAlternativaDAO() {
		alternativaDAO = new AlternativaDAO(emf.createEntityManager());
		return alternativaDAO;
	}
	
	public static IItemRespostaDAO getItemRespostaDAO() {
		itemRespostaDAO = new ItemRespostaDAO(emf.createEntityManager());
		return itemRespostaDAO;
	}

	public static INivelAprendizagemDAO getNivelAprendizagemDAO() {
		nivelAprendizagemDAO = new NivelAprendizagemDAO(emf.createEntityManager());
		return nivelAprendizagemDAO;
	}
	
	public static IDesempenhoAlunoDAO getDesempenhoAlunoDAO() {
		desempenhoAlunoDAO = new DesempenhoAlunoDAO(emf.createEntityManager());
		return desempenhoAlunoDAO;
	}
	
	public static IDuvidaDAO getDuvidaDAO() {
		duvidaDAO = new DuvidaDAO(emf.createEntityManager());
		return duvidaDAO;
	}
	
	public static IRespostaDuvidaDAO getRespostaDuvidaDAO() {
		respostaDuvidaDAO = new RespostaDuvidaDAO(emf.createEntityManager());
		return respostaDuvidaDAO;
	}

	public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

}
