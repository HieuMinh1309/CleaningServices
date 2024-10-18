package pack.models;

import java.sql.Date;

public class Staff {
	private int id;
	private String username;
	private String password;
	private String email;
	private String phone;
	private int jobOccupied;
	private String images;
	private Date createDate;
	private String status;
	
	public Staff() {}

	public Staff(int id, String username, String password, String email, String phone, int jobOccupied, String images,
			Date createDate, String status) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.jobOccupied = jobOccupied;
		this.images = images;
		this.createDate = createDate;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getJobOccupied() {
		return jobOccupied;
	}

	public void setJobOccupied(int jobOccupied) {
		this.jobOccupied = jobOccupied;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
