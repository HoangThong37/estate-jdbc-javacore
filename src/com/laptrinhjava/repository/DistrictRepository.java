package com.laptrinhjava.repository;

import com.laptrinhjava.entity.DistrictEntity;

public interface DistrictRepository {
	DistrictEntity findByCode(Integer id);

}
