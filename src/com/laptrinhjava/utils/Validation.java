/* 
 *package com.laptrinhjava.utils;
 

public class Validation {
	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str); // phương thức static của lớp Double để chuyển đổi String thành Double.
			return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean stringIsNumeric(String checkNumber) {
		if (!checkNumber.isEmpty() || checkNumber.length() == 0 || !isNumeric(checkNumber)) {
			return false;
		}
		return true;
	}

}

*/