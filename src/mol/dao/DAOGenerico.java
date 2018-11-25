package mol.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public abstract class DAOGenerico<Entidade> implements IDAOGenerico<Entidade> {
	
	protected EntityManager entityManager;
    protected Class<Entidade> classePersistente;
 
    @SuppressWarnings("unchecked")
    public DAOGenerico(EntityManager em){
        this.entityManager = em;
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();  
        classePersistente = (Class<Entidade>) parameterizedType.getActualTypeArguments()[0];  
    }
    
    public final void alterar(Entidade obj) {
        EntityTransaction tx = getEntityManager().getTransaction();
        try {
            tx.begin();
            obj = getEntityManager().merge(obj);
            tx.commit();
             
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null && tx.isActive()){
                tx.rollback();
            }
        }
    }

    public final void inserir(Entidade obj) {
        EntityTransaction tx = getEntityManager().getTransaction();     
        try {
            tx.begin();
            getEntityManager().persist(obj);
            tx.commit();
            System.out.println(classePersistente.getSimpleName() + " salvo com sucesso");
            
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null && tx.isActive()){
                tx.rollback();
            }
        }
    }

    public final void inserirColecao(Collection<Entidade> colecao) {
        EntityTransaction tx = getEntityManager().getTransaction();
        try {
            tx.begin();
            for (Entidade entidade : colecao) {
                getEntityManager().persist(entidade);   
            }
            tx.commit();
            System.out.println(classePersistente.getSimpleName() + " salvos com sucesso: " + colecao.size());
            
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null && tx.isActive()){
                tx.rollback();
            }
        }
    }
 
    public final void remover(Entidade obj) {
        EntityTransaction tx = getEntityManager().getTransaction();
        try {
            tx.begin();
            obj = getEntityManager().merge(obj);
            getEntityManager().remove(obj);
            tx.commit();
            System.out.println(classePersistente.getSimpleName() + " removido com sucesso");
            
        } catch (Exception e){
            e.printStackTrace();
            if (tx != null && tx.isActive()){
                tx.rollback();
            }
        }
    }
 
    public final Entidade consultarPorId(Integer id) {
        Entidade instance = null;
        try {
            instance = (Entidade) getEntityManager().find(classePersistente, id);
 
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
        return instance;
    }
 
    public List<Entidade> consultarTodos() {
        try {
            String sql = "from " + classePersistente.getSimpleName() + " order by id";
            TypedQuery<Entidade> query = getEntityManager().createQuery(sql, classePersistente);
            return query.getResultList();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
    public List<Entidade> consultarTodos(Integer indiceInicial, Integer quantidade) {
        try {
            String sql = "from " + classePersistente.getSimpleName();
            TypedQuery<Entidade> query = getEntityManager().createQuery(sql, classePersistente);
            query = query.setFirstResult(indiceInicial).setMaxResults(quantidade);
            return query.getResultList();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }    
    
    public void setEntityManager(EntityManager em) {
        this.entityManager = em;
    }
 
    public EntityManager getEntityManager() {
        return entityManager;
    }
 
}
