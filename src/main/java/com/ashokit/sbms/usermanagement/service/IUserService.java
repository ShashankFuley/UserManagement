package com.ashokit.sbms.usermanagement.service;


import java.util.Map;

import com.ashokit.sbms.usermanagement.entity.User;

 
public interface IUserService {
	
	
	/* ==============================Registration Functionality======================================== */
	
	//Method to register user
	Boolean saveUser(User user);
	
	//Method to get countries 
	Map<Long , String> getCountries();
	
	//Method to get state specific to country 
	Map<Long , String> getStates(Long id);
	
	//Method to get cities specific to state
	Map<Long , String> getCities(Long id);
	
	
	/* ==============================Login Functionality=============================================== */
	
	//Method to check whether the account is unlocked or not 
	//Boolean isUnlocked(String email);
	
	//Method to login
	void loginCheck(String email , String password);
	
	
	/* ==============================Unlock Account Functionality====================================== */
	
	//Method to verify temporary password
	Boolean isTempPasswordValid(String email , String tempPassword);
	
	//Method to unlock Account
	Boolean unlockAccount(String email, String tempPassword, String newPassword);
	
	
	/* ==============================Forgot Password Functionality===================================== */
	
	//Method to verify if email is registered or not
	Boolean isEmailRegistered(String email);
	
	//Method to send password to user email
	Boolean sendPasswordToUserEmail(String email);

	
}
