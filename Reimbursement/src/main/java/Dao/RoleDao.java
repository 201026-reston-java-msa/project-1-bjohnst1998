package Dao;

import java.util.List;

import javax.persistence.RollbackException;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Role;
import util.HibernateUtil;

public class RoleDao {
	private static Logger log = Logger.getLogger(RoleDao.class);
	
	public RoleDao()
	{
		
	}
	
	public List<Role> findAll() {
		Session ses = HibernateUtil.getSession();
		List<Role> r = ses.createQuery("from user_roles", Role.class).list();
		log.info("Found Status: " + r.size());
		return r;
	}

	
	public boolean insert(Role r) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		try {
			ses.save(r);
			tx.commit();
			return true;
		} catch (RollbackException e) {
			// TODO: handle exception
			log.warn("Failed to insert into Reimbursement Status table", e);
			return false;

		}
	}

	
	public boolean update(Role r) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		try {
			ses.update(r);
			tx.commit();
			return true;
		} catch (RollbackException e) {
			// TODO: handle exception
			log.warn("Failed to update reimbursement status table", e);
			return false;

		}
	}

	
	public Role findById(int id) {
		Session ses = HibernateUtil.getSession();
		Role r = ses.get(Role.class, id);
		return r;
	}
}
