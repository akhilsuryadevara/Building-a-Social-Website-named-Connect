import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LikeCount")
public class LikeCount extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
		System.out.println("In DeletePost: Do post method");
        
		String postId = request.getParameter("postId");
		String count = request.getParameter("value");
		MySQLDataStoreUtilities.incrementLikeCount(postId,count);
		
		
    }
}
