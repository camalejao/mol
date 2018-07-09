package mol.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mol.model.user.Usuario;

public class UsuarioDAO extends DAOGenerico<Usuario> implements IUsuarioDAO {

	public UsuarioDAO(EntityManager em) {
		super(em);
	}

	@Override
	public Usuario consultarPorEmail(String email) {
		try {
			TypedQuery<Usuario> query = getEntityManager().createQuery("select u from Usuario u where u.email = :email",
					Usuario.class);
			query.setParameter("email", email);
			return query.getSingleResult();

		} catch (RuntimeException re) {
			re.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Usuario> consultarPorNome(String nome) {
		try {
			TypedQuery<Usuario> query = getEntityManager()
					.createQuery("select u from Usuario u where u.nome like :nome", Usuario.class);
			query.setParameter("nome", "%" + nome + "%");
			return query.getResultList();

		} catch (RuntimeException re) {
			re.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean verificarPorEmail(String email) {
		Boolean b;
		if (consultarPorEmail(email) != null) {
			b = false;
			return b;
		} else {
			b = true;
			return b;
		}
	}

}
