package com.sana.avocado.enums;

import java.util.HashSet;
import java.util.Set;

public enum TransactionStatusEnum {
	SUCCESS, FAILURE, PENDING, TIMEDOUT, NONE;

	private static final Set<String> values = new HashSet<>(TransactionStatusEnum.values().length);

	static {
		for (TransactionStatusEnum transEnum : TransactionStatusEnum.values())
			values.add(transEnum.name());
	}

	public static boolean contains(String value) {
		return values.contains(value);
	}
} 
