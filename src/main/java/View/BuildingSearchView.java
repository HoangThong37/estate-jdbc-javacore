package View;

import java.util.List;
import java.util.Scanner;

import Controller.BuildingController;
import Model.BuildingModel;

public class BuildingSearchView {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter building name: ");
		String name = in.nextLine();
		System.out.print("Enter street : ");
		String street = in.nextLine();
		System.out.println("-----------------------");

		BuildingModel model = new BuildingModel();
		model.setName(name);
		model.setStreet(street);

		BuildingController buildingController = new BuildingController();
		List<BuildingModel> result = buildingController.buildingSearch(model);
		for (BuildingModel item : result) { // duyệt hết
			System.out.println("Id : " + item.getId());
			System.out.println("Name : " + item.getName());
			System.out.println("Street : " + item.getStreet());
			System.out.println("BuildingType : " + item.getBuildingType());
			System.out.println("----------------------------------------");
		}
		in.close();
	}
}
