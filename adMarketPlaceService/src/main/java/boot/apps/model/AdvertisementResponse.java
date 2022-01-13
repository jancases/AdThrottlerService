package boot.apps.model;

public class AdvertisementResponse {
	
	private String ad_type;
	private String category;
	private String image_url;
	
	public AdvertisementResponse() {
		
	}
	
	public AdvertisementResponse(String ad_type, String category, String image_url) {
		setAd_type(ad_type);
		setCategory(category);
		setImage_url(image_url);
	}
	
	public void setAd_type(String ad_type) {
		this.ad_type = ad_type;
	}
	
	public String getAd_type() {
		return ad_type;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
	public String getImage_url() {
		return image_url;
	}

}
