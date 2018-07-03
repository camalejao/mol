package mol.dao;

import javax.persistence.EntityManager;

import mol.model.materialDidatico.MaterialDidatico;

public class MaterialDidaticoDAO extends DAOGenerico<MaterialDidatico> implements IMaterialDidaticoDAO {

	public MaterialDidaticoDAO(EntityManager em) {
		super(em);
	}

}
