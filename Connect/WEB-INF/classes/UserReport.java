import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserReport extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		Utilities utility = new Utilities(request,pw);
		
		HashMap<String,String> List = new HashMap<String,String>(); 
		List = MySQLDataStoreUtilities.getUsersByCity();
		
		List<Entry<String, String>> list = new LinkedList<Map.Entry<String, String>>(List.entrySet());

		//sorting the list with a comparator
		Collections.sort(list, new Comparator<Entry<String, String>>() {
			public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		//convert sortedMap back to Map
		Map<String, String> sortedMap = new LinkedHashMap<String, String>();
		for (Entry<String, String> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		if (utility.isLoggedin())
			utility.printHtml("HeaderLogout.html");
		else
			utility.printHtml("Header.html");
		pw.println("<section id=\"content\">");
	
		pw.print("<div class='box'>");
		pw.print("<div class='box-header'>");
		pw.print("<h3 class='box-title' style='font-weight: bold;text-transform: uppercase'>Users Per City</h3>");
		pw.print("</div>");
		pw.print("<table  class='table' style='border:double'>");
		pw.print("<th><label><b>User Name</b></label></th>");
		pw.print("<th><label><b>City</b></label></th>");
	    for (Map.Entry<String, String> entry : List.entrySet())
	    {
            pw.print("<tr style='border:inset'>");
    		pw.print("<td><label><b>"+entry.getKey()+"</b></label></td>");
    		pw.print("</td>");
    		pw.print("<td>");
    		pw.print("<label><b>"+entry.getValue()+"</b></label>");
    		pw.print("</td>");
    		pw.print("</tr>");

		}
            
	    pw.println("</table>");
		 pw.print("</div></form>");
		    pw.println("</section>");
			utility.printHtml("LeftNav.html");
			utility.printHtml("Footer.html");
				
	}
}
