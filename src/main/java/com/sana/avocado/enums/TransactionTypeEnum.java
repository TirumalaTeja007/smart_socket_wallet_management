package com.sana.avocado.enums;

import java.util.HashSet;
import java.util.Set;

public enum TransactionTypeEnum {
	CREDIT, DEBIT, ON_HOLD, NONE;

	private static final Set<String> values = new HashSet<>(TransactionTypeEnum.values().length);

	static {
		for (TransactionTypeEnum transEnum : TransactionTypeEnum.values())
			values.add(transEnum.name());
	}

	public static boolean contains(String value) {
		return values.contains(value);
	}

}
