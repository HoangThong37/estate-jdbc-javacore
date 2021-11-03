package repository;

import java.util.List;

import Entity.BuildingEntity;

public interface BuildingRepository {
	List<BuildingEntity> getBuilding(String name, String street);
    List<BuildingEntity> getBuildingByStaff();
//    void save(BuildingEntity entity);
}
 // int id,String buildingType