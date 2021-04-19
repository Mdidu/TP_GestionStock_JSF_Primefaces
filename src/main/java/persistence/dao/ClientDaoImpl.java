package persistence.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import persistence.entities.Client;

public class ClientDaoImpl implements ClientDao {

	Transaction tx;

	@Override
	public void add(Client client) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		tx = session.beginTransaction();
		session.save(client);
		tx.commit();
		session.close();
	}

	@Override
	public void delete(Client client) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		tx = session.beginTransaction();
		session.delete(client);
		tx.commit();
		session.close();
	}

	@Override
	public void update(Client client) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		tx = session.beginTransaction();
		session.update(client);
		tx.commit();
		session.close();

	}

	@Override
	public List<Client> findAll() {

		Session session = HibernateUtil.getSessionFactory().openSession();

		List<Client> listeClient = session.createQuery("from Client").list();
		session.close();
		return listeClient;
	}

	@Override
	public Client findById(Serializable idclient) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Client client = session.get(Client.class, (int) idclient);
		session.close();
		return client;
	}

	public List<Client> findByNom(String nomclient) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Criteria criteria = session.createCriteria(Client.class);
		Criterion criterion = Restrictions.eq("nomclient", nomclient);
		criteria.add(criterion);
		List<Client> listClient = criteria.list();
		session.close();

		return listClient;
	}

	public List<Client> findByPrenom(String prenomclient) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Criteria criteria = session.createCriteria(Client.class);
		Criterion criterion = Restrictions.eq("prenomclient", prenomclient);
		criteria.add(criterion);
		List<Client> listClient = criteria.list();
		session.close();


		return listClient;
	}

}
