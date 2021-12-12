package com.laptrinhjava.utils;

import com.laptrinhjava.constant.BuldingTypeConstant;

public class ValidateUtils {
	public static boolean isValid(Object obj) {
		boolean isTrue = null != obj && !BuldingTypeConstant.STRING_EMPTY.equals(obj.toString());
		// truyền vào isTrue khác null và khác rỗng
		if (isTrue) {
			if (obj instanceof String) {
				return true;
			} else if (obj instanceof Integer) {
				return 0 <= Integer.parseInt(obj.toString());
			}
		}
		return false;
	}
}
