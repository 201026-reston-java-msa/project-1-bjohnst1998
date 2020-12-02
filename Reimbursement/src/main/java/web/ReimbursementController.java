package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import Dao.ReimbursementDao;
import Dao.ReimbursementStatusDao;
import Dao.ReimbursementTypeDao;
import model.Reimbursement;
import model.ReimbursementDTO;
import model.ReimbursementStatus;
import model.ReimbursementTemplate;
import model.User;
import model.UserDTO;
import model.UseridTemplate;
import service.UserService;
import util.SortByStatus;

public class ReimbursementController {
	private static Logger log = Logger.getLogger(ReimbursementController.class);
	private static ObjectMapper mapper = new ObjectMapper();
	private static UserService uServ = new UserService();
	private static ReimbursementStatusDao statusDao = new ReimbursementStatusDao();
	private static ReimbursementTypeDao typeDao = new ReimbursementTypeDao();
	private static ReimbursementDao reimburseDao = new ReimbursementDao();
	public static boolean submitReimbursement(HttpServletRequest req, HttpServletResponse resp) throws IOException, HTTPException
	{
		HttpSession session = req.getSession();
		User currentU = (User)session.getAttribute("user");
		
		BufferedReader r = req.getReader();
		StringBuilder s = new StringBuilder();
		//Transfor reader data to SB
		String line = r.readLine();
		while(line!=null)
		{
			s.append(line);
			line = r.readLine();
		}
		
		String body = s.toString();
		ReimbursementTemplate t = mapper.readValue(body,ReimbursementTemplate.class);
		Reimbursement re = new Reimbursement(t.getAmount(), t.getDescription(), null, currentU,statusDao.findById(1) ,typeDao.findById(t.getType()));
		if(!reimburseDao.insert(re))
		{
			resp.setContentType("application/json");
			resp.setStatus(204);

			return false;

		}
		else {
			

			return true;
		}

	}
	
	public static void viewAll(HttpServletRequest req, HttpServletResponse resp) throws IOException, HTTPException
	{
	 List<Reimbursement> l =  reimburseDao.findAll();
	 List<ReimbursementDTO> dtos = new ArrayList<>();
	 for(Reimbursement r : l)
	 {
		 ReimbursementDTO rd = reimburseDao.convertToDTO(r);
		 
		 dtos.add(rd);
	 }
	 Collections.sort(dtos,new SortByStatus());

	 resp.setContentType("application/json");
	 
	 resp.getWriter().println(mapper.writeValueAsString(dtos));
	 
	}
	
	public static void viewUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, HTTPException
	{
		BufferedReader r = req.getReader();
		StringBuilder s = new StringBuilder();
		//Transfor reader data to SB
		String line = r.readLine();
		while(line!=null)
		{
			s.append(line);
			line = r.readLine();
		}
		
		String body = s.toString();
		System.out.println(body);
		UseridTemplate i = mapper.readValue(body,UseridTemplate.class);
		int id = i.getUserId();
		 List<Reimbursement> l =reimburseDao.findByUserId(id);

		 List<ReimbursementDTO> dtos = new ArrayList<>();
		 for(Reimbursement re : l)
		 {
			 ReimbursementDTO rd = reimburseDao.convertToDTO(re);
			 dtos.add(rd);
		 }
		 Collections.sort(dtos,new SortByStatus());

		 resp.setContentType("application/json");
		 
		 resp.getWriter().println(mapper.writeValueAsString(dtos));
		
	}
	public static void viewCurrent(HttpServletRequest req, HttpServletResponse resp) throws IOException, HTTPException
	{
		
		User u  = (User)req.getSession().getAttribute("user");
		int i = u.getId();
		 List<Reimbursement> l =reimburseDao.findByUserId(i);

		 List<ReimbursementDTO> dtos = new ArrayList<>();
		 for(Reimbursement re : l)
		 {
			 ReimbursementDTO rd = reimburseDao.convertToDTO(re);
			 dtos.add(rd);
		 }
		 Collections.sort(dtos,new SortByStatus());

		 resp.setContentType("application/json");
		 
		 resp.getWriter().println(mapper.writeValueAsString(dtos));
	}
	
