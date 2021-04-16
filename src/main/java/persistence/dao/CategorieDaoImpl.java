package persistence.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.entities.Categorie;

public class CategorieDaoImpl implements CategorieDao {

	@Override
	public void add(Categorie categorie) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(categorie);
            tx.commit();
        }
	}

	@Override
	public void delete(Categorie categorie) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.delete(categorie);
            tx.commit();
        }
	}

	@Override
	public void update(Categorie categorie) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(categorie);
            tx.commit();
        }
	}

	@Override
	public List<Categorie> findAll() {
		List<Categorie> listCategorie = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            listCategorie = session.createQuery("from Categorie").list();
        }
        return listCategorie;
	}

	@Override
	public Categorie findById(Serializable object) {
		Categorie categorie = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            categorie = session.get(Categorie.class,(int) object);
        }
        return categorie;
	}

}
