import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDBDataStoreUtilities {
	static DBCollection myReviews;
	static DBCollection Comments;
	public static DBCollection getConnection()
	{
		
		MongoClient mongo;
        mongo = new MongoClient("localhost", 27017);

        DB db = mongo.getDB("connectnetwork");
        Comments = db.getCollection("Comments");
		return Comments; 
	}
	public static DBCollection getMyReviewConnection()
	{
		
		MongoClient mongo;
        mongo = new MongoClient("localhost", 27017);

        DB db = mongo.getDB("connectnetwork");
        myReviews = db.getCollection("myReviews");
		return myReviews; 
	}
	
	 public static int getCommentsCount(String username) {
			String previousComments[] = null;
			getConnection();
	        int i=0;
	        	previousComments=new String[(int) Comments.count()];
	        	 DBCursor cursor = Comments.find();
	             while (cursor.hasNext())
	             {
	                 BasicDBObject obj = (BasicDBObject) cursor.next();
	                 if (username.equals(obj.getString("username"))) {
	                	 i=i+1;
	                 }
	        	 }
	            return i;
		}
	 
	 public static String[] getPreviousComments(String postId, String username) {
			String previousComments[] = null;
			getConnection();
	        int i=0;
	        if(Comments.count()==0)
	        	return previousComments;
	        else {
	        	previousComments=new String[(int) Comments.count()];
	        	 DBCursor cursor = Comments.find();
	             while (cursor.hasNext()) {
	                 BasicDBObject obj = (BasicDBObject) cursor.next();
	                 if (postId.equals(obj.getString("postId"))) {
	                	 previousComments[i]=obj.getString("commentText");
	                	 i=i+1;
	                 }
	             }
	        	return previousComments;
	        	}
		}
	 public static String insertComment(String postId, String username, String commentText,String commentTime) {
			try {
				getConnection();
				BasicDBObject doc = new BasicDBObject("title", "Comments").append("postId", postId)
						.append("username", username).append("commentText", commentText)
						.append("commentTime", commentTime);
				Comments.insert(doc);
				return "Successful";
			} catch (Exception e) {
				return "UnSuccessful";
			}

		}
	 public static HashMap<String, ArrayList<Review>> selectReview() {
	        HashMap<String, ArrayList<Review>> reviews = null;

	        try {

	        	getMyReviewConnection();
	            DBCursor cursor = myReviews.find();
	            reviews = new HashMap<String, ArrayList<Review>>();
	            while (cursor.hasNext()) {
	                BasicDBObject obj = (BasicDBObject) cursor.next();

	                if (!reviews.containsKey(obj.getString("reviewforuser"))) {
	                    ArrayList<Review> arr = new ArrayList<Review>();
	                    reviews.put(obj.getString("reviewforuser"), arr);
	                }
	                ArrayList<Review> listReview = reviews.get(obj.getString("reviewforuser"));
	                Review review = new Review(obj.getString("reviewforuser"), obj.getString("userfullname"), obj.getString("currentuser"),
	                        obj.getString("userAge"), obj.getString("userGender"), obj.getString("userOccupation"), obj.getString("userpin"),
	                        obj.getString("usercity"), obj.getString("reviewrating"), obj.getString("reviewDate"), obj.getString("reviewText"));
	                listReview.add(review);
	            }
	            return reviews;
	        } catch (Exception e) {
	            reviews = null;
	            return reviews;
	        }
	 }
	 
	    public static String insertReview(String reviewforuser, String userfullname, String currentuser, 
				String userAge, String userGender, String userOccupation, String userpin, String usercity, 
				String reviewrating, String reviewDate, String reviewText) {
	        try {
	        	getMyReviewConnection();
	            BasicDBObject doc = new BasicDBObject("title", "myReviews").
	                    append("reviewforuser", reviewforuser).
	                    append("userfullname", userfullname).
	                    append("currentuser", currentuser).
	                    append("userAge", userAge).
	                    append("userGender", userGender).
	                    append("userOccupation", userOccupation).
	                    append("userpin", userpin).
	                    append("usercity", usercity).
	                    append("reviewrating", Integer.parseInt(reviewrating)).
	                    append("reviewDate", reviewDate).
	                    append("reviewText", reviewText);
	                    
	                   
	            myReviews.insert(doc);
	            return "Successful";
	        } catch (Exception e) {
	            return "UnSuccessful";
	        }

	    }
		public static ArrayList<Review> selectReviewForChart() {

			
	        ArrayList<Review> reviewList = new ArrayList<Review>();
	        try {

	        	getMyReviewConnection();
	            Map<String, Object> dbObjIdMap = new HashMap<String, Object>();
	            dbObjIdMap.put("userpin", "$userpin");
	            dbObjIdMap.put("userfullname", "$userfullname");
	            DBObject groupFields = new BasicDBObject("_id", new BasicDBObject(dbObjIdMap));
	            groupFields.put("count", new BasicDBObject("$sum", 1));
	            DBObject group = new BasicDBObject("$group", groupFields);

	            DBObject projectFields = new BasicDBObject("_id", 0);
	            projectFields.put("userpin", "$_id");
	            projectFields.put("userfullname", "$userfullname");
	            projectFields.put("reviewCount", "$count");
	            DBObject project = new BasicDBObject("$project", projectFields);

	            DBObject sort = new BasicDBObject();
	            sort.put("reviewCount", -1);

	            DBObject orderby = new BasicDBObject();            
	            orderby = new BasicDBObject("$sort",sort);
	            

	            AggregationOutput aggregate = myReviews.aggregate(group, project, orderby);

	            for (DBObject result : aggregate.results()) {

	                BasicDBObject obj = (BasicDBObject) result;
	                Object o = com.mongodb.util.JSON.parse(obj.getString("userpin"));
	                BasicDBObject dbObj = (BasicDBObject) o;
	                Review review = new Review(dbObj.getString("userfullname"), dbObj.getString("userpin"),
	                        obj.getString("reviewCount"), null);
	                reviewList.add(review);
	                
	            }
	            return reviewList;

	        }

	        catch (

	        Exception e) {
	            reviewList = null;
	            
	            return reviewList;
	        }

	    }
}
