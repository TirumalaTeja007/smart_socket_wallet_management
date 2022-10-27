package com.sana.avocado.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sana.avocado.enums.StatusEnum;
import com.sana.avocado.enums.SupportTicketStatusEnum;
import com.sana.avocado.enums.SupportTicketTranxIdTypeEnum;
import com.sana.avocado.enums.SupportTicketTypeEnum;

@Entity
public class CustomerSupport extends VersionableEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "customer_support_seq")
	private Integer id;
	private String userName;
	private String mobileNumber;
	@Enumerated(EnumType.STRING)
	private SupportTicketTypeEnum ticketTypeEnum = SupportTicketTypeEnum.NONE;
	@Enumerated(EnumType.STRING)
	private SupportTicketTranxIdTypeEnum tranxIdTypeEnum = SupportTicketTranxIdTypeEnum.NONE;
	@Enumerated(EnumType.STRING)
	private SupportTicketStatusEnum ticketStatusEnum = SupportTicketStatusEnum.NONE;
	@Column(columnDefinition = "TEXT")
	private String description;
	private String issueType;
	private String tranxId;
	private String ticketId;
	@Enumerated(EnumType.STRING)
	private StatusEnum status = StatusEnum.ACTIVE;
	public Integer getId() { 
		return id;
	} 
	public SupportTicketTranxIdTypeEnum getTranxIdTypeEnum() {
		return tranxIdTypeEnum;
	}
	public SupportTicketStatusEnum getTicketStatusEnum() {
		return ticketStatusEnum;
	}
	public void setTicketStausEnum(SupportTicketStatusEnum ticketStatusEnum) {
		this.ticketStatusEnum = ticketStatusEnum;
	}
	public void setTranxIdTypeEnum(SupportTicketTranxIdTypeEnum tranxIdTypeEnum) {
		this.tranxIdTypeEnum = tranxIdTypeEnum;
	}
	public String getTranxId() {
		return tranxId;
	}
	public void setTranxId(String tranxId) {
		this.tranxId = tranxId;
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
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public SupportTicketTypeEnum getTicketTypeEnum() {
		return ticketTypeEnum;
	}
	public void setTicketTypeEnum(SupportTicketTypeEnum ticketTypeEnum) {
		this.ticketTypeEnum = ticketTypeEnum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	public String getTicketId() {
		return ticketId;
	}
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "CustomerSupport [id=" + id + ", userName=" + userName + ", mobileNumber=" + mobileNumber
				+ ", ticketTypeEnum=" + ticketTypeEnum + ", tranxIdTypeEnum=" + tranxIdTypeEnum + ", ticketStatusEnum="
				+ ticketStatusEnum + ", description=" + description + ", issueType=" + issueType + ", tranxId=" + tranxId
				+ ", ticketId=" + ticketId + ", status=" + status + "]";
	}
	
	
	
	
}
	
	