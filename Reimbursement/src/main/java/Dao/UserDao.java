package Dao;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.RollbackException;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.User;
import util.HibernateUtil;

public class UserDao implements DaoTemplate<User> {
	private static Logger log = Logger.getLogger(UserDao.class);

	public UserDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<User> findAll() {
		Session ses = HibernateUtil.getSession();
		List<User> users = ses.createQuery("from User", User.class).list();
		log.info("Found Users: " + users.size());
		return users;
	}

	@Override
	public boolean insert(User user) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		try {
			ses.save(user);
			tx.commit();
			return true;
		} catch (RollbackException e) {
			// TODO: handle exception
			log.warn("Failed to insert into Users table", e);
			return false;

		}
	}

	@Override
	public boolean update(User user) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		try {
			ses.update(user);
			tx.commit();
			return true;
		} catch (RollbackException e) {
			// TODO: handle exception
			log.warn("Failed to update users table", e);
			return false;

		}
	}

	@Override
	public User findById(int id) {
		Session ses = HibernateUtil.getSession();
		User u = ses.get(User.class, id);
		return u;
	}

	@Override
	public User findByName(String name) {
		Session ses = HibernateUtil.getSession();
		List<User> foundU = ses.createQuery("from User where username ='" + name + "'", User.class).list();
		if (foundU.size() > 0) {
			return foundU.get(0);
		} else {
			return null;
		}
	}

}
