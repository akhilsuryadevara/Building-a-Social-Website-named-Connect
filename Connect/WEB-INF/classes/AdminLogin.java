import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminLogin extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession();
		Utilities utility = new Utilities(request, pw);
		HashMap<String,Post> postList= new HashMap<String,Post>();
		String username = (String) session.getAttribute("username");
		postList = MySQLDataStoreUtilities.getAllPosts();
		if (session.getAttribute("username") != null) {
			if (utility.isLoggedin())
				utility.printHtml("HeaderLogout.html");
			else
				utility.printHtml("Header.html");
			pw.println("<section id=\"content\">");
			pw.print("<div class='box'>");
			pw.print("<div class='box-header'>");
			pw.print("</div>");
			
				for (Map.Entry<String, Post> entry : postList.entrySet()) {
					Post post = (Post) entry.getValue();
					pw.print("<table  class='table' style='border:double'>");
					pw.print("<tr><td align='center' colspan='2'><label><b>"+post.getPostText()+"</label></b></td></tr>");
					
					pw.print("<tr>");
		            pw.print("<td><a  href=\"#\">");
		            pw.print("<img src=\"Html/images/people/" + post.getPostImage()
							+ "\" alt=\"Trolltunga Norway\" width=\"720\" height=\"400\">");
		            pw.print("</a></td>");
		            pw.print("</tr>");
		            pw.print("<tr><td style='text-align: center;'>"+
		            		"<button class=\"lbutton\"><a href=\"DeletePost?postId=" + post.getPostId() + "&&postText=" + post.getPostText() +
		            		"&&postImage=" + post.getPostImage() +
		            		"\"	 class=\"btnreview\" style=\"color:white\">Delete</a></button>"
		            		+ "</td></tr>");	
	
					pw.print("</table>");
				}
				pw.print("</div></section>");
				utility.printHtml("LeftNav.html");
				utility.printHtml("Footer.html");
			
		} else {
			utility.printHtml("Header.html");
			utility.printHtml("Login.html");
			utility.printHtml("LoginLeftNav.html");;
			utility.printHtml("Footer.html");
		}
	}

}
