package repository;

import java.util.List;

import Entity.BuildingEntity;

public interface BuildingRepository {
	List<BuildingEntity> getBuilding(); // get all k cần truyền tham số vào
	List<BuildingEntity> buildingSearch(String name, String street);
    List<BuildingEntity> getBuildingByStaff();
//    void save(BuildingEntity entity);
}
