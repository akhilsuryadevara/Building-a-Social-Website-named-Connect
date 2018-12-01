import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUser extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
        
		String username = request.getParameter("username");
		
		MySQLDataStoreUtilities.deleteUser(username);
		
		if(utility.isLoggedin())
			utility.printHtml("HeaderLogout.html");
		else
			utility.printHtml("Header.html");
		pw.println("<section id=\"content\">");
		pw.print("<h3 style=\"color:green\">Your have successfully deleted the user.</h3>");
      	pw.println("</section>");
      	utility.printHtml("LeftNav.html");
    	utility.printHtml("Footer.html");

    }
}
