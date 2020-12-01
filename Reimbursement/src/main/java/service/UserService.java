package service;

import org.apache.log4j.Logger;

import Dao.UserDao;
import model.User;
import model.UserDTO;
import util.SecurityUtil;

public class UserService {

	private static Logger log = Logger.getLogger(UserService.class);
	private static UserDao uDao = new UserDao();
	
	public boolean insert(User u)
	{
		String oldPas = u.getPassword();
		u.setPassword(encrypt(oldPas));
		return uDao.insert(u);
	}
	
	public boolean update(User u)
	{
		String oldPas = u.getPassword();
		u.setPassword(encrypt(oldPas));
		return uDao.update(u);	
	}
	
	public User findById(int id)
	{
		User u = uDao.findById(id);
		String ePass = u.getPassword();
		u.setPassword(decrypt(ePass));
		return u;

	}
	
	public User login(String username, String password)
	{
		User u = uDao.findByName(username);
		if(u !=null)
		{
			String ePass = u.getPassword();
			u.setPassword(decrypt(ePass));
			if(u.getPassword().equals(password))
			{
				log.info(username + " logged in successfully");
				return u;
			}
			else 
			{
				log.info(username + " failed to log in");

				return null;
			}
		}
		else {
			return null;
		}
		
		
	}
	
	public String encrypt(String s)
	{
		String encrypted = null;
		try {
		SecurityUtil secure = new SecurityUtil();
		
		encrypted = secure.encrypt(s);
		
		}catch(Exception e)
		{
			log.warn("Failed to encrypt user info, aborting",e);
		}
		return encrypted;
	}
	public String decrypt(String s)
	{
		String decrypted = null;
		try {
			SecurityUtil secure = new SecurityUtil();
			decrypted = secure.decrypt(s);
		}catch (Exception e) {
			log.warn("Failed to decrypt user info, aborting",e);
		}
		return decrypted;
	}
	
	public UserDTO convertToDTO(User u)
	{
		return new UserDTO(u.getId(), u.getUsername(), u.getPassword(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getUserRole().getRoleName());
	}
}












