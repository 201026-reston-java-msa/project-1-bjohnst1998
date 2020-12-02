package Dao;

import java.util.List;

import javax.persistence.RollbackException;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Reimbursement;
import model.ReimbursementDTO;
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
		ses.clear();

		List<Reimbursement> r = ses.createQuery("from Reimbursement", Reimbursement.class).list();
		log.info("Found Users: " + r.size());
		return r;
	}

	@Override
	public boolean insert(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		ses.clear();

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
		ses.clear();
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
		ses.clear();
		Reimbursement r = ses.get(Reimbursement.class, id);
		return r;
	}

	@Override
	public Reimbursement findByName(String name) {
		return null;
	}
	
	public ReimbursementDTO convertToDTO(Reimbursement r)
	{
		String resolverName = null;
		if(r.getResolver() ==null)
		{
			resolverName = "";
		}
		else {
			resolverName =new String(r.getResolver().getFirstName()+" " + r.getResolver().getLastName());
		}
		return new ReimbursementDTO(r.getId(), r.getAmount(), r.getDescription(), new String(r.getUser().getFirstName()+" "+r.getUser().getLastName())
				, r.getDateSubmitted().toString(), resolverName, r.getStatus().getStatus(), r.getType().getType());
	}
	
	public List<Reimbursement> findByUserId(int id)
	{
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> r = ses.createQuery("from Reimbursement where user = " +id, Reimbursement.class).list();
		return r;
	}
	
}
