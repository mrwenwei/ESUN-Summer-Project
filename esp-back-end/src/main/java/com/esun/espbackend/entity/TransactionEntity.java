package com.esun.espbackend.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="TRANSACTIONS")
public class TransactionEntity {
	@Id
	@Column(name = "id", columnDefinition="uniqueidentifier")
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	String Id;
	
    @Column(name = "datetime", insertable = false, updatable = false)
    @CreationTimestamp
    Date DateTime;
	
    @Column(name = "branch_code")
    String BranchCode;
	
	@NotNull
    @Column(name = "type")
    String Type;
	
	@NotNull
	@Column(name = "broker")
	String Broker;
	
	@NotNull
    @Column(name = "receipts_data")
    String ReceiptsData;
	
    @Column(name = "finished")
    boolean Finished;
    
    @Column(name = "reviewed")
    boolean Reviewed;
	
	public String getId() {
		return Id;
	}
	public Date getDateTime() {
		return DateTime;
	}
	public String getBranchCode() {
		return BranchCode;
	}
	public String getType() {
		return Type;
	}
	public String getBroker() {
		return Broker;
	}
	public String getReceiptsData() {
		return ReceiptsData;
	}
	public boolean getFinished() {
		return Finished;
	}
	public boolean getReviewed() {
		return Reviewed;
	}
	
	
	public void setId(String id) {
		this.Id = id;
	}
	public void setDateTime(Date datetime) {
		this.DateTime = datetime;
	}
	public void setBranchCode(String branch_code) {
		this.BranchCode = branch_code;
	}
	public void setType(String type) {
		this.Type = type;
	}
	public void setBroker(String broker) {
		this.Broker = broker;
	}
	public void setReceiptsData(String receipts_data) {
		this.ReceiptsData = receipts_data;
	}
	public void setFinished(boolean finished) {
		this.Finished = finished;
	}
	public void setReviewed(boolean reviewed) {
		this.Reviewed = reviewed;
	}
}
