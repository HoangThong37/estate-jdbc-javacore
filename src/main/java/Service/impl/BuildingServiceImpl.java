package Service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;
import javax.xml.bind.DataBindingException;

import Entity.BuildingEntity;
import Model.BuildingModel;
import Service.BuildingService;
import constant.SystemConstant;
import repository.BuildingRepository;
import repository.impl.BuildingRepositoryImpl;
import utils.DataUtilis;

public class BuildingServiceImpl implements BuildingService {
	private BuildingRepository buildingRepository = new BuildingRepositoryImpl();

	@Override
	public List<BuildingModel> getBuilding() {
		List<BuildingModel> result = new ArrayList<>(); // new để hứng kết quả entity
		List<BuildingEntity> buildingEntities = buildingRepository.getBuilding();
		// convert từ entity -> model
		
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

	@Override
	public List<BuildingModel> buildingSearch(BuildingModel model) {
		List<BuildingModel> result = new ArrayList<>();
		List<BuildingEntity> buildingEntities = buildingRepository.buildingSearch(model.getName(), model.getStreet());
		
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
	
	private String convertBuildingType(String types) {
		StringBuilder result = new StringBuilder();  // stringbuilder là có thể thay đổi., result để hứng kq !
		String[] strArray = types.split(","); // tách chuỗi bằng dấu phẩy
		Map<String, String> mapTypes = DataUtilis.getBuildingTypes();
		for (String item : strArray) { // duyệt hết 
			if(mapTypes.containsKey(item)) { // ktra xem item có trong myTypes ko ?
				result.append("\n - ").append(mapTypes.get(item));
			}
		}
		return types.toString();	
	}
	}




