package com.laptrinhjava.view;

import java.util.List;
import java.util.Scanner;

import com.laptrinhjava.controller.BuldingController;
import com.laptrinhjava.dto.request.BuildingRequestDto;
import com.laptrinhjava.dto.response.BuildingResponse;

public class BuildingSearch {
public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter building name : ");
		String buildingName = scanner.nextLine();
		System.out.println("Enter ward : ");
		String ward = scanner.nextLine();
		System.out.println("Enter street : ");
		String street = scanner.nextLine();
		System.out.println("Enter direction: ");
		String direction = scanner.nextLine();
		System.out.println("Enter level : ");
		String level = scanner.nextLine();
		System.out.println("Enter district code : ");
		String districtCode = scanner.nextLine();
		System.out.println("Enter floor area : ");
		String floorArea = scanner.nextLine();
		System.out.println("Enter number of basement : ");
		String numberofbasement = scanner.nextLine();
		System.out.println("Enter area from : ");
		String areafrom = scanner.nextLine();
		System.out.println("Enter area to : ");
		String areaTo = scanner.nextLine();
		System.out.println("Enter rent price from : ");
		String rentpricefrom = scanner.nextLine();
		System.out.println("Enter rent price to : ");
		String rentpriceto = scanner.nextLine();
		System.out.println("Enter staff id : ");
		String staffId = scanner.nextLine();
		
		// số loại tòa nhà
		System.out.println("Số loại tòa nhà : ");
		int number = scanner.nextInt();
		String type[] = new String[number];
		for (int i = 0; i < number; i++) {
			System.out.println("Enter building type : ");
			String buildingType = scanner.nextLine();
			buildingType = type[i];
		}
		
		BuildingRequestDto requestDto = new BuildingRequestDto();
		requestDto.setName(buildingName);
		requestDto.setWard(ward);
		requestDto.setStreet(street);
		requestDto.setDirection(direction);
		requestDto.setLevel(level);
		requestDto.setDistrictCode(districtCode);
		requestDto.setFloorArea(floorArea);
		requestDto.setNumberOfBasements(numberofbasement);
		requestDto.setAreaFrom(areafrom);
		requestDto.setAreaTo(areaTo);
		requestDto.setRentPriceFrom(rentpriceto);
		requestDto.setRentPriceTo(rentpriceto);
		requestDto.setStaffId(staffId);
		requestDto.setBuildingTypes(type);
	
	    BuldingController buldingController = new BuldingController();
	    List<BuildingResponse> result = buldingController.getBuildingSearch(requestDto);
	    for(BuildingResponse item :result) {
	    	System.out.println("Name:" + item.getName());
			System.out.println("Address:" + item.getAddress());
			System.out.println("FloorArea:" + item.getFloorarea());
			System.out.println("Rentprice:" + item.getRentprice());
			System.out.println("ServiceFee:" + item.getServicefee());
			System.out.println("BrokerageFee:" + item.getBrokeragefee());
			System.out.println("____________________________________");
	    }
	    scanner.close();
	}
}
