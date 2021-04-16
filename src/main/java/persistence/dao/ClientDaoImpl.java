package persistence.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import persistence.entities.Client;

public class ClientDaoImpl implements ClientDao {

	@Override
	public void add(Client client) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(client);
		tx.commit();
		session.close();
	}

	@Override
	public void delete(Client client) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction(); 
		s.delete(client);
		tx.commit();
		s.close();
		
	}

	@Override
	public void update(Client client) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction(); 
		s.update(client);
		tx.commit();
		s.close();
		
	}

	@Override
	public List<Client> findAll() {
		List<Client> listeClient = new ArrayList<Client>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		listeClient = s.createQuery("from Client").list();
		s.close();
		return listeClient;
	}

	@Override
	public Client findById(Serializable idclient) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		Client client = s.get(Client.class,(int) idclient);
		s.close();
		return client;
	}
	
	public List<Client> findByNom(String nomclient) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		Criteria crt = s.createCriteria(Client.class);
		Criterion crt1 = Restrictions.eq("nomclient", nomclient);
		crt.add(crt1);
		List<Client> listClient = crt.list();
		s.close();
		return listClient;
	}
	
	public List<Client> findByPrenom(String prenomclient) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		Criteria crt = s.createCriteria(Client.class);
		Criterion crt1 = Restrictions.eq("prenomclient", prenomclient);
		crt.add(crt1);
		List<Client> listClient = crt.list();
		s.close();
		return listClient;
	}

}
