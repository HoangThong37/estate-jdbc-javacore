package Entity;

public class BuildingEntity {
	private int id;
	private String name;
	private String street;
	private Integer rentArea;
	private String buldingType;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	public Integer getRentArea() {
		return rentArea;
	}
	public void setRentArea(Integer rentArea) {
		this.rentArea = rentArea;
	}

	public String getBuldingType() {
		return buldingType;
	}

	public void setBuldingType(String buldingType) {
		this.buldingType = buldingType;
	}
}
