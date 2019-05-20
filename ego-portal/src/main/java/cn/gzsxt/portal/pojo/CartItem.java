package cn.gzsxt.portal.pojo;

public class CartItem {

	private String id;
	private String title;
	private String image;
	private Long price;
	private Integer num;
	private String[] images;
	public String[] getImages() {
		if(null!=this.image){
			images = image.split(",");
		}
		return images;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}

}
