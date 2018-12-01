import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShowPost")
public class ShowPost extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
	PrintWriter pw = response.getWriter();
	Utilities utility = new Utilities(request, pw);
	
	if(utility.isLoggedin())
		utility.printHtml("HeaderLogout.html");
	else
		utility.printHtml("Header.html");
	
	pw.println("<div id=\"body\">");
	pw.println("<section id=\"content\">");
    pw.print("<form name ='SubmitPost' action='SubmitPost' method='post'>");
    pw.print("<div class='box'>");
	pw.print("<div class='box-header'>");
	pw.print("</div>");
	
	
	pw.print("<table  class='table'>");
    pw.print("<tr><td><label><b> Post Comments: </b></label></td><td>");
    pw.print("<td><textarea name='postText' rows='4' cols='50'> </textarea></td></tr>");

    pw.print("<tr><td><label><b> Post Image Name: </b></label></td><td>");
    pw.print("<td> <input type='text' name='postImage'></td>");
    pw.print("</tr></table>");
    

    pw.print("<tr><td colspan='2'><input type='submit' class='lbutton' name='SubmitPost' value='UploadPost'></td></tr></table>");

    pw.print("</div></form>");
    pw.println("</section>");
	utility.printHtml("LeftNav.html");
	utility.printHtml("Footer.html");
	
	}
}
