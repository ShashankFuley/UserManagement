package com.ashokit.sbms.usermanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Country_Master")
public class Country {

	@Id
	@Column(name = "Country_Id" , nullable = false , unique = true , length = 10)
	private Long id;
	
	@Column(name= "Country_Code" , nullable = false , unique=true , length = 10)
	private String countryCode;
	
	@Column(name = "Country_Name" , nullable = false , unique = true , length=50 )
	private String countryName;
	
}
