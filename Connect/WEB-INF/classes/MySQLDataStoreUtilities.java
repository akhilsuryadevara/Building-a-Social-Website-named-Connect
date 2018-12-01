import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MySQLDataStoreUtilities extends HttpServlet {

	static Connection conn = null;

	public static String getConnection()
	{

		System.out.println("Inside getConnection");
		 try {
	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/connectnetwork?useUnicode=true&characterEncoding=utf8", "root", "ashish");
	            return "successfull";
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            return "unsuccessfull";
	        }
	}
	
	public static boolean insertUser(String userName, String firstName, String lastName,String email,String password,String repassword,String birthday,String gender,String profilepic, String city, String school, String userType) {
        try {

            getConnection();
            String insertIntoCustomerRegisterQuery = "INSERT INTO registration(username,firstname,lastname,email,password,repassword,birthday,gender,profilepic,city,school,usertype) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
            pst.setString(1, userName);
            pst.setString(2, firstName);
            pst.setString(3, lastName);
            pst.setString(4, email);
			pst.setString(5, password);
			pst.setString(6, repassword);
			pst.setString(7, birthday);
			pst.setString(8, gender);
			pst.setString(9, profilepic);
			pst.setString(10, city);
			pst.setString(11, school);			
			pst.setString(12, userType);
				  
            pst.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
	public static boolean insertFollow(String username, String following) {
		try {
			getConnection();
			String insertIntoTable = "INSERT INTO follow(username,following)" + "VALUES (?,?);";
			 PreparedStatement pst = conn.prepareStatement(insertIntoTable);
			 pst.setString(1, username);
			 pst.setString(2, following);
			 pst.execute();
		} catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
		return true;
	}
	
	 public static HashMap<String, Users> selectUser() {
	        HashMap<String, Users> hm = new HashMap<String, Users>();
	        try {
	            getConnection();
	            Statement stmt = conn.createStatement();
	            String selectCustomerQuery = "select * from registration";
	            ResultSet rs = stmt.executeQuery(selectCustomerQuery);
	            while (rs.next()) {
	            	Users user = new Users(rs.getString("username"), rs.getString("password"), rs.getString("usertype"));
	                hm.put(rs.getString("username")+rs.getString("usertype"), user);
	            }
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        return hm;
	    }
	 
	 public static HashMap<String, Users> getPeopleToFollow(String username)
	 {	
	 	HashMap<String, Users> hm = new HashMap<String, Users>();
	         try {
	         	getConnection();
	   			Statement stmt=conn.createStatement();
	   			String users[];int i=1;
	   			
	   			String selectFollowingQuery="select * from  Follow where username=\""+username+"\" ;";
	   			ResultSet Followrs = stmt.executeQuery(selectFollowingQuery);
	   			 int rowcount=0;
	   			if (Followrs.last()) {
	   			   rowcount = Followrs.getRow();
	   			Followrs.beforeFirst();
	   			}
	   			users=new String[rowcount+1];
	   			users[0]=username;
	   			while(Followrs.next())
	   			{	
	   					users[i]=Followrs.getString("following");
	   					i++;
	   			}
	   			
	 	  			String selectCustomerQuery="select * from  registration where userType!=\"Admin\" ;";
	 	  			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
	 	  			while(rs.next())
	 	  			{	Users user = new Users(rs.getString("userName"),rs.getString("firstName"),rs.getString("lastName"),rs.getString("email"),rs.getString("password"),
	 	  					rs.getString("repassword"),rs.getString("birthday"),rs.getString("gender"),rs.getString("profilepic"),rs.getString("city")
	 	  					,rs.getString("school"),rs.getString("userType"));
	 	  					hm.put(rs.getString("userName"), user);
	 	  			}
	 	  			for(i=0;i<users.length;i++) {
	 	  				hm.remove(users[i]);
	 	  			}
	         } catch (Exception e) {
	             System.out.println(e.getMessage());
	         }
	         return hm;	
	 }
	
	 public static void AddPost(String username, String posttext,String postImage,String date,int PostLikeCount,int PostCommentCount)
	  	{	
	  		System.out.println("Inside AddPost");
	  		try
	  		{	

	  			System.out.println("Inside insertUser");
	  			getConnection();
	  			String insertIntoCustomerRegisterQuery = "INSERT INTO Post(UserName,PostText,PostImage,PostDate,PostLikeCount,PostCommentCount) "
	  			+ "VALUES (?,?,?,?,?,?);";
	  			PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
	  			pst.setString(1,username);
	  			pst.setString(2,posttext);
	  			pst.setString(3,postImage);
	  			pst.setString(4,date);
	  			pst.setInt(5,PostLikeCount);
	  			pst.setInt(6,PostCommentCount);
	  			
	  			
	  			pst.execute();
	  			conn.close();
	  		}
	  		catch(Exception e)
	  		{
	  		e.printStackTrace();
	  		}
	  	}
	  	
	  	public static HashMap<String,Post> getAllUsersPost(String username)
	  	{	
	  		System.out.println("Inside selectUser");
	  		HashMap<String,Post> hm=new HashMap<String,Post>();
	  		try 
	  		{
	  			getConnection();
	  			Statement stmt=conn.createStatement();
	  			String users[] = new String[100];int i=0;
	  			String selectFollowingQuery="select * from  Follow where username=\""+username+"\" ;";
	  			users[i]=username;
	  			i++;
	  			ResultSet Followrs = stmt.executeQuery(selectFollowingQuery);
	  			while(Followrs.next())
	  			{	
	  					users[i]=Followrs.getString("following");
	  					i++;
	  			}
	  			for(i=0;i<users.length;i++) {
		  			String selectCustomerQuery="select * from  Post where UserName=\""+users[i]+"\" order by Postdate desc;";
		  			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		  			while(rs.next())
		  			{	Post post = new Post(rs.getInt("PostId"),rs.getString("UserName"),rs.getString("PostText"),rs.getString("PostImage"),rs.getString("PostDate"),
		  					rs.getInt("PostLikeCount"),rs.getInt("PostCommentCount"));
		  					hm.put(rs.getString("PostId")+rs.getString("UserName"), post);
		  			}
	  			}
	  			conn.close();
	  		}
	  		catch(Exception e)
	  		{
	  			e.printStackTrace();
	  		}
	  		return hm;			
	  	}

	  	public static Users getProfile(String username)
	  	{	
	  		System.out.println("Inside updateProfile");
	  		Users user = null;
	  		try 
	  		{
	  			getConnection();
	  			Statement stmt=conn.createStatement();
	  			String selectCustomerQuery="select * from  registration where UserName=\""+username+"\" ;";
	  			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
	  			while(rs.next())
	  			{	user = new Users(rs.getString("userName"),rs.getString("firstName"),rs.getString("lastName"),rs.getString("email"),rs.getString("password"),
	  					rs.getString("repassword"),rs.getString("birthday"),rs.getString("gender"),rs.getString("profilepic"),rs.getString("city"),rs.getString("school"),rs.getString("userType"));
	  			
	  			}
	  			conn.close();
	  		}
	  		catch(Exception e)
	  		{
	  			e.printStackTrace();
	  		}
	  		return user;			
	  	}

	  	public static Users updateProfile(String username,String firstname, String lastname,String email, String bday, String gender, String pic, String city, String school)
	  	{	
	  		System.out.println("Inside updateProfile");
	  		Users user = null;
	  		try 
	  		{
	  			getConnection();
	  			String updateProductQurey = "UPDATE registration SET firstName=?,lastName=?,email=?,birthday=?,gender=?,profilepic=?,city=?,school=? where userName =?;" ;

				PreparedStatement pst = conn.prepareStatement(updateProductQurey);

				pst.setString(1,firstname);
				pst.setString(2,lastname);
				pst.setString(3,email);
				pst.setString(4,bday);
				pst.setString(5,gender);
				pst.setString(6,pic);
				pst.setString(7,city);
				pst.setString(8,school);
				pst.setString(9,username);
				pst.executeUpdate();

	  		}
	  		catch(Exception e)
	  		{
	  			e.printStackTrace();
	  		}
	  		return user;			
	  	}
	  	
	  	public static void changePassword(String username,String password, String repassword)
	  	{	
	  		System.out.println("Inside changePassword");
	  		try 
	  		{
	  			getConnection();
	  			String updateProductQurey = "UPDATE registration SET password=?,repassword=? where userName =?;" ;

				PreparedStatement pst = conn.prepareStatement(updateProductQurey);

				pst.setString(1,password);
				pst.setString(2,repassword);
				pst.setString(3,username);
				pst.executeUpdate();

	  		}
	  		catch(Exception e)
	  		{
	  			e.printStackTrace();
	  		}
	  					
	  	}


		public static HashMap<String, Users> getFollowings(String username) {
			HashMap<String,Users> hm=new HashMap<String,Users>();
			try 
	  		{
	  			getConnection();
	  			Statement stmt=conn.createStatement();
	  			String users[] = new String[100];int i=0;
	  			String selectFollowingQuery="select * from  Follow where username=\""+username+"\" ;";
	  			ResultSet Followrs = stmt.executeQuery(selectFollowingQuery);
	  			while(Followrs.next())
	  			{	
	  					users[i]=Followrs.getString("following");
	  					i++;
	  			}
	  			for(i=0;i<users.length;i++) {
		  			String selectCustomerQuery="select * from  registration where UserName=\""+users[i]+"\" ;";
		  			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		  			while(rs.next())
		  			{	Users user = new Users(rs.getString("userName"),rs.getString("firstName"),rs.getString("lastName"),rs.getString("email"),rs.getString("password"),
		  					rs.getString("repassword"),rs.getString("birthday"),rs.getString("gender"),rs.getString("profilepic"),rs.getString("city")
		  					,rs.getString("school"),rs.getString("userType"));
		  					hm.put(rs.getString("userName"), user);
		  			}
	  			}
	  			conn.close();
	  		}
	  		catch(Exception e)
	  		{
	  			e.printStackTrace();
	  		}
			return hm;
		}


		public static int removeFollower(String follower,String username) {
			int rs= 0;
			try
			{	
			getConnection();
	        //select the table 
			String selectOrderQuery = new String();
			selectOrderQuery ="Delete from follow where username = \""+follower+ "\" and following=\""+username+"\";";
			PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
			rs = pst.executeUpdate();
			conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			return rs;
		}


		public static HashMap<String, Users> getFollowers(String username) {
			HashMap<String,Users> hm=new HashMap<String,Users>();
			try 
	  		{
	  			getConnection();
	  			Statement stmt=conn.createStatement();
	  			String users[] = new String[100];int i=0;
	  			String selectFollowingQuery="select * from  Follow where following=\""+username+"\" ;";
	  			ResultSet Followrs = stmt.executeQuery(selectFollowingQuery);
	  			while(Followrs.next())
	  			{	
	  					users[i]=Followrs.getString("username");
	  					i++;
	  			}
	  			for(i=0;i<users.length;i++) {
		  			String selectCustomerQuery="select * from  registration where UserName=\""+users[i]+"\" ;";
		  			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		  			while(rs.next())
		  			{	Users user = new Users(rs.getString("userName"),rs.getString("firstName"),rs.getString("lastName"),rs.getString("email"),rs.getString("password"),
		  					rs.getString("repassword"),rs.getString("birthday"),rs.getString("gender"),rs.getString("profilepic"),rs.getString("city")
		  					,rs.getString("school"),rs.getString("userType"));
		  					hm.put(rs.getString("userName"), user);
		  			}
	  			}
	  			conn.close();
	  		}
	  		catch(Exception e)
	  		{
	  			e.printStackTrace();
	  		}
			return hm;
		}


		public static int removeFollowing(String following,String username) {
			int rs= 0;
			try
			{	
			getConnection();
	        //select the table 
			String selectOrderQuery = new String();
			selectOrderQuery ="Delete from follow where following = \""+following+ "\" and username=\""+username+"\";";
			PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
			rs = pst.executeUpdate();
			conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			return rs;
		}
		
		public static void deletePost(String postId) {
			System.out.println("Inside deletepost: ");
			try
			{	
			getConnection();
	        //select the table 
			String selectOrderQuery = new String();
			selectOrderQuery ="Delete from Post where PostId ='"+postId+"';";
			PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
			pst.executeUpdate();
			conn.close();
			System.out.println("after deletepost: ");
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		public static HashMap<String,Post> getAllPosts()
	  	{	
	  		System.out.println("Inside selectUser");
	  		HashMap<String,Post> hm=new HashMap<String,Post>();
	  		try 
	  		{
	  			getConnection();
	  			Statement stmt=conn.createStatement();
		  			String selectCustomerQuery="select * from  Post;";
		  			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		  			while(rs.next())
		  			{	Post post = new Post(rs.getInt("PostId"),rs.getString("UserName"),rs.getString("PostText"),rs.getString("PostImage"),rs.getString("PostDate"),
		  					rs.getInt("PostLikeCount"),rs.getInt("PostCommentCount"));
		  					hm.put(rs.getString("PostId"), post);
		  			}
	  			conn.close();
	  		}
	  		catch(Exception e)
	  		{
	  			e.printStackTrace();
	  		}
	  		return hm;			
	  	}
		
	  	public static List<String> getAllUserNames()
	  	{	
	  		System.out.println("Inside selectUser");
	  		List<String> list = new ArrayList<String>();
	  		String usertype = "User";
	  		try 
	  		{
	  			getConnection();
	  			Statement stmt=conn.createStatement();
		  			String selectCustomerQuery="select * from  registration where userType='"+usertype+"';";
		  			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		  			while(rs.next())
		  			{	
		  					list.add(rs.getString("userName"));
		  			}
	  			conn.close();
	  		}
	  		catch(Exception e)
	  		{
	  			e.printStackTrace();
	  		}
	  		return list;			
	  	}
	  	
	  	public static int getPostCount(String username)
	  	{	
	  		System.out.println("Inside selectUser");
	  		int count =0;
	  		try 
	  		{
	  			getConnection();
	  			Statement stmt=conn.createStatement();
		  			String selectCustomerQuery="select COUNT(*) as count from Post where UserName='"+username+"';";
		  			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		  			while(rs.next())
		  			{	
		  					count = rs.getInt("count");
		  			}
	  			conn.close();
	  		}
	  		catch(Exception e)
	  		{
	  			e.printStackTrace();
	  		}
	  		return count;			
	  	}
	  	
	  	public static HashMap<String,String> getUsersByCity()
	  	{	
	  		System.out.println("Inside selectUser");
	  		HashMap<String,String> list = new HashMap<String,String>();
	  		String usertype = "User";
	  		try 
	  		{
	  			getConnection();
	  			Statement stmt=conn.createStatement();
		  			String selectCustomerQuery="select * from  registration where userType='"+usertype+"' order by city;";
		  			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		  			while(rs.next())
		  			{	
		  				list.put(rs.getString("userName"), rs.getString("city"));	
		  			}
	  			conn.close();
	  		}
	  		catch(Exception e)
	  		{
	  			e.printStackTrace();
	  		}
	  		return list;			
	  	}

		public static HashMap<String, Users> getAllUsers() {
			System.out.println("Inside selectUser");
	  		HashMap<String,Users> list = new HashMap<String,Users>();
	  		String usertype = "Admin";
	  		try 
	  		{
	  			getConnection();
	  			Statement stmt=conn.createStatement();
		  			String selectCustomerQuery="select * from  registration where userType!='"+usertype+"';";
		  			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		  			while(rs.next())
		  			{	
		  				Users user = new Users(rs.getString("userName"),rs.getString("firstName"),rs.getString("lastName"),rs.getString("email"),rs.getString("password"),
		 	  					rs.getString("repassword"),rs.getString("birthday"),rs.getString("gender"),rs.getString("profilepic"),rs.getString("city")
		 	  					,rs.getString("school"),rs.getString("userType"));
		  				list.put(rs.getString("userName"), user);
		  			}
	  			conn.close();
	  		}
	  		catch(Exception e)
	  		{
	  			e.printStackTrace();
	  		}
	  		return list;			
		}

		public static void deleteUser(String username) {
			System.out.println("Inside deleteUser: ");
			try
			{	
			getConnection();
			String selectOrderQuery1 = new String();
			String selectOrderQuery2 = new String();
			String selectOrderQuery3 = new String();
			
			selectOrderQuery1 ="Delete from registration where userName ='"+username+"';";
			selectOrderQuery2 ="Delete from Post where UserName ='"+username+"';";
			selectOrderQuery3 ="Delete from follow where username ='"+username+"';";
			
			PreparedStatement pst = conn.prepareStatement(selectOrderQuery1);
			pst.executeUpdate();
			pst = conn.prepareStatement(selectOrderQuery2);
			pst.executeUpdate();
			pst = conn.prepareStatement(selectOrderQuery3);
			pst.executeUpdate();
			conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		public static HashMap<String, Users> getAllUsersFromCity(String username,String city) {
			HashMap<String,Users> hm=new HashMap<String,Users>();
			try {
			getConnection();
	  			String selectCustomerQuery="select * from  registration where city='"+city+"' and userType!=\"Admin\";";
	  			Statement stmt = conn.createStatement();
	  			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
	  			while(rs.next())
	  			{	Users user = new Users(rs.getString("userName"),rs.getString("firstName"),rs.getString("lastName"),rs.getString("email"),rs.getString("password"),
	  					rs.getString("repassword"),rs.getString("birthday"),rs.getString("gender"),rs.getString("profilepic"),rs.getString("city")
	  					,rs.getString("school"),rs.getString("userType"));
	  					hm.put(rs.getString("userName"), user);
	  			}
  			conn.close();
  		}
  		catch(Exception e)
  		{
  			e.printStackTrace();
  		}
		return hm;	
		}

		public static void incrementLikeCount(String postId, String count) {
			int rs=0;
			try
			{	
			getConnection();
			String selectOrderQuery = new String();
			selectOrderQuery ="Update Post set PostLikeCount=\""+Integer.parseInt(count)+"\" where PostId = "+Integer.parseInt(postId)+ ";";
			PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
			rs = pst.executeUpdate();
			conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void AddAdvertise(String posttext,String postImage)
	  	{	
	  		System.out.println("Inside AddAdvertise");
	  		try
	  		{	

	  			//System.out.println("Inside insertUser");
	  			getConnection();
	  			String insertIntoCustomerRegisterQuery = "INSERT INTO Advertise(AdUrl,AdImage) "
	  			+ "VALUES (?,?);";
	  			PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
	  			pst.setString(1,posttext);
	  			pst.setString(2,postImage);
	  			
	  			pst.execute();
	  			conn.close();
	  		}
	  		catch(Exception e)
	  		{
	  		e.printStackTrace();
	  		}
	  	}
	  	
	  	public static HashMap<String,String> getAllAds()
	  	{
	  		HashMap<String, String> hm = new HashMap<String,String>();	
 			try 
 			{
		  		getConnection();
	 			
	 			String selectCustomerQuery="select * from  Advertise";
	  			Statement stmt = conn.createStatement();
	  			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
	
	  			while(rs.next())
	  			{	
	  				hm.put(rs.getString("AdUrl"),rs.getString("AdImage"));
	  			}
				conn.close();
 			}
 			catch(Exception e) {
 				
 			}
	  		return hm;
	  	}
	  	
	  	public static void deleteAdvertise(String image, String url) {
			System.out.println("Inside deleteAdvertise: ");
			try
			{	
			getConnection();
			String selectOrderQuery = new String();
			selectOrderQuery ="Delete from Advertise where AdUrl ='"+url+"' and AdImage ='"+image+"';";
			PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
			pst.executeUpdate();
			conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

}
