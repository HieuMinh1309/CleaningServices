package pack.models;

import java.sql.Date;

public class ConfirmImages {
	private int id;
	private int detailId;
	private String images;
	private String note;
	private Date uploadDate;
	
	public ConfirmImages() {}

	public ConfirmImages(int id, int detailId, String images, String note, Date uploadDate) {
		super();
		this.id = id;
		this.detailId = detailId;
		this.images = images;
		this.note = note;
		this.uploadDate = uploadDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDetailId() {
		return detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	
}
