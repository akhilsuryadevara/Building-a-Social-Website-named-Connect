import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginParser extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String usertype = request.getParameter("usertype");
		
		System.out.println("usertype"+usertype);
		HttpSession session = request.getSession();
		HashMap<String, Users> hm = new HashMap<String, Users>();
		Utilities utility = new Utilities(request, pw);
		try {
				hm=MySQLDataStoreUtilities.selectUser();
	
		} catch (Exception ex) {
			ex.getMessage();
		}
		System.out.println("username+usertype "+username+usertype);
		if (hm.containsKey(username+usertype)) {
			Users user = (Users) hm.get(username+usertype);
			
			if(user.getPassword().equals(password) && user.getUsertype().equals(usertype)) {
				switch(usertype) {
				case "User":
					System.out.println("request.getRequestDispatcher(\"/Home\").forward(request, response);");
					session.setAttribute("user", user);
					session.setAttribute("username", username);
					session.setAttribute("usertype", usertype);
					request.getRequestDispatcher("/Home").forward(request, response);
					break;
				case "Admin":
					System.out.println("INside request.getRequestDispatcher(\"/ManageUser\")");
					session.setAttribute("user", user);
					session.setAttribute("username", username);
					session.setAttribute("usertype", usertype);
					request.getRequestDispatcher("/ManageUser").forward(request, response);
					break;
				default:
					System.out.println("No case found.");
				}
			}else if (!user.getPassword().equals(password)) {
				System.out.println("user.getpassword()" + user.getPassword());
				System.out.println("password" + password);
				session.setAttribute("login_msg", "Login Failed. Please enter correct password.");
				request.getRequestDispatcher("/Login").forward(request, response);
			} else if (!user.getUsertype().equals(usertype)) {
				System.out.println("user.getusertype()" + user.getUsertype());
				System.out.println("usertype" + usertype);
				session.setAttribute("login_msg", "Login Failed. Please select correct usertype.");
				request.getRequestDispatcher("/Login").forward(request, response);
			} 
		}else {
			utility.printHtml("Header.html");
			pw.println("<section>");
			pw.println("<h2 style=\"color:red;text-align: center\"> User does not exist or You have selected wrong Usertype.</h2>");
			pw.println(
					"<h2 style=\"color:blue;text-align: center\"> Please do Registartion<a href=\"Registration\" style=\"color:green\"> here.</a> </h2>");
			pw.println("</section>");
			utility.printHtml("Login.html");
			utility.printHtml("LoginLeftNav.html");
			utility.printHtml("Footer.html");
		}

	}

}