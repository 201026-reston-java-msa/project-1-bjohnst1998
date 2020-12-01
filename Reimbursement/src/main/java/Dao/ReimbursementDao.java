package Dao;

import java.util.List;

import javax.persistence.RollbackException;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Reimbursement;
import model.User;
import util.HibernateUtil;

public class ReimbursementDao implements DaoTemplate<Reimbursement> {
	private static Logger log = Logger.getLogger(ReimbursementDao.class);

	public ReimbursementDao() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public List<Reimbursement> findAll() {
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> r = ses.createQuery("from reimbursements", Reimbursement.class).list();
		log.info("Found Users: " + r.size());
		return r;
	}

	@Override
	public boolean insert(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		try {
			ses.save(r);
			tx.commit();
			return true;
		} catch (RollbackException e) {
			// TODO: handle exception
			log.warn("Failed to insert into Users table", e);
			return false;

		}
	}

	@Override
	public boolean update(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		try {
			ses.update(r);
			tx.commit();
			return true;
		} catch (RollbackException e) {
			// TODO: handle exception
			log.warn("Failed to update users table", e);
			return false;

		}
	}

	@Override
	public Reimbursement findById(int id) {
		Session ses = HibernateUtil.getSession();
		Reimbursement r = ses.get(Reimbursement.class, id);
		return r;
	}

	@Override
	public Reimbursement findByName(String name) {
		return null;
	}
}
