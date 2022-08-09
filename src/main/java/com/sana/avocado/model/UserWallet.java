package com.sana.avocado.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="USER_WALLET_AMOUNT")
public class UserWallet extends VersionableEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "user_wallet_seq")
	private Integer id;
	private String userName;
	private double totalWalletAmount;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public double getTotalWalletAmount() {
		return totalWalletAmount;
	}
	public void setTotalWalletAmount(double totalWalletAmount) {
		this.totalWalletAmount = totalWalletAmount;
	}
	@Override
	public String toString() {
		return "UserWallet [id=" + id + ", userName=" + userName + ", totalWalletAmount=" + totalWalletAmount + "]";
	}
	

}
