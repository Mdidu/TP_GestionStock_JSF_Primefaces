package persistence.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import persistence.entities.Produit;

public class ProduitDaoImpl implements ProduitDao{
	
	Transaction tx;
	
	@Override
    public void add(Produit produit) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.save(produit);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(Produit produit) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.delete(produit);
        tx.commit();
        session.close();
    }

    @Override
    public void update(Produit produit) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.update(produit);
        tx.commit();
        session.close();
    }

    @Override
    public Produit findById(Serializable id) {

    	Session session = HibernateUtil.getSessionFactory().openSession();
        Produit produit = session.get(Produit.class,(int) id);
        session.close();
        return produit;
    }

    @Override
    public List<Produit> findAll() {

    	Session session = HibernateUtil.getSessionFactory().openSession();
        List<Produit> listProduits = session.createQuery("from Produit").list();
        session.close();
        
        return listProduits;
    }

    @Override
    public List<Produit> findByDesignation(String designation) {

    	Session session = HibernateUtil.getSessionFactory().openSession();
            
        Criteria criteria = session.createCriteria(Produit.class);
        Criterion criterion = Restrictions.ilike("designproduit", designation + "%");
        criteria.add(criterion);
      
        List<Produit> listProduits = criteria.list();
        session.close();
            
        return listProduits;
    }

    @Override
    public List<Produit> findByMarque(String marque) {

    	Session session = HibernateUtil.getSessionFactory().openSession();
            
        Criteria criteria = session.createCriteria(Produit.class);
        Criterion criterion = Restrictions.ilike("marqueproduit", marque + "%");
        criteria.add(criterion);
        
        List<Produit> listProduits = criteria.list();

        session.close();
            
        return listProduits;
    }
    
}
