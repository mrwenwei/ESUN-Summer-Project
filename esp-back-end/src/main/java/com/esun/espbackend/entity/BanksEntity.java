package com.esun.espbackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "BANKS")
public class BanksEntity {
	@Id
	@Column(name = "SWIFTCODE")
	String SWIFTCODE;
	
	@NotNull
    @Column(name = "name")
    String Name;
    
	public String getSWIFTCODE() {
		return SWIFTCODE;
	}
	
	public String getName() {
		return Name;
	}
	
	
	public void setSWIFTCODE(String swiftcode) {
		this.SWIFTCODE = swiftcode;
	}
	
	public void setName(String name) {
		this.Name = name;
	}
	
}
