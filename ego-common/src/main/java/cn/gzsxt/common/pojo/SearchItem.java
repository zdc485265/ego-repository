package cn.gzsxt.common.pojo;

public class SearchItem {
	
	private Long id;
	private String title;
	private String sell_point;
	private Long price;
	private String image;
	private String category_name;
	private String[] images;
	
	public String[] getImages() {
		if(null!=this.image){
			images = image.split(",");
		}
		return images;
	}

	public SearchItem() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSell_point() {
		return sell_point;
	}
	public void setSell_point(String sell_point) {
		this.sell_point = sell_point;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

}
