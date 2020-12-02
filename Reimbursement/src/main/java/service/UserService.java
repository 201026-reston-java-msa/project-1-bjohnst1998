package service;

import java.util.List;

import org.apache.log4j.Logger;

import Dao.UserDao;
import model.User;
import model.UserDTO;
import util.SecurityUtil;

public class UserService {

	private static Logger log = Logger.getLogger(UserService.class);
	private static UserDao uDao = new UserDao();

	public boolean insert(User u) {
		String oldPas = u.getPassword();
		u.setPassword(encrypt(oldPas));
		return uDao.insert(u);
	}

	public boolean update(User u) {
		String oldPas = u.getPassword();
		u.setPassword(encrypt(oldPas));

		return uDao.update(u);
	}

	public User findById(int id) {
		User u = uDao.findById(id);
		String ePass = u.getPassword();
		u.setPassword(decrypt(ePass));
		return u;

	}

	public List<User> findAll() {
		List<User> list = uDao.findAll();
		for (User u : list) {
			u.setPassword(decrypt(u.getPassword()));
		}

		return list;
	}

	public User login(String username, String password) {
		List<User> l = uDao.findAll();
		User u = new User();
		for (User n : l) {
			if (n.getUsername().equals(username)) {
				u = n;
			}
		}
		String ePass = encrypt(password);
		
		if (u.getPassword().equals(ePass)) {
			log.info(username + " logged in successfully");
			return u;
		} else {
			log.info(username + " failed to log in");

			return null;
		}
	}

	public String encrypt(String s) {
		String encrypted = null;
		try {
			SecurityUtil secure = new SecurityUtil();

			encrypted = secure.encrypt(s);

		} catch (Exception e) {
			log.warn("Failed to encrypt user info, aborting", e);
		}
		return encrypted;
	}

	public String decrypt(String s) {
		String decrypted = null;
		log.info("String to decrypt:" + s);
		try {
			SecurityUtil secure = new SecurityUtil();
			decrypted = secure.decrypt(s);
		} catch (Exception e) {
			log.warn("Failed to decrypt user info, aborting", e);
		}
		return decrypted;
	}

	public UserDTO convertToDTO(User u) {
		return new UserDTO(u.getId(), u.getUsername(), u.getPassword(), u.getFirstName(), u.getLastName(), u.getEmail(),
				u.getUserRole().getRoleName());
	}

	public User convertToUser(UserDTO u) {
		User usr = findById(u.getId());
		if (u.getFirstName() != null && u.getFirstName() != "") {
			usr.setFirstName(u.getFirstName());
		}
		if (u.getLastName() != null && u.getLastName() != "") {
			usr.setLastName(u.getLastName());
		}
		if (u.getUsername() != null && u.getUsername() != "") {
			usr.setUsername(u.getUsername());
		}
		if (u.getPassword() != null && !u.getPassword().equals("hidden") && u.getPassword() != "") {
			usr.setPassword(u.getPassword());
		}
		return usr;
	}
}
