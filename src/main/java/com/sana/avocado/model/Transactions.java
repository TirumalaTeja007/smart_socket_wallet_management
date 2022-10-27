package com.sana.avocado.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sana.avocado.enums.SessionStatusEnum;
import com.sana.avocado.enums.SessionTypeEnum;
import com.sana.avocado.enums.StatusEnum;


@Entity
public class Transactions extends VersionableEntity {
	
	/*
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "transaction_seq")
	private Integer id;
	private String userName;
	private String evseId;
	private String sessionId;
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDateTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date stopDateTime;
	private double meterValue;
	private double energyConsumed;
	private String stopChargeReason;
	@Enumerated(EnumType.STRING)
	private StatusEnum status = StatusEnum.ACTIVE;
	@Enumerated(EnumType.STRING)
	private SessionStatusEnum sessionStatusEnum = SessionStatusEnum.IDEL;
	@Enumerated(EnumType.STRING)
	private SessionTypeEnum sessionTypeEnum = SessionTypeEnum.REGULAR_MODE;
	private String chargingDuration;
	private String timeLimit;
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
	public String getEvseId() {
		return evseId;
	}
	public void setEvseId(String evseId) {
		this.evseId = evseId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Date getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}
	public Date getStopDateTime() {
		return stopDateTime;
	}
	public void setStopDateTime(Date stopDateTime) {
		this.stopDateTime = stopDateTime;
	}
	public double getMeterValue() {
		return meterValue;
	}
	public void setMeterValue(double meterValue) {
		this.meterValue = meterValue;
	}
	public double getEnergyConsumed() {
		return energyConsumed;
	}
	public void setEnergyConsumed(double energyConsumed) {
		this.energyConsumed = energyConsumed;
	}
	public String getStopChargeReason() {
		return stopChargeReason;
	}
	public void setStopChargeReason(String stopChargeReason) {
		this.stopChargeReason = stopChargeReason;
	}
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	public SessionStatusEnum getSessionStatusEnum() {
		return sessionStatusEnum;
	}
	public void setSessionStatusEnum(SessionStatusEnum sessionStatusEnum) {
		this.sessionStatusEnum = sessionStatusEnum;
	}
	
	public String getChargingDuration() {
		return chargingDuration;
	}
	public void setChargingDuration(String chargingDuration) {
		this.chargingDuration = chargingDuration;
	}
	
	public SessionTypeEnum getSessionTypeEnum() {
		return sessionTypeEnum;
	}
	public void setSessionTypeEnum(SessionTypeEnum sessionTypeEnum) {
		this.sessionTypeEnum = sessionTypeEnum;
	}
	@Override
	public String toString() {
		return "Transactions [id=" + id + ", userName=" + userName + ", evseId=" + evseId + ", sessionId=" + sessionId
				+ ", startDateTime=" + startDateTime + ", stopDateTime=" + stopDateTime + ", meterValue=" + meterValue
				+ ", energyConsumed=" + energyConsumed + ", stopChargeReason=" + stopChargeReason + ", status=" + status
				+ ", sessionStatusEnum=" + sessionStatusEnum + ", sessionTypeEnum=" + sessionTypeEnum
				+ ", chargingDuration=" + chargingDuration + ", timeLimit=" + timeLimit + "]";
	}
	public String getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}
		
		
	
	
	
	
	

}
