package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class ReimbursementServlet
 */
public class ReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Logger log = Logger.getLogger(ReimbursementServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("user") != null)
		{
			if(ReimbursementController.submitReimbursement(req, resp))
			{
				log.info("Reimbursement submitted!");
				resp.setContentType("application/json");
				resp.setStatus(200);
				resp.getWriter().println("Completed!");
			}
		}
		
	}

}
