import java.io.InputStream;
import java.sql.Date;

public class Post implements java.io.Serializable  {
	int PostId;
    String UserName ;
    String PostText ;
    String PostImage;
    String PostDate ;
    int PostLikeCount ;
    int PostCommentCount ;
    
   
	

	
	public Post(int postId, String userName, String postText, String postImage, String postDate, int postLikeCount,
			int postCommentCount) {
		super();
		PostId = postId;
		UserName = userName;
		PostText = postText;
		PostImage = postImage;
		PostDate = postDate;
		PostLikeCount = postLikeCount;
		PostCommentCount = postCommentCount;
	}

	public Post()
 {
		super();
	}

	public int getPostId() {
		return PostId;
	}

	public void setPostId(int postId) {
		PostId = postId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPostText() {
		return PostText;
	}

	public void setPostText(String postText) {
		PostText = postText;
	}

	public String getPostDate() {
		return PostDate;
	}

	public void setPostDate(String postDate) {
		PostDate = postDate;
	}

	public int getPostLikeCount() {
		return PostLikeCount;
	}

	public void setPostLikeCount(int postLikeCount) {
		PostLikeCount = postLikeCount;
	}

	public int getPostCommentCount() {
		return PostCommentCount;
	}

	public void setPostCommentCount(int postCommentCount) {
		PostCommentCount = postCommentCount;
	}
	public String getPostImage() {
		return PostImage;
	}
	public void setPostImage(String postImage) {
		PostImage = postImage;
	}

	
    
}
