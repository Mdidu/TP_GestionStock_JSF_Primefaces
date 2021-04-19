package persistence.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import persistence.entities.Commande;
import persistence.entities.CommandeId;
import persistence.entities.Etat;
import persistence.entities.Produit;

public class CommandeDaoImpl implements CommandeDao {

	Transaction tx;
	
	@Override
	public void add(Commande commande) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.save(commande);
        tx.commit();
        session.close();
	}

	@Override
	public void delete(Commande commande) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.delete(commande);
        tx.commit();
        session.close();
		
	}

	@Override
	public void update(Commande commande) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.update(commande);
        tx.commit();
        session.close();
	}

	@Override
	public List<Commande> findAll() {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
				
		List<Commande> listeCommande = session.createQuery("from Commande").list();
		session.close();
		return listeCommande;
	}

	@Override
	public Commande findById(Serializable commande) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Commande cmd = session.get(Commande.class,(CommandeId) commande);
		session.close();
		return cmd;
	}

	@Override
	public List<Commande> findByEtat(BigDecimal idetat) {
		Etat etat = new Etat(idetat);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
				
		Criteria criteria = session.createCriteria(Commande.class);
		Criterion criterion = Restrictions.eq("etat", etat);
		criteria.add(criterion);
		List<Commande> listCommande = criteria.list();
		session.close();
		return listCommande;
	}
	
	public List<Commande> findByProduit(Produit produit) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		Criteria criteria = session.createCriteria(Commande.class);
		Criterion criterion = Restrictions.eq("produit", produit);
		criteria.add(criterion);
		List<Commande> listCommande = criteria.list();
		session.close();
		return listCommande;
	}
	
	@Override
	public List<Commande> findByDate(Date dateDebut,Date dateFin) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		Criteria criteria = session.createCriteria(Commande.class);
		Criterion criterion = Restrictions.between("datecommande", dateDebut, dateFin);
		criteria.add(criterion);
		List<Commande> listCommande = criteria.list();
		session.close();
		return listCommande;
	}
}
