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
@Table(name="State_Master")
public class State {
	
	@Id
	@Column(name = "State_Id" , nullable = false , length = 10 , unique = true)
	private Long id;
	
	@Column(name = "Country_Id" , nullable = false , length = 10 )
	private Long countryId;
	
	@Column(name="State_Name" , nullable = false , length = 50 , unique = true)
	private String stateName;
	
	
}
