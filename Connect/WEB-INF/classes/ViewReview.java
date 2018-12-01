

import java.io.IOException;
import java.io.PrintWriter;

import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@WebServlet("/ViewReview")

public class ViewReview extends HttpServlet {

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
            if (!utility.isLoggedin()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("login_msg", "Please Login to view Review");
                response.sendRedirect("Login");
                return;
            }
            String username = request.getParameter("username");
            HashMap<String, ArrayList<Review>> hm = MongoDBDataStoreUtilities.selectReview();
            String reviewRating = "";
            String reviewDate;
            String reviewText = "";
            String price = "";
            String city = "";
            String userAge = "";
            String userGender = "";
            String userOccupation = "";
            String pin = "";

            if (utility.isLoggedin())
    			utility.printHtml("HeaderLogout.html");
    		else
    			utility.printHtml("Header.html");
    		pw.println("<section id=\"content\">");
    	
    		pw.print("<div class='box'>");
    		pw.print("<div class='box-header'>");
    		pw.print("<h3 class='box-title' >Testimonial Reviews</h3>");
    		pw.print("</div>");

            if (hm == null) {
                pw.println("<h2>Mongo Db server is not up and running</h2>");
            } else {
                if (!hm.containsKey(username)) {
                    pw.println("<h2>There are no testimonials.</h2>");
                } else {
                    for (Review r : hm.get(username)) {
                    	
                    	
                    	pw.print("<table  class='table' style='border:double'>");
                    	
        				pw.print("<tr>");
        				pw.print("<td><label><b> User Name: </b></label></td>");
        				username = r.getUserfullname();
        				pw.print("<td>" +username+ "</td>");
        				pw.print("</tr>");
                    	
        				
        				pw.print("<tr>");
        				pw.print("<td><label><b> User Age: </b></label></td>");
        				userAge = r.getUserAge();
        				pw.print("<td>" +userAge+ "</td>");
        				pw.print("</tr>");
        				
        				pw.print("<tr>");
        				pw.print("<td><label><b> User Gender: </b></label></td>");
        				userGender = r.getUserGender();
        				pw.print("<td>" +userGender+ "</td>");
        				pw.print("</tr>");
        				
        				pw.print("<tr>");
        				pw.print("<td><label><b> User Occupation: </b></label></td>");
        				 userOccupation = r.getUserOccupation();
        				pw.print("<td>" +userOccupation+ "</td>");
        				pw.print("</tr>");
        				
        				pw.print("<tr>");
        				pw.print("<td><label><b> User City: </b></label></td>");
        				city = r.getUsercity();
        				pw.print("<td>" +city+ "</td>");
        				pw.print("</tr>");
        				
        				
        				pw.print("<tr>");
        				pw.print("<td><label><b> User Zipcode: </b></label></td>");
        				pin = r.getUserpin();
        				pw.print("<td>" +pin+ "</td>");
        				pw.print("</tr>");
        				
        				
        				pw.print("<tr>");
        				pw.print("<td><label><b> User Ratings: </b></label></td>");
        				reviewRating = r.getReviewrating();
        				pw.print("<td>" +reviewRating+ "</td>");
        				pw.print("</tr>");
        				

        				pw.print("<tr>");
        				pw.print("<td><label><b> Date of Testimonial given: </b></label></td>");
        				reviewDate = r.getReviewDate();
        				pw.print("<td>" +reviewDate+ "</td>");
        				pw.print("</tr>");
        				
        				pw.print("<tr>");
        				pw.print("<td><label><b> About this Person: </b></label></td>");
        				 reviewText = r.getReviewText();
        				pw.print("<td>" +reviewText+ "</td>");
        				pw.print("</tr>");
        				
                        pw.println("</table>");
                    }

                }
            }
            pw.print("</div>");
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
