package com.esun.espbackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.Id;

@Entity
@Table(name = "ACCOUNTINFO")
public class UserEntity {
	@Id
	@Column(name = "account")
	String Account;
	
	@NotNull
    @Column(name = "password")
    String Password;
	
    @Column(name = "role")
    String Role;
	
    @Column(name = "name")
    String Name;

    @Column(name = "branch_code")
    String BranchCode; 
	
	public String getAccount() {
		return Account;
	}
	
	public String getPassword() {
		return Password;
	}
	
	public String getRole() {
		return Role;
	}
	
	public String getName() {
		return Name;
	}
	
	public String getBranchCode() {
		return BranchCode;
	}
	
	public void setAccount(String account) {
		this.Account = account;
	}
	
	public void setPassword(String password) {
		this.Password = password;
	}
	
	public void setRole(String role) {
		this.Role = role;
	}
	
	public void setName(String name) {
		this.Name = name;
	}
	
	public void setBranchCode(String branch_code) {
		this.BranchCode = branch_code;
	}
	
	
}