	public static void approveReim(HttpServletRequest req, HttpServletResponse resp) throws IOException, HTTPException
	{
		HttpSession session = req.getSession();
		User currentU = (User)session.getAttribute("user");

		log.info("Approved!");
		BufferedReader r = req.getReader();
		StringBuilder s = new StringBuilder();
		//Transfor reader data to SB
		String line = r.readLine();
		while(line!=null)
		{
			s.append(line);
			line = r.readLine();
		}
		
		String body = s.toString();
		System.out.println(body);
		
		UseridTemplate uid = mapper.readValue(body, UseridTemplate.class);
		Reimbursement rim= reimburseDao.findById(uid.getUserId());
		rim.setStatus(new ReimbursementStatus(2,"Approved"));
		rim.setResolver(currentU);
		if(reimburseDao.update(rim))
		{
			resp.setContentType("application/json");
			resp.setStatus(200);
			resp.getWriter().println("Success!");
		}
		else {
			resp.setContentType("application/json");
			resp.setStatus(204);
			resp.getWriter().println("Failed");
		}
	}
	
	public static void denyReim(HttpServletRequest req, HttpServletResponse resp) throws IOException, HTTPException
	{
		HttpSession session = req.getSession();
	User currentU = (User)session.getAttribute("user");
		log.info("Denied!");

		BufferedReader r = req.getReader();
		StringBuilder s = new StringBuilder();
		//Transfor reader data to SB
		String line = r.readLine();
		while(line!=null)
		{
			s.append(line);
			line = r.readLine();
		}
		
		String body = s.toString();
		System.out.println(body);
		
		UseridTemplate uid = mapper.readValue(body, UseridTemplate.class);
		Reimbursement rim= reimburseDao.findById(uid.getUserId());
		
		rim.setStatus(new ReimbursementStatus(3,"Denied"));
		rim.setResolver(currentU);
		if(reimburseDao.update(rim))
		{
			resp.setContentType("application/json");
			resp.setStatus(200);
			resp.getWriter().println("Success!");
		}
		else {
			resp.setContentType("application/json");
			resp.setStatus(204);
			resp.getWriter().println("Failed");
		}
	}

	public static void updateProfile(HttpServletRequest req, HttpServletResponse resp) throws IOException, HTTPException {
		log.info("Attempting to update profile");
		BufferedReader r = req.getReader();
		StringBuilder s = new StringBuilder();
		//Transfor reader data to SB
		String line = r.readLine();
		while(line!=null)
		{
			s.append(line);
			line = r.readLine();
		}
		
		String body = s.toString();
		UserDTO u = mapper.readValue(body, UserDTO.class);
		log.info(u);
		User usr = uServ.convertToUser(u);
		log.info(usr);
		if(uServ.update(usr))
		{
			log.info("Updated: " +usr.getId());

			resp.setContentType("application/json");
			resp.setStatus(200);
			resp.getWriter().println("Success!");
		}
		else {
			log.warn("Failed to update User: " +usr.getId());
			resp.setContentType("application/json");
			resp.setStatus(204);
			resp.getWriter().println("Failed");
		}
		
		
	}
	
	public static void viewAllEmployees(HttpServletRequest req, HttpServletResponse resp) throws IOException, HTTPException
	{
	 List<User> l =  uServ.findAll();
	 for(User u : l)
	 {
		 u.setPassword("hidden");
	 }
	 List<UserDTO> dtos = new ArrayList<>();
	 for(User r : l)
	 {
		 UserDTO rd = uServ.convertToDTO(r);
		 
		 dtos.add(rd);
	 }
	 resp.setContentType("application/json");
	 
	 resp.getWriter().println(mapper.writeValueAsString(dtos));
	 
	}
	
	public static void viewUserEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException, HTTPException
	{
		BufferedReader r = req.getReader();
		StringBuilder s = new StringBuilder();
		//Transfor reader data to SB
		String line = r.readLine();
		while(line!=null)
		{
			s.append(line);
			line = r.readLine();
		}
		
		String body = s.toString();
		System.out.println(body);
		UseridTemplate i = mapper.readValue(body,UseridTemplate.class);
		int id = i.getUserId();
		 User l =uServ.findById(id);
		 l.setPassword("hidden");
		 UserDTO uDto = uServ.convertToDTO(l);
		List<UserDTO> ul = new ArrayList<>();
		ul.add(uDto);
		 resp.setContentType("application/json");
		 
		 resp.getWriter().println(mapper.writeValueAsString(ul));
		
	}
	public static void viewCurrentEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException, HTTPException
	{
		
		User u  = (User)req.getSession().getAttribute("user");
		u.setPassword("hidden");
		UserDTO uD = uServ.convertToDTO(u);
		List<UserDTO> ul = new ArrayList<>();
		ul.add(uD);
		 resp.setContentType("application/json");
		 
		 resp.getWriter().println(mapper.writeValueAsString(ul));
		 log.info("Sent user to client");
	}
	
	
}
