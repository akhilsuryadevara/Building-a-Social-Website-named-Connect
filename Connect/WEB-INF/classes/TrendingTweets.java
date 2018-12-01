import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

public class TrendingTweets extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		Utilities utility = new Utilities(request,pw);
		
		String username = utility.username();
	

		if (utility.isLoggedin())
			utility.printHtml("HeaderLogout.html");
		else
			utility.printHtml("Header.html");
		pw.println("<section id=\"content\">");
	
		pw.print("<div class='box'>");
		pw.print("<div class='box-header'>");
		pw.print("<h3 class='box-title' style='font-weight: bold;text-transform: uppercase'>Top Trending Tweets</h3>");
		pw.print("</div>");
		pw.print("<table  class='table' style='border:double'>");
		pw.print("<th><label><b>Number</b></label></th>");
		pw.print("<th><label><b>Tweet URL</b></label></th>");
		Integer count=1;
		try
			{
				String line=null;
				String key = null;
				Boolean flag = false;
						
					
				BufferedReader reader = new BufferedReader(new FileReader (new File("C:\\apache-tomcat-7.0.34\\webapps\\Connect\\trend.txt")));

				while((line=reader.readLine()) != null)
				{
						if(line.contains("http"))
							{
								String withUrlString = line.substring(line.lastIndexOf(" ")+1); 
					            pw.print("<tr style='border:inset'>");
					    		pw.print("<td><label><b>"+count+"</b></label></td>");
					    		pw.print("</td>");
					    		pw.print("<td>");
					    		pw.print("<label><b><a href='"+withUrlString+"'>"+line+"</a></b></label>");
					    		pw.print("</td>");
					    		pw.print("</tr>");
					    		 count++;
							}
				}
			}
			catch(Exception e) {
				pw.println("<p align='center'>No Trendings</p>");
			}
		pw.println("</table>");
		 pw.print("</div></form>");
		    pw.println("</section>");
			utility.printHtml("LeftNav.html");
			utility.printHtml("Footer.html");
				
	}
}
