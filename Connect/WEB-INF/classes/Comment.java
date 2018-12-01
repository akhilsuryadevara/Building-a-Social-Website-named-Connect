public class Comment implements java.io.Serializable  {
	String PostId;
    String UserName ;
    String CommentText ;
    String CommentDate ;
    
    
	public Comment(String postId, String userName, String commentText, String commentDate) {
		super();
		PostId = postId;
		UserName = userName;
		CommentText = commentText;
		CommentDate = commentDate;
	}
	public String getPostId() {
		return PostId;
	}
	public void setPostId(String postId) {
		PostId = postId;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getCommentText() {
		return CommentText;
	}
	public void setCommentText(String commentText) {
		CommentText = commentText;
	}
	public String getCommentDate() {
		return CommentDate;
	}
	public void setCommentDate(String commentDate) {
		CommentDate = commentDate;
	}
   
}