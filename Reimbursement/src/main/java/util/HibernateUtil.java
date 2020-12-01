package util;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	static Logger log = Logger.getLogger(HibernateUtil.class);
	private static Session ses;
	private static SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	public static Session getSession() {
		if(ses == null)
		{
			log.info("Creating new Session!");
			ses = sf.openSession();
		}
		return ses;
	}
	
	public static void closeSes()
	{
		ses.close();
		sf.close();
	}
	
}
