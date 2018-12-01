import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ShowWriteComment")
public class ShowWriteComment extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
	PrintWriter pw = response.getWriter();
	HttpSession session = request.getSession();
	String postId = request.getParameter("postId");	
	String postText = request.getParameter("postText");	
	String postImage = request.getParameter("postImage");	
	Utilities utility = new Utilities(request, pw);
	String username = (String) session.getAttribute("username");
	String previousComments[]=null;
	previousComments=MongoDBDataStoreUtilities.getPreviousComments(postId,username);
	
	if (utility.isLoggedin())
		utility.printHtml("HeaderLogout.html");
	else
		utility.printHtml("Header.html");
	pw.println("<section id=\"content\">");
	pw.print("<form name ='SubmitComment' action='SubmitComment' method='post'>");
	pw.print("<input type='hidden' name='postId' value='"+postId+"'>");
	pw.print("<div class='box'>");
	pw.print("<div class='box-header'>");
	pw.print("</div>");
	pw.print("<table  class='table' style='border:double'>");
	pw.print("<tr><td align='center'><label><b>"+postText+"</label></b></td></tr>");
	pw.print("<tr>");
    pw.print("<td><a  href=\"#\">");
    pw.print("<img src=\"Html/images/people/" + postImage
			+ "\" alt=\"Trolltunga Norway\" width=\"720\" height=\"400\">");
    pw.print("</a></td>");
    pw.print("</tr>");
    pw.print("<tr>");
    pw.print("<td><label><b>Previous Comments: </b></label>");
    pw.print("<br>");
    if(previousComments!=null && previousComments[0]!=null) {
    	for(int i=0;i<previousComments.length;i++) {
		    
		   
		    pw.print(previousComments[i]);
		    pw.print("<br><br>");
		    
    	}
    }
    pw.print("</td></tr>");
    pw.print("<tr><td style='vertical-align: middle;'><label><b>Your Comment :</b></label>");
    pw.print("<textarea name='commentText' class='uname' required='true' style=\"margin: 15px; width: 759px; height: 158px;\"> </textarea></td>");
    pw.print("</tr>");
    pw.print("<tr><td><input type='submit' class='lbutton' name='SubmitComment' value='Post Comment'></td></tr>");


    pw.print("</table>");
    pw.print("</div></form></section>");
    utility.printHtml("LeftNav.html");
	utility.printHtml("Footer.html");
	
	}
}
