package com.sana.avocado.enums;

import java.util.HashSet;
import java.util.Set;
 
public enum SupportTicketTranxIdTypeEnum {
  
	SESSION_ID, WALLET_TRANSACTION_ID, ORDER_ID, NONE;

	private static final Set<String> values = new HashSet<>(SupportTicketTranxIdTypeEnum.values().length);

	static {
		for (SupportTicketTranxIdTypeEnum transStatusEnum : SupportTicketTranxIdTypeEnum.values())
			values.add(transStatusEnum.name());
	}

	public static boolean contains(String value) {
		return values.contains(value);
	}
}
