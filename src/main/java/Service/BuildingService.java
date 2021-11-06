package Service;

import java.util.List;

import Model.BuildingModel;

public interface BuildingService {
	List<BuildingModel> getBuilding();
	List<BuildingModel> buildingSearch(BuildingModel model);
}
