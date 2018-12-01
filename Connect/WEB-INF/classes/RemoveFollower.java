
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

@WebServlet("/RemoveFollower")

public class RemoveFollower extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("name");	
		
		HashMap<String, Users > follow = new HashMap<String, Users>();
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		Utilities utility = new Utilities(request, pw);
		String follower = request.getParameter("username");
		String username = utility.username();
		int result=0;
		try{
			result = MySQLDataStoreUtilities.removeFollower(follower,username);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		if (utility.isLoggedin())
			utility.printHtml("HeaderLogout.html");
		else
			utility.printHtml("Header.html");
		pw.println("<section id=\"content\">");
		if(result>0)
			pw.print("<h3 style=\"color:green\">Your have successfully removed the friend " +follower+".</h3>");
		else
			pw.print("<h3 style=\"color:red\">Error in removing the friend " +follower+".</h3>");
      	pw.println("</section>");
      	utility.printHtml("LeftNav.html");
    	utility.printHtml("Footer.html");
	}
}
	
