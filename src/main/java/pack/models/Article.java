package pack.models;

import java.sql.Date;

public class Article {
	private int id;
	private String title;
	private String content;
	private String images;
	private Date createDate;
	private Date updateDate;
	
	public Article() {}

	public Article(int id, String title, String content, String images, Date createDate, Date updateDate) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.images = images;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
}
