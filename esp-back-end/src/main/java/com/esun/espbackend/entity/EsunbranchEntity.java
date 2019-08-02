package com.esun.espbackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ESUNBRANCH")
public class EsunbranchEntity {
	@Id
	@Column(name = "branch_code")
    String BranchCode;
	
	@NotNull
    @Column(name = "name")
    String Name;
    
	@NotNull
    @Column(name = "address")
    String Address;
	
	@NotNull
    @Column(name = "tele")
    String Tele;
	
    @Column(name = "manager_name")
    String ManagerName;
	
	
	public String getBranchCode() {
		return BranchCode;
	}
	public String getName() {
		return Name;
	}
	public String getAddress() {
		return Address;
	}
	public String getTele() {
		return Tele;
	}
	public String getManagerName() {
		return ManagerName;
	}
	
	public void setBranchCode(String branch_code) {
		this.BranchCode = branch_code;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public void setAddress(String address) {
		this.Address = address;
	}
	public void setTele(String tele) {
		this.Tele = tele;
	}
	public void setManagerName(String manager_name) {
		this.ManagerName = manager_name;
	}
}
