package com.laptrinhjava.dto.response;

public class BuildingResponse { // java bean
	private String name; // ten sp
	private String address; // địa chi = duong + phuong + quan
	private Integer floorarea; // dien tich san
	private Integer rentprice; // gia thue
//	private String service;
	private Integer servicefee; // phi dich vu
	private Integer brokeragefee; // phi moi gioi

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getFloorarea() {
		return floorarea;
	}

	public void setFloorarea(String floorarea) {
		this.floorarea = Integer.parseInt(floorarea);
	}

	public Integer getRentprice() {
		return rentprice;
	}

	public void setRentprice(String rentprice) {
		this.rentprice = Integer.parseInt(rentprice);
	}

	public Integer getServicefee() {
		return servicefee;
	}

	public void setServicefee(String servicefee) {
		this.servicefee = Integer.parseInt(servicefee);
	}

	public Integer getBrokeragefee() {
		return brokeragefee;
	}

	public void setBrokeragefee(Integer brokeragefee) {
		this.brokeragefee = brokeragefee;
	}

}
