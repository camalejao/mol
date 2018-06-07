package mol.dao;

import javax.persistence.EntityManager;

import mol.model.user.Monitor;

public class MonitorDAO extends DAOGenerico<Monitor> implements IMonitorDAO {

	public MonitorDAO(EntityManager em) {
		super(em);
	}

}
