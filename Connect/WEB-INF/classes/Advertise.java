
public class Advertise implements java.io.Serializable{
	
	
	
    String AdUrl ;
    String AdImageName ;
    
	
	public Advertise(String AdUrl, String AdImage ) {
		super();
		AdUrl = AdUrl;
		AdImageName = AdImage;
	}

	

	public String getAdUrl() {
		return AdUrl;
	}

	public void setAdUrl(String url) {
		AdUrl = url;
	}

	public String getAdImageName() {
		return AdImageName;
	}

	public void setAdImageName(String image) {
		AdImageName = image;
	}

	
}
