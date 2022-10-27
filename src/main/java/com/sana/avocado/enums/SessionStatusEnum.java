package com.sana.avocado.enums;

import java.util.HashSet;
import java.util.Set;

public enum SessionStatusEnum {
	
	IDEL, START, STOP;

	private static final Set<String> values = new HashSet<>(SessionStatusEnum.values().length);

	static {
		for (SessionStatusEnum sessionStatusEnum : SessionStatusEnum.values())
			values.add(sessionStatusEnum.name());
	}

	public static boolean contains(String value) {
		return values.contains(value);
	}
}
