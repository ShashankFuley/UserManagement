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
@Table(name="City_Master")
public class City {

	@Id
	@Column(name = "City_Id" , nullable = false ,length = 10 , unique = true)
	private Long id;
	
	@Column(name="City_Name" , nullable = false , length = 50 ,unique = true)
	private String cityName;
	
	@Column(name = "State_Id" , nullable = false , length = 10)
	private Long stateId;
}
