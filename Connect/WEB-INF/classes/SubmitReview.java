

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SubmitReview")

public class SubmitReview extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
        storeReview(request, response);
    }

    protected void storeReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            Utilities utility = new Utilities(request, pw);
            if (!utility.isLoggedin()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("login_msg", "Please Login to add items to cart");
                response.sendRedirect("Login");
                return;
            }
            String reviewforuser = request.getParameter("reviewforuser");
            String userfullname = request.getParameter("userfullname");
            String currentuser=utility.username();
            String userAge =  request.getParameter("userAge");
            String userGender =  request.getParameter("userGender");
            String userOccupation =  request.getParameter("userOccupation");
            String userpin = request.getParameter("userpin");
            String usercity = request.getParameter("usercity");
            String reviewrating = request.getParameter("reviewrating");
            String reviewDate = request.getParameter("reviewdate");
            String reviewText = request.getParameter("reviewtext");

            String message = utility.storeReview(reviewforuser, userfullname, currentuser, userAge, userGender, userOccupation, userpin, usercity, reviewrating, reviewDate, reviewText);

            if (utility.isLoggedin())
				utility.printHtml("HeaderLogout.html");
			else
				utility.printHtml("Header.html");
			pw.println("<section id=\"content\">");
			if (message.equals("Successful"))
                pw.print("<h3 style=\"color:green\">Testimonial for &nbsp" + reviewforuser + " Stored. </h3>");
            else
                pw.print("<h3 style=\"color:red\">Testimonial for &nbsp" + reviewforuser + " not Stored. </h3>");
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
