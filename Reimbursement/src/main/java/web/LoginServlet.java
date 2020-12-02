package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.LoginTemplate;
import model.Role;
import model.User;
import model.UserDTO;
import service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ObjectMapper mapper = new ObjectMapper();
	Logger log = Logger.getLogger(LoginServlet.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
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
		
		LoginTemplate l = mapper.readValue(body,LoginTemplate.class);
		String username = l.getUsername();
		String password = l.getPassword();
		log.info("User attempted to log on with username: " +username);
		
		UserService uServ = new UserService();
		User u=uServ.login(username, password);
		if(u!=null)
		{
			log.info(username +" has logged in successfully");
			
			
			session.setAttribute("user", u);
			
			PrintWriter writer = resp.getWriter();
			resp.setContentType("application/json");
			UserDTO uDto = uServ.convertToDTO(u);
			writer.println(mapper.writeValueAsString(uDto));
			
		}
		else {
			resp.setContentType("application/json");
			resp.setStatus(204);
		}
		
		
		
	}

}
