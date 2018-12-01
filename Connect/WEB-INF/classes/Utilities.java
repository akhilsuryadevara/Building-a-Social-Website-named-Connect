
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Utilities extends HttpServlet {

	HttpServletRequest req;
	PrintWriter pw;
	HttpSession session;
	String url;
	Users user;

	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.fetchURL();
		this.session = req.getSession(true);
	}

	public void printHtml(String htmlFile) {
		String result = HtmlToString(htmlFile);

		if (htmlFile == "HeaderLogout.html") {

			if (session.getAttribute("username") != null) {
				String  username = (String) session.getAttribute("username");
				String usertype =(String) session.getAttribute("usertype");
				if(usertype!=null) {
					if(usertype.equals("Admin")) {
						
								result = result + "<li><a href=\"./ManageUser\"><span class=\"glyphicon glyphicon-home\"> Home</span></a></li>";
								result = result + "<li><a href=\"./ManageUser\">ManageUsers</a></li>";
								result = result + "<li><a href=\"./ManagePosts\">ManagePosts</a></li>";
								result = result + "<li><a href=\"./ManageAdvertise\">ManageAdvertise</a></li>";
								result = result + "<li><a href=\"./CommentReport\">CommentReport</a></li>";
								result = result + "<li><a href=\"./DataVisualization\">ReviewChart</a></li>";
								result = result + "<li><a href=\"./UserReport\">UserReport</a></li>";
								result = result + "<li><a href=\"./PostReport\">PostReport</a></li>";
								result =	result + "<li><div class=\"search-box\">\r\n" + 
										"				<form name = \"AutoCompleteServlet\" action=\"AjaxUtilities\">\r\n" + 
										"					<div class=\"row\" name=\"searchbar\">\r\n" + 
										"						<div class=\"col-md-6 no-padding\">\r\n" + 
										"							<input\r\n" + 
										"								type=\"text\"\r\n" + 
										"								name=\"searchid\"\r\n" + 
										"								id= \"searchid\"\r\n" + 
										"								placeholder=\"Find Users Here..\"\r\n" + 
										"								onkeyup=\"doCompletion()\"\r\n" + 
										"								class=\"search-input\"\r\n" + 
										"								style=\"width: 200px;margin-top: 10px;margin-left: 10px;\"/>\r\n" + 
										"							\r\n" + 
										"							<div id=\"auto-row\">\r\n" + 
										"								<table\r\n" + 
										"									id=\"complete-table\"\r\n" + 
										"									class=\"gridtable\"\r\n" + 
										"									style=\"position: absolute; width: 100%\"></table>\r\n" + 
										"							</div>\r\n" + 
										"						</div>\r\n" + 
										"					</div>\r\n" + 
										"				</form>\r\n" + 
										"			</div></li>";
						
						result = result + "<li class=\"login\"><a href=\"./LogoutServlet\">Hi "+username+", Logout</a></li>";
					}else if(usertype.equals("User")) {
						result = result + "<li><a href=\"./Home\"><span class=\"glyphicon glyphicon-home\"> Home</span></a></li>";
						result = result + "<li class=\"\"><a href=\"./UpdateProfile\"><i class=\"fa fa-cog\"></i> My Profile</a></li>";
						result = result + "<li><a href=\"./Followers\">Followers</a></li>";
						result = result + "<li><a href=\"./Following\">Following</a></li>";
						result = result + "<li><a href=\"./FollowUser\">Follow Friends</a></li>";
						result = result + "<li><a href=\"./ShowPost\">Add Post</a></li>";
						result =	result + "<li><a href=\"./TrendingTweets\"><i class=\"fa fa-globe\"></i>Trending Tweets</a></li>";
						
						result =	result + "<li><div class=\"search-box\">\r\n" + 
								"				<form name = \"AutoCompleteServlet\" action=\"AjaxUtilities\">\r\n" + 
								"					<div class=\"row\" name=\"searchbar\">\r\n" + 
								"						<div class=\"col-md-6 no-padding\">\r\n" + 
								"							<input\r\n" + 
								"								type=\"text\"\r\n" + 
								"								name=\"searchid\"\r\n" + 
								"								id= \"searchid\"\r\n" + 
								"								placeholder=\"Find Friends Here..\"\r\n" + 
								"								onkeyup=\"doCompletion()\"\r\n" + 
								"								class=\"search-input\"\r\n" + 
								"								style=\"width: 200px;margin-top: 10px;margin-left: 10px;\"/>\r\n" + 
								"							\r\n" + 
								"							<div id=\"auto-row\">\r\n" + 
								"								<table\r\n" + 
								"									id=\"complete-table\"\r\n" + 
								"									class=\"gridtable\"\r\n" + 
								"									style=\"position: absolute; width: 100%\"></table>\r\n" + 
								"							</div>\r\n" + 
								"						</div>\r\n" + 
								"					</div>\r\n" + 
								"				</form>\r\n" + 
								"			</div></li>";
						result = result + "<li class=\"login\"><a href=\"./LogoutServlet\">Hi "+username+", Logout</a></li>";
						
					}
				}
			}
			result = result + "</ul>"
					+ "</nav>";
			pw.print(result);
		}else if(htmlFile == "LeftNav.html"){
			if (session.getAttribute("username") != null) {
				String usertype =(String) session.getAttribute("usertype");
				if(usertype!=null) {
					if(usertype.equals("User")) {
						
						result = result + "<li>";
						result = result + "<h4>Find By City</h4>";
						result = result + "<ul>";
						result = result + "<li id=\"first\"><a href=\"./FollowUser?name=Chicago\">Chicago</a></li>";
						result = result + "<li><a href=\"./FollowUser?name=New York\">New York</a></li>";
						result = result + "<li><a href=\"./FollowUser?name=San Francisco\">San Francisco</a></li>";
						result = result + "<li><a href=\"./FollowUser?name=Dallas\">Dallas</a></li>";
						result = result + "<li><a href=\"./FollowUser?name=Boston\">Boston</a></li>";
						result = result + "<li><a href=\"./FollowUser?name=Seattle\">Seattle</a></li>";
						result = result + "<li><a href=\"./FollowUser?name=Phoenix\">Phoenix</a></li>";
						
						result = result + "</ul>";
						result = result + "</li>";
						
						result = result + "<li>";
						result = result + "<h4>Find By School</h4>";
						result = result + "<ul>";
						result = result + "<li id=\"first\"><a href=\"FollowUser?name=IIT\">Illinois Institute of Tectnology</a></li>";
						result = result + "<li><a href=\"FollowUser?name=CSU\">California State University</a></li>";
						result = result + "<li><a href=\"FollowUser?name=ASU\">Arizona State University</a></li>";
						
						result = result + "</ul>";
						result = result + "</li>";
						
						result = result + "<table  class='table' style='border:double'>";
						result = result + "<th><h4>Advertises</h4></th>";
						
						HashMap<String,String> hm = new HashMap<String,String>(); 
						hm = MySQLDataStoreUtilities.getAllAds();
						
						for(Map.Entry<String, String> entry : hm.entrySet())
						{
							result = result + "<tr style='border:inset'>";
							result = result + "<td><a href='"+entry.getKey()+"'><img src='Html/images/advertise/"+entry.getValue()+"' width='300' height='200'></a></td>";
							result = result + "</tr>";
							result = result + "<tr></tr>";
						}

						result = result + "</table>";
						
					}else{
					
						
						result = result + "<li>";
						result = result + "<h4>Find By City</h4>";
						result = result + "<ul>";
						result = result + "<li id=\"first\"><a href=\"./ManageUser?name=Chicago\">Chicago</a></li>";
						result = result + "<li><a href=\"./ManageUser?name=New York\">New York</a></li>";
						result = result + "<li><a href=\"./ManageUser?name=San Francisco\">San Francisco</a></li>";
						result = result + "<li><a href=\"./ManageUser?name=Dallas\">Dallas</a></li>";
						result = result + "<li><a href=\"./ManageUser?name=Boston\">Boston</a></li>";
						result = result + "<li><a href=\"./ManageUser?name=Seattle\">Seattle</a></li>";
						result = result + "<li><a href=\"./ManageUser?name=Phoenix\">Phoenix</a></li>";
						
						result = result + "</ul>";
						result = result + "</li>";
						
						result = result + "<li>";
						result = result + "<h4>Find By School</h4>";
						result = result + "<ul>";
						result = result + "<li id=\"first\"><a href=\"ManageUser?name=IIT\">Illinois Institute of Tectnology</a></li>";
						result = result + "<li><a href=\"ManageUser?name=CSU\">California State University</a></li>";
						result = result + "<li><a href=\"ManageUser?name=ASU\">Arizona State University</a></li>";
						
						result = result + "</ul>";
						result = result + "</li>";
					}
				}
				
				
				result = result + "</ul></li></ul></aside>";
				result = result + "<div class=\"clear\"></div></div>";
				pw.print(result);
			}else {
			
			pw.print(result);
		}
	}else
		pw.print(result);
	}
			
	public String fetchURL() {
		String scheme = req.getScheme();
		String server = req.getServerName();
		int port = req.getServerPort();
		String path = req.getContextPath();
		StringBuffer sb = new StringBuffer();
		sb.append(scheme).append("://").append(server);
		if ((port != 80)) {
			sb.append(":").append(port);
		}
		sb.append(path).append("/Html/");
		return sb.toString();
	}

	public String HtmlToString(String htmlFile) {
		String text = null;
		String newPage = url + htmlFile;
		try {
			URL url = new URL(newPage);
			URLConnection connection = url.openConnection();
			InputStream in = connection.getInputStream();
			InputStreamReader inr = new InputStreamReader(in);
			int readChars;
			char[] arr = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((readChars = inr.read(arr)) > 0) {
				sb.append(arr, 0, readChars);
			}

			text = sb.toString();
			//pw.println(text);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return text;
	}

	public boolean isLoggedin() {
		if (session.getAttribute("username") == null)
			return false;
		return true;
	}

	public String username() {
		if (session.getAttribute("username") != null)
			return session.getAttribute("username").toString();
		return null;
	}


	
	public String storeReview(String reviewforuser, String userfullname, String currentuser, 
			String userAge, String userGender, String userOccupation, String userpin, String usercity, 
			String reviewrating, String reviewDate, String reviewText) {
        String message = MongoDBDataStoreUtilities.insertReview(reviewforuser, userfullname, currentuser, userAge, userGender, userOccupation, userpin, usercity, reviewrating, reviewDate, reviewText);
        if (!message.equals("Successful")) {
            return "UnSuccessful";
        } else {
            HashMap<String, ArrayList<Review>> reviews = new HashMap<String, ArrayList<Review>>();
            try {
                reviews = MongoDBDataStoreUtilities.selectReview();
            } catch (Exception e) {

            }
            if (reviews == null) {
                reviews = new HashMap<String, ArrayList<Review>>();
            }
            // if there exist product review already add it into same list for productname or create a new record with product name

            if (!reviews.containsKey(reviewforuser)) {
                ArrayList<Review> arr = new ArrayList<Review>();
                reviews.put(reviewforuser, arr);
            }
            ArrayList<Review> listReview = reviews.get(reviewforuser);
            Review review = new Review(reviewforuser, userfullname, currentuser, userAge, userGender, userOccupation, userpin, usercity, reviewrating, reviewDate, reviewText);
            listReview.add(review);

            // add Reviews into database

            return "Successful";
        }
    }
}