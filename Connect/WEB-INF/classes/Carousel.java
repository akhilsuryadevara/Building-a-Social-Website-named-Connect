
  

import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;	

			
			
public class Carousel{
			
	public String  carouselfeature(Utilities utility){
		
		
		
		HashMap<String, Users> hm = new HashMap<String, Users>();
		HashMap<String, Users> hmfollowing = new HashMap<String, Users>();
		StringBuilder sb = new StringBuilder();
		String myCarousel = null;
			
		String name = null;
		String CategoryName = null;
		String username=utility.username();
		Users user = MySQLDataStoreUtilities.getProfile(username);
		if(CategoryName==null){
			try{
			hm=MySQLDataStoreUtilities.getAllUsersFromCity(username,user.getCity());
			hm.remove(username);
			hmfollowing = MySQLDataStoreUtilities.getFollowings(username);
			for (Map.Entry<String, Users> entry : hmfollowing.entrySet()) {
				hm.remove(entry.getKey());
			}
			name = "";
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
				sb.append("<div><div class='post'><h2 class='title meta'>");
				sb.append("<a style='font-size: 24px;'>"+""+" Recommended Friends from your City</a>");
				sb.append("</h2>");
				sb.append("<div>");
				
				sb.append("<div id=\""+myCarousel+"\" class=\"carousel slide\" data-ride=\"carousel\">");
				sb.append("<ol class=\"carousel-indicators\"> ");
				sb.append("<li data-target=\"#myCarousel\" data-slide-to=\"0\" class=\"active\"></li>  ");
				sb.append("<li data-target=\"#myCarousel\" data-slide-to=\"1\"></li>  ");
				sb.append("<li data-target=\"#myCarousel\" data-slide-to=\"2\"></li>  ");
				sb.append("</ol>  ");
				
				sb.append("<div class=\"carousel-inner\" role=\"listbox\">  ");

				int k = 0;
				for (Map.Entry<String, Users> entry : hm.entrySet()) {
					Users usr = (Users) entry.getValue();
					if (k==0 )
					{
						
						sb.append("<div class='item active' align=\"center\">");
					}
					else
					{
						sb.append("<div class='item' align=\"center\">");
					}
					
					sb.append("<div class=\"item\" align=\"center\"><table id='bestseller'>  ");
					
					sb.append("<tr>");
					sb.append("<td><div id='follower_item' style='text-align: center'>");
					sb.append("<h3 style='text-align: center'>"+usr.getUsername()+"</h3>");
					sb.append("<strong style='text-align: center'>"+usr.getFirstname()+   usr.getLastname()+"</strong><ul>");
					sb.append("<li id='item'><img src='Html/images/people/"+usr.getProfilepic()+"' alt='' /></li>");
					sb.append("<li><form method='post' action='SaveFollowUser'>" +
							"<input type='hidden' name='username' value='"+entry.getKey()+"'>"+
							"<input type='hidden' name='actualname' value='"+usr.getUsername()+"'>"+
							"<input type='submit' class='lbutton' value='Follow'></form></li>");
					sb.append("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='username' value='"+entry.getKey()+"'>"+
						    "<input type='submit' value='Write Testimonial' class='lbutton'></form></li>");
					sb.append("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='username' value='"+entry.getKey()+"'>"+
						    "<input type='submit' value='View Testimonial' class='lbutton'></form></li>");
					sb.append("</ul></div></td>");
					sb.append("</tr></table>");
					sb.append("</div>");
					sb.append("</div>");
				
					k++;
					
				}
			
				sb.append(
						"<a class=\"left carousel-control\" href=\"#"+myCarousel+"\" role=\"button\" data-slide=\"prev\">  ");
				sb.append("<span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span>  ");
				sb.append("<span class=\"sr-only\">Previous</span>  ");
				sb.append("</a>  ");
				sb.append(
						"<a class=\"right carousel-control\" href=\"#"+myCarousel+"\" role=\"button\" data-slide=\"next\">  ");
				sb.append("<span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span>  ");
				sb.append("<span class=\"sr-only\">Next</span>  ");
				sb.append("</a> ");
				sb.append("</div></div>");
				sb.append("</div></div>");
				sb.append("</div>");
			
			return sb.toString();
		}
	}
	