package com.theankulal.util;

import org.springframework.stereotype.Component;

@Component
public class CommonConditions {
	
	public boolean checkNulAndEmpty(String variable) {
		boolean result = false;
		if(null != variable && !variable.isEmpty()) {
			result = true;
		}
		return result;
	}

}
