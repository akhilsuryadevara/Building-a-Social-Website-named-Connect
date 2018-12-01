import java.io.IOException;
import java.io.*;


public class Review implements Serializable {
    private String reviewForUser;
    private String userfullname;
    private String currentuser;
    private String userAge;
    private String userGender;
    private String userOccupation;
    private String userpin;
    private String usercity;
    private String reviewrating;
    private String reviewDate;
    private String reviewText;
    
    
	public Review(String reviewForUser, String userfullname, String currentuser, String userAge, String userGender,
			String userOccupation, String userpin, String usercity, String reviewrating, String reviewDate,
			String reviewText) {
		super();
		this.reviewForUser = reviewForUser;
		this.userfullname = userfullname;
		this.currentuser = currentuser;
		this.userAge = userAge;
		this.userGender = userGender;
		this.userOccupation = userOccupation;
		this.userpin = userpin;
		this.usercity = usercity;
		this.reviewrating = reviewrating;
		this.reviewDate = reviewDate;
		this.reviewText = reviewText;
	}
	
	public Review(String userfullname, String userpin, String reviewrating, String reviewText) {
	       this.userfullname = userfullname;
	       this.userpin = userpin;
	       this.reviewrating = reviewrating;
	       this.reviewText = reviewText;
	    }

	
	public String getReviewForUser() {
		return reviewForUser;
	}
	public void setReviewForUser(String reviewForUser) {
		this.reviewForUser = reviewForUser;
	}
	public String getUserfullname() {
		return userfullname;
	}
	public void setUserfullname(String userfullname) {
		this.userfullname = userfullname;
	}
	public String getCurrentuser() {
		return currentuser;
	}
	public void setCurrentuser(String currentuser) {
		this.currentuser = currentuser;
	}
	public String getUserAge() {
		return userAge;
	}
	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserOccupation() {
		return userOccupation;
	}
	public void setUserOccupation(String userOccupation) {
		this.userOccupation = userOccupation;
	}
	public String getUserpin() {
		return userpin;
	}
	public void setUserpin(String userpin) {
		this.userpin = userpin;
	}
	public String getUsercity() {
		return usercity;
	}
	public void setUsercity(String usercity) {
		this.usercity = usercity;
	}
	public String getReviewrating() {
		return reviewrating;
	}
	public void setReviewrating(String reviewrating) {
		this.reviewrating = reviewrating;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}


}
