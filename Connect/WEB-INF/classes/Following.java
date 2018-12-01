import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/Following")

public class Following extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		String username = utility.username();
		HashMap<String, Users > follow = new HashMap<String, Users>();
		
		try{
		     follow = MySQLDataStoreUtilities.getFollowings(username);
		}
		catch(Exception e)
		{
			
		}

		if(utility.isLoggedin())
			utility.printHtml("HeaderLogout.html");
		else
			utility.printHtml("Header.html");
		pw.println("<div id=\"body\">");
		pw.println("<section id=\"content\">");
		if(follow.size()>0) {
		pw.print("<div class='post'><h2 class='title meta'>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= follow.size();
		for(Map.Entry<String, Users> entry : follow.entrySet())
		{
			Users user = entry.getValue();
			if(i%3==1)
			pw.print("<tr>");
			pw.print("<td><div id='follower_item' style='text-align: center'>");
			pw.print("<h3 style='text-align: center'>"+user.getUsername()+"</h3>");
			pw.print("<strong style='text-align: center'>"+user.getFirstname()+   user.getLastname()+"</strong><ul>");
			pw.print("<li id='item'><img src='Html/images/people/"+user.getProfilepic()+"' alt='' /></li>");

			pw.print("<li><form method='post' action='Unfollow'>" +
					"<input type='hidden' name='username' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='actualname' value='"+user.getUsername()+"'>"+
					"<input type='submit' class='lbutton' value='Unfollow'></form></li>");
			
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size)
			pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div>");
		}else {
			pw.print("<h3 style=\"color:green\">There are no Following for you.</h3>");
		}
		pw.println("</section>");
		utility.printHtml("LeftNav.html");
		utility.printHtml("Footer.html");

	}
}

