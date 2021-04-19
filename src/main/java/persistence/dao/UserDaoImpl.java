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
		Session session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
		session.save(value);
		tx.commit();
		session.close();
	}

	@Override
	public void delete(Stockuser value) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
		session.delete(value);
		tx.commit();
		session.close();
	}

	@Override
	public void update(Stockuser value) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
		session.update(value);
		tx.commit();
		session.close();
	}

	@Override
	public Stockuser findById(Serializable id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Stockuser user = session.get(Stockuser.class, id);
		session.close();
		return user;
	}

	@Override
	public List<Stockuser> findAll() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Stockuser> listUsers = session.createQuery("from Stockuser").list();
		session.close();
		return listUsers;
	}

	@Override
	public Stockuser findUserByLoginAndPassword(String login, String password) {

		Session session = HibernateUtil.getSessionFactory().openSession();
			
		Criteria criteria = session.createCriteria(Stockuser.class);
		Criterion criterionLogin = Restrictions.eq("login", login);
		Criterion criterionPassword = Restrictions.eq("password", password);
		criteria.add(criterionLogin);
		criteria.add(criterionPassword);
		
		Stockuser user = (Stockuser) criteria.uniqueResult();
		session.close();
		return user;
	}

}
