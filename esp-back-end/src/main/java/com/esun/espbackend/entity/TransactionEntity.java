package com.esun.espbackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="TRANSACTIONS")
public class TransactionEntity {
	@Id
	@Column(name = "id")
	String Id;
	
	@NotNull
    @Column(name = "datetime")
    String DateTime;
	
	@NotNull
    @Column(name = "branch_code")
    String BranchCode;
	
	@NotNull
    @Column(name = "type")
    String Type;
	
	@NotNull
    @Column(name = "front_data")
    String FrontData;
	
	@NotNull
    @Column(name = "back_data")
    String BackData;
	
	public String getId() {
		return Id;
	}
	public String getDateTime() {
		return DateTime;
	}
	public String getBranchCode() {
		return BranchCode;
	}
	public String getType() {
		return Type;
	}
	public String getFrontData() {
		return FrontData;
	}
	public String getBackData() {
		return BackData;
	}
	
	public void setId(String id) {
		this.Id = id;
	}
	public void setDateTime(String datetime) {
		this.DateTime = datetime;
	}
	public void setBranchCode(String branch_code) {
		this.BranchCode = branch_code;
	}
	public void setType(String type) {
		this.Type = type;
	}
	public void setFrontData(String front_data) {
		this.FrontData = front_data;
	}
	public void setBackData(String back_data) {
		this.BackData = back_data;
	}
}
