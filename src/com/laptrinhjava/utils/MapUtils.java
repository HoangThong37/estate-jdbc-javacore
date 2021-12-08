package com.laptrinhjava.utils;

import java.util.Map;

public class MapUtils {
	public  static  Object getValue(Map<String ,Object> maps, String key){
        return  maps.containsKey(key) ? maps.get(key) : null;
    }

}
