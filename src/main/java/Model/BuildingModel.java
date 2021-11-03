package Model;

public class BuildingModel {
	private int id;
	private String name;
	private String street;
	private Integer rentArea;
	private String buildingType;
	
    


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

	public String getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}

}
