package pack.models;

public class Service {
	private int id;
	private String serName;
	private String description;
	private double basePrice;
	private double duration;
	
	public Service() {}

	public Service(int id, String serName, String description, double basePrice, double duration) {
		super();
		this.id = id;
		this.serName = serName;
		this.description = description;
		this.basePrice = basePrice;
		this.duration = duration;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSerName() {
		return serName;
	}

	public void setSerName(String serName) {
		this.serName = serName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}
	
	
}
