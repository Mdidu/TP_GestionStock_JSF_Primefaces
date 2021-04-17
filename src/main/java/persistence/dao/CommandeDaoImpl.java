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
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			tx = session.beginTransaction(); 
			session.save(commande);
			tx.commit();
		}
	}

	@Override
	public void delete(Commande commande) {
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			tx = session.beginTransaction(); 
			session.delete(commande);
			tx.commit();
		}
		
	}

	@Override
	public void update(Commande commande) {
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			tx = session.beginTransaction(); 
			session.update(commande);
			tx.commit();
		}
	}

	@Override
	public List<Commande> findAll() {
		List<Commande> listeCommande = null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
				
			listeCommande = session.createQuery("from Commande").list();
		}
		return listeCommande;
	}

	@Override
	public Commande findById(Serializable commande) {
		Commande cmd = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			cmd = session.get(Commande.class,(CommandeId) commande);
		}
		return cmd;
	}

	@Override
	public List<Commande> findByEtat(BigDecimal idetat) {
		Etat etat = new Etat(idetat);
		List<Commande> listCommande = null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
				
			Criteria criteria = session.createCriteria(Commande.class);
			Criterion criterion = Restrictions.eq("etat", etat);
			criteria.add(criterion);
			listCommande = criteria.list();
		}
		return listCommande;
	}
	
	public List<Commande> findByProduit(Produit produit) {
		List<Commande> listCommande = null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {

			Criteria criteria = session.createCriteria(Commande.class);
			Criterion criterion = Restrictions.eq("produit", produit);
			criteria.add(criterion);
			listCommande = criteria.list();
		}
		return listCommande;
	}
	
	@Override
	public List<Commande> findByDate(Date dateDebut,Date dateFin) {
		
		List<Commande> listCommande = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {

			Criteria criteria = session.createCriteria(Commande.class);
			Criterion criterion = Restrictions.between("datecommande", dateDebut, dateFin);
			criteria.add(criterion);
			listCommande = criteria.list();
		}
		return listCommande;
	}
}
