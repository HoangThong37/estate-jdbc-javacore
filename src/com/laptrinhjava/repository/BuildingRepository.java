package com.laptrinhjava.repository;

import java.util.List;
import java.util.Map;

import com.laptrinhjava.dto.request.BuildingRequestDto;
import com.laptrinhjava.dto.response.BuildingResponse;
import com.laptrinhjava.entity.BuildingEntity;


public interface BuildingRepository { 
	List<BuildingEntity> buildingSearch(Map<String, Object> params ,List<String>  buildingTypes);

}
