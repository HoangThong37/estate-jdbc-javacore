package View;

import java.util.List;
import java.util.Scanner;

import Controller.BuildingController;
import Model.BuildingModel;

public class BuildingListViewAll {
	public static void main(String[] args) {
		BuildingController buildingController = new BuildingController();
		List<BuildingModel> results = buildingController.getBuilding();
		for (BuildingModel item : results) {
			System.out.println("Id : " + item.getId());
			System.out.println("Name : " + item.getName());
			System.out.println("Street : " + item.getStreet());
			System.out.println("BuildingType : " + item.getBuildingType());
			System.out.println("-------------------------------------");
		}
	}
}
