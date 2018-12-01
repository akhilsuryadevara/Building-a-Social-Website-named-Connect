import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class AjaxUtilities extends HttpServlet {
   String searchKeyword;
   String action;
  HashMap<String, Users> users ;
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
StringBuffer sb = new StringBuffer();

		boolean prod  = false;
		PrintWriter out= response.getWriter();
		users = MySQLDataStoreUtilities.getAllUsers();
		action = request.getParameter("action");
		searchKeyword = request.getParameter("id");	  
		Utilities utility = new Utilities(request,out);
		String username = utility.username();
		users.remove(username);
	  
	  if(searchKeyword!= "" || !searchKeyword.equals(""))
	  {
		
	  
		  if(action.equals("complete"))
		  {
			  searchKeyword = searchKeyword.trim().toLowerCase();  
				 for(Map.Entry<String,Users> entry : users.entrySet())
					{
					 Users user=(Users)entry.getValue();
						String Userfullname=user.getFirstname()+user.getLastname();
						if(Userfullname.toLowerCase().startsWith(searchKeyword))
						{
						sb.append("<product>");
						sb.append("<id>" + user.getUsername() + "</id>");
						sb.append("<name>" + user.getFirstname()+user.getLastname() + "</name>");
						sb.append("</product>");
						prod = true;
						}
					}	
					if(prod)
					{
						response.setContentType("text/xml");
						response.setHeader("Cache-Control","no-cache");
						out.write("<products>" + sb.toString() +"</products>");
					}
					else
					{
						response.setStatus(HttpServletResponse.SC_NO_CONTENT);
					}
		  }
		if(action.equals("lookup"))
			{
				request.setAttribute("productSearched",users.get(searchKeyword));
				request.getRequestDispatcher("/ViewSearchUser").forward(request, response);
			}
	  }
	  }
}