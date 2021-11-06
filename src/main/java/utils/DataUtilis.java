package utils;

import java.util.HashMap;
import java.util.Map;

import constant.SystemConstant;

public class DataUtilis {
	// viết utils ví dụ DataUtils với method getBuildingTypes để set data đó vào map.
	public static Map<String, String> getBuildingTypes() {
	Map<String, String> map = new HashMap<>();
	map.put(SystemConstant.NGUYENCAN_CODE, SystemConstant.NGUYENCAN_NAME);
	map.put(SystemConstant.NOITHAT_CODE, SystemConstant.NOITHAT_NAME);
	map.put(SystemConstant.TANGTRET_CODE, SystemConstant.TANGTRET_NAME);
	return map;
	}
}

//put = lưu trữ giá trị được chỉ định và liên kết nó với key được chỉ định trong map này.
//Các giá trị có thể được lưu trữ trong map bằng cách tạo một cặp key - value.
//Giá trị có thể được lấy bằng cách truyền key đến đúng phương thức.