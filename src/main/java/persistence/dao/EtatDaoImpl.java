package persistence.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import persistence.entities.Etat;

public class EtatDaoImpl implements EtatDao {

	@Override
	public void add(Etat type) {
	}

	@Override
	public void delete(Etat type) {
	}

	@Override
	public void update(Etat type) {
	}

	@Override
	public List<Etat> findAll() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Etat> listEtat = session.createQuery("from Etat").list();
        session.close();
        return listEtat;
	}

	@Override
	public Etat findById(Serializable o) {
		return null;
	}

}
