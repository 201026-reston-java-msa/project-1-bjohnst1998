package Dao;

import java.util.List;

import javax.persistence.RollbackException;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.ReimbursementType;
import util.HibernateUtil;

public class ReimbursementTypeDao {
	private static Logger log = Logger.getLogger(ReimbursementStatusDao.class);
	
	public ReimbursementTypeDao() {
		// TODO Auto-generated constructor stub
	}
	
	public List<ReimbursementType> findAll() {
		Session ses = HibernateUtil.getSession();
		List<ReimbursementType> r = ses.createQuery("from reimbursement_type", ReimbursementType.class).list();
		log.info("Found Status: " + r.size());
		return r;
	}

	
	public boolean insert(ReimbursementType r) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		try {
			ses.save(r);
			tx.commit();
			return true;
		} catch (RollbackException e) {
			// TODO: handle exception
			log.warn("Failed to insert into Reimbursement Type table", e);
			return false;

		}
	}

	
	public boolean update(ReimbursementType r) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		try {
			ses.update(r);
			tx.commit();
			return true;
		} catch (RollbackException e) {
			// TODO: handle exception
			log.warn("Failed to update reimbursement Type table", e);
			return false;

		}
	}

	
	public ReimbursementType findById(int id) {
		Session ses = HibernateUtil.getSession();
		ReimbursementType r = ses.get(ReimbursementType.class, id);
		return r;
	}

	
}
