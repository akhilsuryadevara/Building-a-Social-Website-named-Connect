import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletePost extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
		System.out.println("In DeletePost: Do post method");
        
		String postId = request.getParameter("postId");
		
		System.out.println("In DeletePost: postId:"+postId);
		MySQLDataStoreUtilities.deletePost(postId);
		
		if(utility.isLoggedin())
			utility.printHtml("HeaderLogout.html");
		else
			utility.printHtml("Header.html");
		pw.println("<section id=\"content\">");
		pw.print("<h3 style=\"color:green\">Your have successfully deleted the post.</h3>");
      	pw.println("</section>");
      	utility.printHtml("LeftNav.html");
    	utility.printHtml("Footer.html");

    }
}
