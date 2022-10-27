package com.sana.avocado.enums;

import java.util.HashSet;
import java.util.Set;

public enum SessionTypeEnum {
	
	REGULAR_MODE, NORMAL_MODE;

	private static final Set<String> values = new HashSet<>(SessionTypeEnum.values().length);

	static {
		for (SessionTypeEnum sessionTypeEnum : SessionTypeEnum.values())
			values.add(sessionTypeEnum.name());
	}

	public static boolean contains(String value) {
		return values.contains(value);
	}


}
