package mol.converter;

import org.springframework.core.convert.converter.Converter;

import mol.dao.DAOFactory;
import mol.dao.IMaterialDidaticoDAO;
import mol.model.materialDidatico.MaterialDidatico;

public class MaterialDidaticoConverter implements Converter<String, MaterialDidatico> {
	
	@Override
	public MaterialDidatico convert(String source) {
		System.out.println(source);
		Integer id = Integer.valueOf(source);
		IMaterialDidaticoDAO mdDAO = DAOFactory.getMaterialDidaticoDAO();
		MaterialDidatico md = mdDAO.consultarPorId(id);
		System.out.println(md.getTitulo());
		return md;
	}
}
