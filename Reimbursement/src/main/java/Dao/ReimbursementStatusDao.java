package Dao;

import java.util.List;

import javax.persistence.RollbackException;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Reimbursement;
import model.ReimbursementStatus;
import util.HibernateUtil;

public class ReimbursementStatusDao {
	private static Logger log = Logger.getLogger(ReimbursementStatusDao.class);
	
	public ReimbursementStatusDao()
	{
		
	}
	
	public List<ReimbursementStatus> findAll() {
		Session ses = HibernateUtil.getSession();
		List<ReimbursementStatus> r = ses.createQuery("from reimbursement_status", ReimbursementStatus.class).list();
		log.info("Found Status: " + r.size());
		return r;
	}

	
	public boolean insert(ReimbursementStatus r) {
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

	
	public boolean update(ReimbursementStatus r) {
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

	
	public ReimbursementStatus findById(int id) {
		Session ses = HibernateUtil.getSession();
		ReimbursementStatus r = ses.get(ReimbursementStatus.class, id);
		return r;
	}

	
	
	
}
