package View;

import java.util.List;
import java.util.Scanner;

import Controller.BuildingController;
import Model.BuildingModel;

public class BuildingListView {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter building name: ");
		String name = in.nextLine();
		System.out.print("Enter street : ");
		String street = in.nextLine();
		System.out.println("---------------------");
		BuildingModel model = new BuildingModel();
		model.setName(name);
		model.setStreet(street);
		BuildingController buildingController = new BuildingController();
		List<BuildingModel> results = buildingController.getBuilding(model);
		for(BuildingModel item : results) {
			System.out.println("Id : " + item.getId() + " - Name : " + item.getName() 
			              + " - Street :" + item.getStreet() + " - buildingType : "
					      +  item.getBuildingType() + " ");
		}
		in.close();
	}
}
