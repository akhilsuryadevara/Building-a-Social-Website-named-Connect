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

@WebServlet("/SubmitPost")

public class SubmitPost extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();


		/* From the HttpServletRequest variable name,type,maker and acessories information are obtained.*/
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Utilities utility = new Utilities(request, pw);
		String postText = request.getParameter("postText");
		String postImage = request.getParameter("postImage");
		String username = utility.username();
		System.out.println("SubmitPost"+sdf.format(new Date()));
		try{
		    MySQLDataStoreUtilities.AddPost(username,postText,postImage, sdf.format(new Date()),0,0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
        if(utility.isLoggedin())
			utility.printHtml("HeaderLogout.html");
		else
			utility.printHtml("Header.html");
		pw.println("<section id=\"content\">");
		pw.print("<h3 style=\"color:green\">Your post is uploaded successfully.</h3>");
      	pw.println("</section>");
      	utility.printHtml("LeftNav.html");
    	utility.printHtml("Footer.html");
		
		
	}
}
