
package com.sana.avocado.enums;

import java.util.HashSet;
import java.util.Set;

public enum SupportTicketStatusEnum {
	
	NONE, PENDING, RESOLVED;

	private static final Set<String> values = new HashSet<>(SupportTicketStatusEnum.values().length);

	static {
		for (SupportTicketStatusEnum sessionStatusEnum : SupportTicketStatusEnum.values())
			values.add(sessionStatusEnum.name());
	}

	public static boolean contains(String value) {
		return values.contains(value);
	}
}
 