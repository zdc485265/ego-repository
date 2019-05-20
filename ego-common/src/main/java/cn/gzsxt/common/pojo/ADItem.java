package cn.gzsxt.common.pojo;

public class ADItem {

	/**
	 * {"srcB":"http://image.ego.com/images/2015/03/03/2015030304360302109345.jpg",
	 * "height":240,
	 * "alt":"",
	 * "width":670,
	 * "src":"http://192.168.88.6/images/1541214436730.jpg",
	 * "widthB":550,
	 * "href":"http://sale.jd.com/act/e0FMkuDhJz35CNt.html?cpdad=1DLSUE","
	 * heightB":240
	 * 
	 */
	
	private String srcB;
	
	private int height= 240;
	
	private String alt;
	
	private int width=670;
	
	private String src;
	
	private int widthB = 550;
	
	private String href;
	
	private int heightB=240;

	public ADItem() {
		super();
	}

	public String getSrcB() {
		return srcB;
	}

	public void setSrcB(String srcB) {
		this.srcB = srcB;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public int getWidthB() {
		return widthB;
	}

	public void setWidthB(int widthB) {
		this.widthB = widthB;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public int getHeightB() {
		return heightB;
	}

	public void setHeightB(int heightB) {
		this.heightB = heightB;
	}

}
