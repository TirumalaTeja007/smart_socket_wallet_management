package com.sana.avocado.enums;

import java.util.HashSet;
import java.util.Set;

public enum SupportTicketTypeEnum {
	
	NONE, CHARGER, TOPUP, MOBILE_APP, SESSION;

	private static final Set<String> values = new HashSet<>(SupportTicketTypeEnum.values().length);

	static {
		for (SupportTicketTypeEnum transStatusEnum : SupportTicketTypeEnum.values())
			values.add(transStatusEnum.name());
	}

	public static boolean contains(String value) {
		return values.contains(value);
	}
}
     