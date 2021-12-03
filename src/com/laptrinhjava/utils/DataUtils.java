package com.laptrinhjava.utils;

import java.util.HashMap;
import java.util.Map;

import com.laptrinhjava.constant.BuldingTypeConstant;

public class DataUtils {
	public static Map<String, String> getBuildingType() {
		Map<String, String> mapCodeName = new HashMap<>();
		mapCodeName.put(BuldingTypeConstant.TANG_TRET_CODE, BuldingTypeConstant.TANG_TRET_VALUE);
		mapCodeName.put(BuldingTypeConstant.NGUYEN_CAN_CODE, BuldingTypeConstant.NGUYEN_CAN_VALUE);
		mapCodeName.put(BuldingTypeConstant.NOI_THAT_CODE, BuldingTypeConstant.NOI_THAT_VALUE);
		return mapCodeName;
	}
	public static Map<String, String> getTypeDistrict() {
		Map<String, String> mapCodeDistrict = new HashMap<>();
		mapCodeDistrict.put(BuldingTypeConstant.QUAN_1_CODE, BuldingTypeConstant.QUAN_1_VALUE);
		mapCodeDistrict.put(BuldingTypeConstant.QUAN_2_CODE, BuldingTypeConstant.QUAN_2_VALUE);
		mapCodeDistrict.put(BuldingTypeConstant.QUAN_4_CODE, BuldingTypeConstant.QUAN_4_VALUE);
		return mapCodeDistrict;
	}
}
