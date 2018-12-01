import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet{
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
			response.sendRedirect("Home");
		
		displayUpdateForm(request, response);
	}


/* displayCart Function shows the products that users has bought, these products will be displayed with Total Amount.*/

	protected void displayUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		HttpSession session1 = request.getSession();
		String username = null;
		Users user;
		username = session1.getAttribute("username").toString();
		System.out.println("Username: "+username);
		user = MySQLDataStoreUtilities.getProfile(username);
		
		Utilities utility = new Utilities(request,pw);
		//Carousel carousel = new Carousel();
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);
			session.setAttribute("login_msg", "Please Login to update profile");
			response.sendRedirect("Login");
			return;
		}

		if(utility.isLoggedin())
			utility.printHtml("HeaderLogout.html");
		else
			utility.printHtml("Header.html");
		
		pw.println("<div id=\"body\">");
		pw.println("<section id=\"content\">");
		
		pw.print("<form name ='SaveUpdateProfile' action='SaveUpdateProfile' method='post'>");
		pw.print("<div class='box'>");
		pw.print("<div class='box-header'>");
		pw.print("</div>");
		
		
		pw.print("<table  class='table'>");
		pw.print("<tr><td><label><b> Username: </b></label></td><td>");
		pw.print(user.getUsername());
		pw.print("<input type='hidden' name='username' value='" + user.getUsername() + "'>");
		pw.print("</td></tr>");
		
		
		
		pw.print("<tr><td><label><b> First Name: </b></label></td><td>");
		pw.print("<input type='text' name='firstname' value='" + user.getFirstname() + "'>");
		pw.print("</td></tr>");
		
		pw.print("<tr><td><label><b> Last Name: </b></label></td><td>");
		pw.print("<input type='text' name='lastname' value='" + user.getLastname() + "'>");
		pw.print("</td></tr>");
		
		pw.print("<tr><td><label><b> Email: </b></label></td><td>");
		pw.print("<input type='text' name='email' value='" + user.getEmail() + "'>");
		pw.print("</td></tr>");
		
		pw.print("<tr><td><label><b> Birthday: </b></label></td><td>");
		pw.print("<input type='date' name='birthday' value=\"" + user.getBirthday()+ "\">");
			
		pw.print("</td></tr>");
		
		

		pw.print("<tr><td><label><b> Gender: </b></label></td>");
		pw.print("<td>");
		pw.print("<select name='gender' class='uname' >");
		if(user.getGender().equalsIgnoreCase("Male"))
			pw.print("<option value='Male' selected=\"selected\">Male</option>");
		else
			pw.print("<option value='Male'>Male</option>");
		
		if(user.getGender().equalsIgnoreCase("Female"))
			pw.print("<option value='Female' selected=\"selected\">Female</option>");
		else
			pw.print("<option value='Female'>Female</option>");
		
		if(user.getGender().equalsIgnoreCase("Other"))
			pw.print("<option value='Other' selected=\"selected\">Other</option>");
		else
			pw.print("<option value='Other' >Other</option>");
		pw.print("</td></tr>");

		pw.print("<tr><td><label><b> Profile Photo: </b></label></td><td>");
		pw.print("<input type='text' name='profilepic' value='" + user.getProfilepic() + "'>");
		pw.print("</td></tr>");
		
		pw.print("<tr><td><label><b> City: </b></label></td><td>");
		pw.print("<input type='text' name='city' value='" + user.getCity() + "'>");
		pw.print("</td></tr>");
		
		pw.print("<tr><td><label><b> School: </b></label></td><td>");
		pw.print("<input type='text' name='school' value='" + user.getSchool() + "'>");
		pw.print("</td></tr>");

		pw.print("<tr><td>");
		 pw.print("<input type='submit' class='lbutton' name='UpdateProfile' value='Update Profile'>");
		 
		 pw.print("<td>"
		 		+ "<button class=\"lbutton\"><a href=\"ChangePassword?username=" + user.getUsername() +
         		"\"	 class=\"btnreview\" style=\"color:white\">Change Password</a></button>"
         		+ "</td></tr></table>");	
		
			

			pw.print("</div></form>");
			pw.println("</section>");
			utility.printHtml("LeftNav.html");
			utility.printHtml("Footer.html");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		String username=null;
		HttpSession session1 = request.getSession();
		username = session1.getAttribute("username").toString();
		if(username.equals(null))
		{
			
		}
		else {
			displayUpdateForm(request, response);
		}

	}
}
