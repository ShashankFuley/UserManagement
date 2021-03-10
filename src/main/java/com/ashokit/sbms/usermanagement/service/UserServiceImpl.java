package com.ashokit.sbms.usermanagement.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ashokit.sbms.usermanagement.entity.City;
import com.ashokit.sbms.usermanagement.entity.Country;
import com.ashokit.sbms.usermanagement.entity.State;
import com.ashokit.sbms.usermanagement.entity.User;
import com.ashokit.sbms.usermanagement.repository.CityRepository;
import com.ashokit.sbms.usermanagement.repository.CountryRepository;
import com.ashokit.sbms.usermanagement.repository.StateRepository;
import com.ashokit.sbms.usermanagement.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {
	
	private CountryRepository countryRepository;
	
	private StateRepository stateRepository;
	
	private CityRepository cityRepository;
	
	private UserRepository userRepository;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
			
	public UserServiceImpl(CountryRepository countryRepository,StateRepository stateRepository,CityRepository cityRepository, UserRepository userRepository) {
		this.countryRepository = countryRepository;
		this.cityRepository = cityRepository;
		this.stateRepository = stateRepository;
		this.userRepository = userRepository;
	}

	//Method to save user
	@Override
	public Boolean saveUser(final User user) {
		logger.debug("**** saveUser() - Execution Started ****");
		try {
			final User savedUser = this.userRepository.save(user);
			if(savedUser != null) {
				logger.info("**** saveUser() - User Saved ****");
				return true;
			}
		}catch(Exception e) {
			logger.error(String.format("**** saveUser() - Exception Occured : **** %s", e.getMessage()));
		}
		logger.info("**** saveUser() - User Not Saved ****");
		logger.debug("**** saveUser() - Execution Ended ****");
		return false;
	}

	//Method to get countries
	@Override
	public Map<Long, String> getCountries() {
		logger.debug("**** getCountries() - Execution Started ****");
		List<Country> countries = this.countryRepository.findAll();
		Map<Long , String> countryMap = countries.stream().collect(Collectors.toMap(Country::getId, Country::getCountryName));
		logger.info("**** getCountries() - Execution Ended ****");
		return countryMap;
		
	}

	//Method to get states based on country id
	@Override
	public Map<Long, String> getStates(final Long id) {
		logger.debug("**** getStates() - Execution Started ****");
		List<State> states = this.stateRepository.findByCountryId(id);
		Map<Long , String> stateMap = states.stream().collect(Collectors.toMap(State::getId, State::getStateName));
		logger.info("**** getStates() - Execution Ended ****");
		return stateMap;
	}

	//Method to get cities based on state id
	@Override
	public Map<Long, String> getCities(final Long id) {
		logger.debug("**** getCities() - Execution Started ****");
		List<City> cities = this.cityRepository.findByStateId(id);
		Map<Long , String> citiesMap = cities.stream().collect(Collectors.toMap(City::getId, City::getCityName));
		logger.info("**** getCities() - Execution Ended ****");
		return citiesMap;
	}


	@Override
	public void loginCheck(String email, String password) {
		// TODO Auto-generated method stub

	}
	
	//Method to validate temporary password
	@Override
	public Boolean isTempPasswordValid(final String email, final String tempPassword) {
		logger.debug("**** isTempPasswordValid() - Execution Started ****");
		List<User> users = this.userRepository.findByEmailAndPassword(email,tempPassword);
		if(users.isEmpty()) {
			logger.info("**** isTempPasswordValid() - User Not Found ****");
			return false;
		}
		User user = users.get(0);
		logger.info("**** isTempPasswordValid() - Execution Ended ****");
		return user.getPassword().equals(tempPassword);
	}

	//Method to unlock account
	@Override
	public Boolean unlockAccount(final String email,final String tempPassword ,String newPassword) {
		logger.debug("**** unlockAccount() - Execution Started ****");
		List<User> users = this.userRepository.findByEmailAndPassword(email,tempPassword);
		if(!users.isEmpty()) {
			User user = users.get(0);
			user.setPassword(newPassword);
			user.setStatus("UNLOCKED");
			logger.info("**** unlockAccount() - User Account Unlocked ****");
			return true;
		}else {
			logger.info("**** unlockAccount() - User Account Not Found ****");
			logger.debug("**** unlockAccount() - Execution Ended ****");
			return false;
		}
	}
	
	//Method to check if email is registered or not
	@Override
	public Boolean isEmailRegistered(final String email) {
		logger.debug("**** isEmailRegistered() - Execution Started ****");
		List<User> users = this.userRepository.findByEmail(email);
		if(users.isEmpty()) {
			logger.info("**** isEmailRegistered() - Email Not Found ****");
			logger.debug("**** isEmailRegistered() - Execution Ended ****");
			return false;
		}else {
			logger.info("**** isEmailRegistered() - Email Found ****");
			return true;
		}		
	}

	@Override
	public Boolean sendPasswordToUserEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
