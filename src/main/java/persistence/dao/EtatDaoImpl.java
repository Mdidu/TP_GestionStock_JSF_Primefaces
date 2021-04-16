package persistence.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import persistence.entities.Etat;
import persistence.entities.Role;

public class EtatDaoImpl implements EtatDao {

	@Override
	public void add(Etat type) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Etat type) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Etat type) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Etat> findAll() {
		List<Etat> listEtat = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            listEtat = session.createQuery("from Etat").list();
        }
        return listEtat;
	}

	@Override
	public Etat findById(Serializable o) {
		// TODO Auto-generated method stub
		return null;
	}

}
