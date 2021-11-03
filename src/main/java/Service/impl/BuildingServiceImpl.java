package Service.impl;

import java.util.ArrayList;
import java.util.List;

import Entity.BuildingEntity;
import Model.BuildingModel;
import Service.BuildingService;
import constant.SystemConstant;
import repository.BuildingRepository;
import repository.impl.BuildingRepositoryImpl;

public class BuildingServiceImpl implements BuildingService {
	private BuildingRepository buildingRepository = new BuildingRepositoryImpl();

	@Override
	public List<BuildingModel> getBuilding(BuildingModel model) {
		List<BuildingModel> result = new ArrayList<>();

		List<BuildingEntity> buildingEntities = buildingRepository.getBuilding(model.getName(), model.getStreet());
		for (BuildingEntity item : buildingEntities) {
			BuildingModel buildingModel = new BuildingModel();
			buildingModel.setId(item.getId());
			buildingModel.setName(item.getName());
			buildingModel.setStreet(item.getStreet());
			buildingModel.setBuildingType(item.getBuldingType());
			result.add(buildingModel);

		}
		return result;
	}

}

//	@Override
//	public void save(BuildingModel model) {
//		// covert model -> entity
//		BuildingEntity buildingEntity = new BuildingEntity();
//		buildingEntity.setId(model.getId());
//		buildingEntity.setName(model.getName());
//		buildingEntity.setStreet(model.getStreet());
//		buildingEntity.setBuldingType(model.getBuildingType());
//		buildingRepository.save(buildingEntity);
//
//	}


