

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/WriteReview")

public class WriteReview extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
        review(request, response);
    }

    protected void review(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            Utilities utility = new Utilities(request, pw);
            HttpSession session = request.getSession(true);

            if (!utility.isLoggedin()) {
                session.setAttribute("login_msg", "Please Login to Write a Review");
                response.sendRedirect("Login");
                return;
            }
            String username = request.getParameter("username");
            Users user = MySQLDataStoreUtilities.getProfile(username);
            String currentuser=utility.username();
            if (utility.isLoggedin())
				utility.printHtml("HeaderLogout.html");
			else
				utility.printHtml("Header.html");
			pw.println("<section id=\"content\">");
			pw.print("<form name ='WriteReview' action='SubmitReview' method='post'>");
			pw.print("<div class='box'>");
			pw.print("<div class='box-header'>");
			pw.print("<h3 class='box-title'>Testimonial Review</h3>");
			pw.print("</div>");
			pw.print("<table  class='table'>");
			
			pw.print("<input type='hidden' name='reviewforuser' value='" + user.getUsername() + "'>");
			pw.print("<input type='hidden' name='userfullname' value='" + user.getFirstname()+" "+user.getLastname() + "'>");
			pw.print("<tr>"
					+ "<td><label><b> Writing a Testimonial for: </b></label></td><td>");
			pw.print(user.getFirstname()+" "+user.getLastname());
			pw.print("</td></tr>");
			

			pw.print("<tr><td><label><b> Everything Would Be Confidential on: </b></label></td><td>");
            pw.print("CONNECT");
            pw.print("</td></tr>");
			


            pw.print("<tr><td><label><b> Username: </b></label></td><td>");
            pw.print(currentuser);
            pw.print("</td></tr>");
            
            pw.print("<tr>");
			pw.print("<td><label><b> Age: </b></label></td>");
			pw.print("<td> <input type='text' name='userAge' class='uname' required='true'> </td>");
			pw.print("</tr>");

			pw.print("<tr><td><label><b> Gender: </b></label></td>");
			pw.print("<td>");
			pw.print("<select name='userGender' class='uname'>");
			pw.print("<option value='Male' selected>Male</option>");
			pw.print("<option value='Female'>Female</option>");
			pw.print("<option value='Other'>Other</option>");
			pw.print("</td></tr>");
			
			
		     pw.print("<tr>");
				pw.print("<td><label><b> What do you do: </b></label></td>");
				pw.print("<td> <input type='text' name='userOccupation' class='uname' required='true'> </td>");
				pw.print("</tr>");


				 pw.print("<tr>");
					pw.print("<td><label><b> Zip Code: </b></label></td>");
					pw.print("<td> <input type='text' name='userpin' class='uname' required='true'> </td>");
					pw.print("</tr>");
					

			pw.print("<tr>");
			pw.print("<td><label><b> City You Live In: </b></label></td>");
			pw.print("<td> <input type='text' name='usercity' class='uname' required='true'> </td>");
			pw.print("</tr>");
			
			
			pw.print("<tr><td><label><b> Rate This Person(Just For Fun): </b></label></td>");
			pw.print("<td>");
			pw.print("<select name='reviewrating' class='uname'>");
			pw.print("<option value='1' selected>1</option>");
			pw.print("<option value='2'>2</option>");
			pw.print("<option value='3'>3</option>");
			pw.print("<option value='4'>4</option>");
			pw.print("<option value='5'>5</option>");
			pw.print("</td></tr>");
			

			pw.print("<tr>");
			pw.print("<td><label><b> Today's Date: </b></label></td>");
			pw.print("<td> <input type='date' name='reviewdate' class='uname' required='true'> </td>");
			pw.print("</tr>");
			

			pw.print("<tr>");
			pw.print("<td><label><b> Review Text: </b></label></td>");
			pw.print("<td><textarea name='reviewtext' rows='4' cols='50' class='uname' required='true'> </textarea></td></tr></table>");
			
			pw.print(
					"<tr><td colspan='2'><input type='submit' class='lbutton' name='SubmitReview' value='SubmitReview'></td></tr></table>");
			

			pw.print("</div></form>");
			pw.println("</section>");
			utility.printHtml("LeftNav.html");
			utility.printHtml("Footer.html");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

    }
}
