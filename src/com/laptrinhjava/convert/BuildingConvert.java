package com.laptrinhjava.convert;

import com.laptrinhjava.dto.response.BuildingResponse;
import com.laptrinhjava.entity.BuildingEntity;
import com.laptrinhjava.entity.DistrictEntity;
import com.laptrinhjava.repository.DistrictRepository;
import com.laptrinhjava.repository.impl.DistrictRepositoryImpl;

public class BuildingConvert {
	public static BuildingResponse ConvertBuilding(BuildingEntity entity)  {
		DistrictRepository districtRepository = new DistrictRepositoryImpl();				
		BuildingResponse model = new BuildingResponse();
		
		DistrictEntity district = districtRepository.findByCode(entity.getDistrictid()); // lấy id district		
		model.setName(entity.getName());
		model.setAddress(entity.getStreet() + " , " + entity.getWard() + " , " + district.getName());
		model.setBrokeragefee(entity.getBrokeragefee());
		model.setFloorarea(entity.getFloorarea());
		model.setRentprice(entity.getRentprice());
		model.setServicefee(entity.getServicefee());
		return model;
		
	}
}
