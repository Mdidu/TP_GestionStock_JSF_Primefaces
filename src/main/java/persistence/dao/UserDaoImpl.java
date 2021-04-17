package persistence.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import persistence.entities.Stockuser;

public class UserDaoImpl implements UserDao {

	private Transaction tx;
	
	@Override
	public void add(Stockuser value) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			session.save(value);
			tx.commit();
		}
	}

	@Override
	public void delete(Stockuser value) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			session.delete(value);
			tx.commit();
		}
	}

	@Override
	public void update(Stockuser value) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			session.update(value);
			tx.commit();
		}
	}

	@Override
	public Stockuser findById(Serializable id) {
		Stockuser user = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			user = session.get(Stockuser.class, id);
		}
		return user;
	}

	@Override
	public List<Stockuser> findAll() {
		List<Stockuser> listUsers = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			listUsers = session.createQuery("from Stockuser").list();
		}
		return listUsers;
	}

	@Override
	public Stockuser findUserByLoginAndPassword(String login, String password) {
		Stockuser user = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			Criteria criteria = session.createCriteria(Stockuser.class);
			Criterion criterionLogin = Restrictions.eq("login", login);
			Criterion criterionPassword = Restrictions.eq("password", password);
			criteria.add(criterionLogin);
			criteria.add(criterionPassword);
			
			user = (Stockuser) criteria.uniqueResult();
		}
		return user;
	}

}
