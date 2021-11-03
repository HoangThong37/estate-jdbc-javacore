package Controller;

import java.util.List;
import Model.BuildingModel;
import Service.BuildingService;
import Service.impl.BuildingServiceImpl;

public class BuildingController {
	private BuildingService buildingService = new BuildingServiceImpl();
	
	public List<BuildingModel> getBuilding(BuildingModel model) {
		// covert từ tk bean -> model
		return buildingService.getBuilding(model);
	}
//	public void save(BuildingModel model) {
//		buildingService.save(model);
//	}

}
