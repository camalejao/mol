package mol.converter;

import org.springframework.core.convert.converter.Converter;

import mol.dao.DAOFactory;
import mol.dao.IUsuarioDAO;
import mol.model.user.Usuario;

public class UsuarioConverter implements Converter<String, Usuario> {
	@Override
	public Usuario convert(String source) {
		Integer id = Integer.valueOf(source);
		IUsuarioDAO uDAO = DAOFactory.getUsuarioDAO();
		Usuario u = uDAO.consultarPorId(id);
		return u;
	}
}
