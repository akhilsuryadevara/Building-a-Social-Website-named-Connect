import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet{
	 protected void doPost(HttpServletRequest request,
             HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
	PrintWriter pw = response.getWriter();
	Utilities utility = new Utilities(request, pw);
	HttpSession session1 = request.getSession();
    
    String username = session1.getAttribute("username").toString();
	String password = request.getParameter("password");
	String repassword = request.getParameter("repassword");
	
	MySQLDataStoreUtilities.changePassword(username,password,repassword);
	if (utility.isLoggedin())
		utility.printHtml("HeaderLogout.html");
	else
		utility.printHtml("Header.html");
	pw.println("<section id=\"content\">");
	pw.print("<h3 style=\"color:green\">Your password has been updated.</h3>");
  	pw.println("</section>");
  	utility.printHtml("LeftNav.html");
	utility.printHtml("Footer.html");
}

protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayChangePassword(request, response, pw, false);
}


protected void displayChangePassword(HttpServletRequest request, HttpServletResponse response, PrintWriter pw, boolean error)
throws ServletException, IOException {

		Utilities utility = new Utilities(request, pw);
		if (utility.isLoggedin())
			utility.printHtml("HeaderLogout.html");
		else
			utility.printHtml("Header.html");
		pw.println("<section id=\"content\">");
		pw.print("<form name ='ChangePassword' action='ChangePassword' method='post'>");
		pw.print("<div class='box'>");
		pw.print("<div class='box-header'>");
		pw.print("</div>");
		
		pw.print("<table  class='table'>");
		
		pw.print("<tr>");
		pw.print("<td><label><b> Password: </b></label></td>");
		pw.print("<td> <input type='password' name='password' class='password' required='true'> </td>");
		pw.print("</tr>");

		pw.print("<tr>");
		pw.print("<td><label><b> Re-enter Password: </b></label></td>");
		pw.print("<td> <input type='password' name='repassword' class='repassword' required='true'> </td>");
		pw.print("</tr>");

		pw.print(
				"<tr><td colspan='2'><input type='submit' class='lbutton' name='ChangePassword' value='Change Password'></td></tr></table>");
		
		pw.print("</div></form>");
		pw.println("</section>");
		utility.printHtml("LeftNav.html");
		utility.printHtml("Footer.html");
	}
}
