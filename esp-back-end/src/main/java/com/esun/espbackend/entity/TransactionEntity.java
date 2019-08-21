package com.esun.espbackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="TRANSACTIONS")
public class TransactionEntity {
	@Id
//	@Column(name = "id", columnDefinition="uniqueidentifier")
//	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
//	@GeneratedValue(generator = "generator")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	String Id;
	
	@Column(name = "tnum", insertable = false, updatable = false)
	String Tnum;
	
    @Column(name = "datetime", insertable = false, updatable = false)
	String DateTime;
	
    @Column(name = "branch_code")
    String BranchCode;
	
	@NotNull
    @Column(name = "type")
    String Type;
	
	@Column(name = "broker")
	String Broker;
	
    @Column(name = "receipts_data")
    String ReceiptsData;
	
    @Column(name = "finished")
    boolean Finished;
    
    @Column(name = "reviewed")
    boolean Reviewed;
    
    @Column(name = "finished_condition")
    Integer FinishedCondition;
    
    @Column(name = "reviewed_condition")
    Integer ReviewedCondition;
    
    @Column(name = "back_data")
    String BackData;
    
    @Column(name = "cash_detail")
    String CashDetail;
    
    @Column(name = "finished_time")
    String FinishedTime;
    
    @Column(name = "data_version")
    String DataVersion;
	
	public String getId() {
		return Id;
	}
	public String getTnum() {
		return Tnum;
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
	public Integer getFinishedCondition() {
		return FinishedCondition;
	}
	public Integer getReviewedCondition() {
		return ReviewedCondition;
	}
	public String getBackData() {
		return BackData;
	}
	public String getCashDetail() {
		return CashDetail;
	}
	public String getFinishedTime() {
		return FinishedTime;
	}
	public String getDataVersion() {
		return DataVersion;
	}
	
	public void setId(String id) {
		this.Id = id;
	}
	public void setTnum(String tnum) {
		this.Tnum = tnum;
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
	public void setFinishedCondition(Integer finished_condition) {
		this.FinishedCondition = finished_condition;
	}
	public void setReviewedCondition(Integer reviewed_condition) {
		this.ReviewedCondition = reviewed_condition;
	}
	public void setBackData(String back_data) {
		this.BackData = back_data;
	}
	public void setCashDetail(String cash_detail) {
		this.CashDetail = cash_detail;
	}
	public void setFinishedTime(String finished_time) {
		this.FinishedTime = finished_time;
	}
	public void setDataVersion(String data_version) {
		this.DataVersion = data_version;
	}
	
}
