package com.sana.avocado.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sana.avocado.enums.TransactionStatusEnum;
import com.sana.avocado.enums.TransactionTypeEnum;

@Entity(name="USER_TRANSACTIONS_WALLET")
public class UserTransactionsWallet extends VersionableEntity {

	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "user_transaction_wallet_seq")
	private Integer id;
	private String userName;
	private double walletAmount;
	@Enumerated(EnumType.STRING)
	private TransactionTypeEnum transactionType = TransactionTypeEnum.NONE;
	@Enumerated(EnumType.STRING)
	private TransactionStatusEnum transactionStatus = TransactionStatusEnum.NONE;
	private double onHoldAmount;
	private double transactionAmount;
	private String walletTransactionId;
	private String orderId;
	private String sessionId;
	public Integer getId() {
		return id;
	} 
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public TransactionStatusEnum getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(TransactionStatusEnum transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public double getWalletAmount() {
		return walletAmount;
	}
	public void setWalletAmount(double walletAmount) {
		this.walletAmount = walletAmount;
	}
	
	public TransactionTypeEnum getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionTypeEnum transactionType) {
		this.transactionType = transactionType;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	
	public double getOnHoldAmount() {
		return onHoldAmount;
	}
	public void setOnHoldAmount(double onHoldAmount) {
		this.onHoldAmount = onHoldAmount;
	}
	@Override
	public String toString() {
		return "UserTransactionsWallet [id=" + id + ", userName=" + userName + ", walletAmount=" + walletAmount
				+ ", transactionType=" + transactionType + ", transactionStatus=" + transactionStatus
				+ ", onHoldAmount=" + onHoldAmount + ", transactionAmount=" + transactionAmount
				+ ", walletTransactionId=" + walletTransactionId + ", orderId=" + orderId + ", sessionId=" + sessionId
				+ "]";
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getWalletTransactionId() {
		return walletTransactionId;
	}
	public void setWalletTransactionId(String walletTransactionId) {
		this.walletTransactionId = walletTransactionId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
