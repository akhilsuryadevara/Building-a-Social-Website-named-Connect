import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentReport extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		Utilities utility = new Utilities(request,pw);
		
		String username = utility.username();
		List<String> usernameList = new ArrayList<String>(); 
		usernameList = MySQLDataStoreUtilities.getAllUserNames();
		int count=0;

		if (utility.isLoggedin())
			utility.printHtml("HeaderLogout.html");
		else
			utility.printHtml("Header.html");
		pw.println("<section id=\"content\">");
	
		pw.print("<div class='box'>");
		pw.print("<div class='box-header'>");
		pw.print("<h3 class='box-title' style='font-weight: bold;text-transform: uppercase'>Comments Per User</h3>");
		pw.print("</div>");
		pw.print("<table  class='table' style='border:double'>");
		pw.print("<th><label><b>User Name</b></label></th>");
		pw.print("<th><label><b>Comment Count</b></label></th>");

		for(int i=0;i<usernameList.size();i++)
		{
			count = MongoDBDataStoreUtilities.getCommentsCount(usernameList.get(i));
            pw.print("<tr style='border:inset'>");
    		pw.print("<td><label><b>"+usernameList.get(i)+"</b></label></td>");
    		pw.print("</td>");
    		pw.print("<td>");
    		pw.print("<label><b>"+count+"</b></label>");
    		pw.print("</td>");
    		pw.print("</tr>");

		}
            
		pw.println("</table>");
		 pw.print("</div></form>");
		    pw.println("</section>");
			utility.printHtml("LeftNav.html");
			utility.printHtml("Footer.html");
				
	}

}
