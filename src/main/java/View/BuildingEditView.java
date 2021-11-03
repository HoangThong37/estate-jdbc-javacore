package View;

import java.util.List;
import java.util.Scanner;

import Controller.BuildingController;
import Model.BuildingModel;

public class BuildingEditView {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter building id : ");
		int id = in.nextInt();
		System.out.print("Enter building name: ");
		String name = in.nextLine();
		System.out.print("Enter street : ");
		String street = in.nextLine();
		System.out.print("Enter buildingType : ");
		String buildingType = in.nextLine();
		System.out.println("-----------------------");
//		System.out.println("Building name is: " + buildingName);
		BuildingModel model = new BuildingModel();
		model.setId(id);
		model.setName(name);
		model.setStreet(street);
		model.setBuildingType(buildingType);
		BuildingController buildingController = new BuildingController();
//		buildingController.save(model);
		in.close();
	}
}
