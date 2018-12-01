
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
import javax.servlet.http.HttpSession;

public class ViewSearchUser extends HttpServlet {
	HttpSession session;
	Utilities utility;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Users searchedUser = (Users) request.getAttribute("productSearched");
		Utilities utility = new Utilities(request, out);
		Carousel carousel = new Carousel();
		
		Users user = MySQLDataStoreUtilities.getProfile(searchedUser.getUsername());
		String username = utility.username();
		Users currentuser = MySQLDataStoreUtilities.getProfile(username);
		HashMap<String, Users> followingUsers = MySQLDataStoreUtilities.getFollowings(username);
		if(utility.isLoggedin())
			utility.printHtml("HeaderLogout.html");
		else
			utility.printHtml("Header.html");
		out.println("<div id=\"body\">");
		out.println("<section id=\"content\">");
		out.print("<div class='post'><h2 class='title meta'>");
		out.print("</h2><div class='entry'><table id='bestseller'>");
		
		out.print("<tr>");
		out.print("<td><div id='follower_item' style='text-align: center'>");
		out.print("<h3 style='text-align: center'>"+user.getUsername()+"</h3>");
		out.print("<strong style='text-align: center'>"+user.getFirstname()+   user.getLastname()+"</strong><ul>");
		out.print("<li id='item'><img src='Html/images/people/"+user.getProfilepic()+"' alt='' /></li>");
		if(currentuser.getUsertype().equals("Admin")) {
			out.print("<li><form method='post' action='DeleteUser'>" +
					"<input type='hidden' name='username' value='"+user.getUsername()+"'>"+
					"<input type='hidden' name='actualname' value='"+user.getUsername()+"'>"+
					"<input type='submit' class='lbutton' value='Delete'></form></li>");
		}else {
			if(followingUsers.containsKey(user.getUsername())) {
				out.print("<li><form method='post' action='Unfollow'>" +
						"<input type='hidden' name='username' value='"+user.getUsername()+"'>"+
						"<input type='hidden' name='actualname' value='"+user.getUsername()+"'>"+
						"<input type='submit' class='lbutton' value='Unfollow'></form></li>");
			}else {
			out.print("<li><form method='post' action='SaveFollowUser'>" +
					"<input type='hidden' name='username' value='"+user.getUsername()+"'>"+
					"<input type='hidden' name='actualname' value='"+user.getUsername()+"'>"+
					"<input type='submit' class='lbutton' value='Follow'></form></li>");
			out.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+user.getUsername()+"'>"+
					"<input type='hidden' name='price' value='23'>"+
				    "<input type='submit' value='Write Testimonial' class='lbutton'></form></li>");
			out.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+user.getUsername()+"'>"+
				    "<input type='submit' value='View Testimonial' class='lbutton'></form></li>");
			}
		}	
		out.print("</ul></div></td>");
		out.print("</tr>");
		
		out.print("</table></div></div>");
		if(!currentuser.getUsertype().equals("Admin")) 
			out.print(carousel.carouselfeature(utility));
		out.println("</section>");
		utility.printHtml("LeftNav.html");
		utility.printHtml("Footer.html");

		

	}
}
