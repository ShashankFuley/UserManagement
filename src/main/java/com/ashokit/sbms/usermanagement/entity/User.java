package com.ashokit.sbms.usermanagement.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="User_Id")
	private Long id;
	
	@Column(name="First_Name" , nullable = false , length = 50 , unique = false)
	private String firstName;
	
	@Column(name="Last_Name" , nullable = false , length = 50 , unique = false)
	private String lastName;
	
	@Column(name="Email" , nullable = false , length = 50 , unique = true)
	private String email;
	
	@Column(name="Phone_Number" , nullable = false , length = 10 , unique = false)
	private Long phoneNumber;
	
	@Column(name="DOB" , nullable = false , length = 10 , unique = false)
	private String Date; 
	
	@Column(name="Gender" , nullable = false , unique = false)
	private String gender;
	
	@Column(name="Password" , nullable = false , unique = false , length = 100)
	private String password;
	
	@Column(name="Country" , nullable = false , length = 50)
	private String country;
	
	@Column(name = "State" , nullable = false , length = 50)
	private String state;
	
	@Column(name = "City" , nullable = false , length = 50)
	private String city;
	
	@Column(name = "Status" , nullable = false)
	private String status;
	
	@Column(name = "Date_Created")
	@CreationTimestamp
	private Date createdDate;
	
	@Column(name = "Date_Updated")
	@UpdateTimestamp
	private Date lastUpdated;
}
