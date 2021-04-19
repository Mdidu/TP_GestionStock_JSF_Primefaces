package persistence.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.entities.Categorie;

public class CategorieDaoImpl implements CategorieDao {

	Transaction tx;
	
	@Override
	public void add(Categorie categorie) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.save(categorie);
        tx.commit();
        session.close();
	}

	@Override
	public void delete(Categorie categorie) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.delete(categorie);
        tx.commit();
        session.close();
	}

	@Override
	public void update(Categorie categorie) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.update(categorie);
        tx.commit();
        session.close();
	}

	@Override
	public List<Categorie> findAll() {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
        List<Categorie> listCategorie = session.createQuery("from Categorie").list();
        session.close();
        return listCategorie;
	}

	@Override
	public Categorie findById(Serializable object) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Categorie categorie = session.get(Categorie.class,(int) object);
        session.close();
        return categorie;
	}

}
