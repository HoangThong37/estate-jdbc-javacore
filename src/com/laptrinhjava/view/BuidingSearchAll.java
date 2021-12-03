package com.laptrinhjava.view;

import java.util.List;
import java.util.Scanner;

import com.laptrinhjava.controller.BuldingController;
import com.laptrinhjava.dto.request.BuildingRequestDto;
import com.laptrinhjava.dto.response.BuildingResponse;
//import com.laptrinhjava.entity.BuildingEntity;


public class BuidingSearchAll {
	public static void main(String[] args) {
		BuldingController buldingController = new BuldingController();
		BuildingRequestDto dto = new BuildingRequestDto();
		Scanner scanner = new Scanner(System.in);
		List<BuildingResponse> result = buldingController.getBuilding(dto);
		for (BuildingResponse item : result) {
			System.out.println("name : " + item.getName());
			System.out.println("address : " + item.getAddress());
			System.out.println("Floorarea : " + item.getFloorarea());
			System.out.println("Rentprice : " + item.getRentprice());
			System.out.println("Servicefee : " + item.getServicefee());
			System.out.println("Brokeragefee : " + item.getBrokeragefee());	
			System.out.println("-------------------------------------");
		}
	}
}
