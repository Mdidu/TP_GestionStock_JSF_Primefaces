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

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			tx = session.beginTransaction();
			session.save(client);
			tx.commit();
		}
	}

	@Override
	public void delete(Client client) {

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			tx = session.beginTransaction();
			session.delete(client);
			tx.commit();
		}

	}

	@Override
	public void update(Client client) {

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			tx = session.beginTransaction();
			session.update(client);
			tx.commit();
		}

	}

	@Override
	public List<Client> findAll() {
		List<Client> listeClient = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			listeClient = session.createQuery("from Client").list();
		}
		return listeClient;
	}

	@Override
	public Client findById(Serializable idclient) {
		Client client = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			client = session.get(Client.class, (int) idclient);
		}
		return client;
	}

	public List<Client> findByNom(String nomclient) {
		List<Client> listClient = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			Criteria criteria = session.createCriteria(Client.class);
			Criterion criterion = Restrictions.eq("nomclient", nomclient);
			criteria.add(criterion);
			listClient = criteria.list();
		}
		return listClient;
	}

	public List<Client> findByPrenom(String prenomclient) {
		List<Client> listClient = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			Criteria criteria = session.createCriteria(Client.class);
			Criterion criterion = Restrictions.eq("prenomclient", prenomclient);
			criteria.add(criterion);
			listClient = criteria.list();
		}

		return listClient;
	}

}
