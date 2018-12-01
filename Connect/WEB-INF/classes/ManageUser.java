import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ManageUser")
public class ManageUser extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("name");	
		Utilities utility = new Utilities(request,pw);
		String username = utility.username();
		HashMap<String, Users > follow = new HashMap<String, Users>();
		
		try{
		     follow = MySQLDataStoreUtilities.getAllUsers();
		}
		catch(Exception e)
		{
			
		}
		HashMap<String, Users> hm = new HashMap<String, Users>();
		if(CategoryName==null){
			hm.putAll(follow);
			name = "";
		 }		
		else
		{
		   if(CategoryName.equals("Chicago"))
			{
				for(Map.Entry<String,Users> entry : follow.entrySet())
				{
				 if(entry.getValue().getCity().equalsIgnoreCase("Chicago"))
				 {
					 hm.put(entry.getValue().getFirstname(),entry.getValue());
				 }
				}
			   	 name = "Chicago";
			}
		   
		   if(CategoryName.equals("New York"))
			{
				for(Map.Entry<String,Users> entry : follow.entrySet())
				{
				 if(entry.getValue().getCity().equalsIgnoreCase("New York"))
				 {
					 hm.put(entry.getValue().getFirstname(),entry.getValue());
				 }
				}
			   	 name = "New York";
			}
		   
		   if(CategoryName.equals("San Francisco"))
			{
				for(Map.Entry<String,Users> entry : follow.entrySet())
				{
				 if(entry.getValue().getCity().equalsIgnoreCase("san francisco"))
				 {
					 hm.put(entry.getValue().getFirstname(),entry.getValue());
				 }
				}
			   	 name = "San Fransisco";
			}
		   
		   if(CategoryName.equals("Dallas"))
			{
				for(Map.Entry<String,Users> entry : follow.entrySet())
				{
				 if(entry.getValue().getCity().equalsIgnoreCase("Dallas"))
				 {
					 hm.put(entry.getValue().getFirstname(),entry.getValue());
				 }
				}
			   	 name = "Dallas";
			}
		   
		   if(CategoryName.equals("Boston"))
			{
				for(Map.Entry<String,Users> entry : follow.entrySet())
				{
				 if(entry.getValue().getCity().equalsIgnoreCase("Boston"))
				 {
					 hm.put(entry.getValue().getFirstname(),entry.getValue());
				 }
				}
			   	 name = "Boston";
			}
		   
		   if(CategoryName.equals("Seattle"))
			{
				for(Map.Entry<String,Users> entry : follow.entrySet())
				{
				 if(entry.getValue().getCity().equalsIgnoreCase("Seattle"))
				 {
					 hm.put(entry.getValue().getFirstname(),entry.getValue());
				 }
				}
			   	 name = "Seattle";
			}
		   
		   if(CategoryName.equals("Phoenix"))
			{
				for(Map.Entry<String,Users> entry : follow.entrySet())
				{
				 if(entry.getValue().getCity().equalsIgnoreCase("Phoenix"))
				 {
					 hm.put(entry.getValue().getFirstname(),entry.getValue());
				 }
				}
			   	 name = "Phoenix";
			}
		   
		   if(CategoryName.equals("IIT"))
			{
				for(Map.Entry<String,Users> entry : follow.entrySet())
				{
				 if(entry.getValue().getSchool().equalsIgnoreCase("IIT"))
				 {
					 hm.put(entry.getValue().getFirstname(),entry.getValue());
				 }
				}
			   	 name = "IIT";
			}
		   
		   if(CategoryName.equals("CSU"))
			{
				for(Map.Entry<String,Users> entry : follow.entrySet())
				{
				 if(entry.getValue().getSchool().equalsIgnoreCase("CSU"))
				 {
					 hm.put(entry.getValue().getFirstname(),entry.getValue());
				 }
				}
			   	 name = "CSU";
			}
		   
		   if(CategoryName.equals("ASU"))
			{
				for(Map.Entry<String,Users> entry : follow.entrySet())
				{
				 if(entry.getValue().getSchool().equalsIgnoreCase("ASU"))
				 {
					 hm.put(entry.getValue().getFirstname(),entry.getValue());
				 }
				}
			   	 name = "ASU";
			}
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
		for(Map.Entry<String, Users> entry : hm.entrySet())
		{
			Users user = entry.getValue();
			if(i%3==1)
			pw.print("<tr>");
			pw.print("<td><div id='follower_item' style='text-align: center'>");
			pw.print("<h3 style='text-align: center'>"+user.getUsername()+"</h3>");
			pw.print("<strong style='text-align: center'>"+user.getFirstname()+   user.getLastname()+"</strong><ul>");
			pw.print("<li id='item'><img src='Html/images/people/"+user.getProfilepic()+"' alt='' /></li>");
			System.out.println("user pic name: "+user.getProfilepic());	
			pw.print("<li><form method='post' action='DeleteUser'>" +
					"<input type='hidden' name='username' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='actualname' value='"+user.getUsername()+"'>"+
					"<input type='submit' class='lbutton' value='Delete'></form></li>");
			
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size)
			pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div>");
		pw.println("</section>");
		utility.printHtml("LeftNav.html");
		utility.printHtml("Footer.html");

	}
}
