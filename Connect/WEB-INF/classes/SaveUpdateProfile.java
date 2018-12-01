import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/SaveUpdateProfile")

public class SaveUpdateProfile extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
	        PrintWriter pw = response.getWriter();
	        Utilities utility = new Utilities(request, pw);

	        HttpSession session1 = request.getSession();
	        
	        String username = session1.getAttribute("username").toString();
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String email = request.getParameter("email");
			String birthday = request.getParameter("birthday");
			String gender = request.getParameter("gender");
			String profilepic = request.getParameter("profilepic");
			String city = request.getParameter("city");
			String school = request.getParameter("school");
			
	       
			MySQLDataStoreUtilities.updateProfile(username,firstname,lastname,email,birthday,gender,profilepic,city,school);
			
			if (utility.isLoggedin())
				utility.printHtml("HeaderLogout.html");
			else
				utility.printHtml("Header.html");
			pw.println("<section id=\"content\">");
			pw.print("<h3 style=\"color:green\">Your profile has been updated.</h3>");
	      	pw.println("</section>");
	      	utility.printHtml("LeftNav.html");
	    	utility.printHtml("Footer.html");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);

	}
}
