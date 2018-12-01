import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/ManageAdvertise")
public class ManageAdvertise extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		Utilities utility = new Utilities(request,pw);
		String username = utility.username();
		
		HashMap<String, String > hm = new HashMap<String, String>();
		try{
			hm = MySQLDataStoreUtilities.getAllAds();
		}
		catch(Exception e)
		{
			
		}
		
		if(utility.isLoggedin())
			utility.printHtml("HeaderLogout.html");
		else
			utility.printHtml("Header.html");
		pw.println("<div id=\"body\">");
		pw.println("<section id=\"content\">");
		pw.print("<div class='post'><h2 class='title meta'>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, String> entry : hm.entrySet())
		{
			String image = entry.getValue();
			String url = entry.getKey();
			if(i%3==1)
			pw.print("<tr>");
			pw.print("<td><div id='follower_item' style='text-align: center'>");
			pw.print("<ul><li id='item'><img src='Html/images/advertise/"+image+"'></li>");
			pw.print("<li><form method='post' action='DeleteAdvertise'>" +
					"<input type='hidden' name='AdImage' value='"+entry.getValue()+"'>"+
					"<input type='hidden' name='AdUrl' value='"+entry.getKey()+"'>"+
					"<input type='submit' class='lbutton' value='Delete Advertise'></form></li>");
			
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size)
			pw.print("</tr>");
			i++;
		}
		pw.print("<form method='get' action='NewAd'>" +
				"<input type='submit' class='lbutton' value='Add New Advertise'></form><br><br>");

		pw.print("</table></div></div>");
		pw.println("</section>");
		utility.printHtml("LeftNav.html");
		utility.printHtml("Footer.html");
	}
	
}
