package repository;

import java.util.List;

import Entity.BuildingEntity;
import Model.BuildingModel;

public interface BuildingRepository {
	List<BuildingEntity> getBuilding(); // get all k cần truyền tham số vào
	List<BuildingEntity> buildingSearch(BuildingModel model);
    List<BuildingEntity> getBuildingByStaff();
}
