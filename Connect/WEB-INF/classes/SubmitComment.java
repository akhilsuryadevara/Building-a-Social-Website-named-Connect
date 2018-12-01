import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SubmitComment")

public class SubmitComment extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();


		/* From the HttpServletRequest variable name,type,maker and acessories information are obtained.*/
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Utilities utility = new Utilities(request, pw);
		String postId = request.getParameter("postId");
		String commentText = request.getParameter("commentText");
		String username = utility.username();
		System.out.println("SubmitPost"+sdf.format(new Date()));
		try{
		    MongoDBDataStoreUtilities.insertComment(postId, username, commentText, sdf.format(new Date()));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		utility.printHtml("HeaderLogout.html");
		pw.println("<section id=\"content\">");
  		pw.print("<h4 style=\"color:green\">Your comment is posted successfully.</h4>");
  		pw.println("</section>");
  		utility.printHtml("LeftNav.html");
		utility.printHtml("Footer.html");
		
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	        Utilities utility= new Utilities(request, pw);
	}
}
