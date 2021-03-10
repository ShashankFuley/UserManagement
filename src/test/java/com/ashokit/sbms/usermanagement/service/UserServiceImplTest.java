package com.ashokit.sbms.usermanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ashokit.sbms.usermanagement.entity.City;
import com.ashokit.sbms.usermanagement.entity.Country;
import com.ashokit.sbms.usermanagement.entity.State;
import com.ashokit.sbms.usermanagement.entity.User;
import com.ashokit.sbms.usermanagement.repository.CityRepository;
import com.ashokit.sbms.usermanagement.repository.CountryRepository;
import com.ashokit.sbms.usermanagement.repository.StateRepository;
import com.ashokit.sbms.usermanagement.repository.UserRepository;

class UserServiceImplTest {

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Mock
	UserRepository userRepository;

	@Mock
	CountryRepository countryRepository;
	
	@Mock
	StateRepository stateRepository;
	
	@Mock
	CityRepository cityRepository;
	
	@InjectMocks
	UserServiceImpl userService;
	
	//Method to test getCountries()
	@Test
	public void testGetCountries() {
		List<Country> countries = new ArrayList<>();
		countries.add(new Country(1L,"+91","India"));
		countries.add(new Country(2L,"+1","USA"));
		Mockito.when(this.countryRepository.findAll()).thenReturn(countries);
		Map<Long , String> map = this.userService.getCountries();
		assertEquals("India", map.get(1L));
		assertEquals("USA" , map.get(2L));
	}
	
	
	//Method to test getStates(id) based on country id
	@Test
	public void testGetStates() {
		List<State> states = new ArrayList<>();
		states.add(new State(1L,1L,"Mahrashtra"));
		states.add(new State(2L,1L,"Chhattisgarh"));
		Mockito.when(this.stateRepository.findByCountryId(1L)).thenReturn(states);
		Map<Long , String> map = this.userService.getStates(1L);
		assertEquals("Mahrashtra" , map.get(1L));
		assertEquals("Chhattisgarh" ,  map.get(2L));
	}
	
	//Method to test getCities(id) based on state id
	@Test
	public void testGetCities() {
		List<City> cities = new ArrayList<>();
		cities.add(new City(1L ,"Mumbai", 1L));
		cities.add(new City(2L ,"Pune", 1L));
		Mockito.when(this.cityRepository.findByStateId(1L)).thenReturn(cities);
		Map<Long , String> map = this.userService.getCities(1L);
		assertEquals("Mumbai" , map.get(1L));
		assertEquals("Pune" , map.get(2L));
	}
	
	
	//Method to test true from isTempPasswordValid() method of user service
	@Test
	public void testIsTempPasswordValidTrue() {
		User user = new User();
		user.setPassword("temppassword");
		user.setStatus("LOCKED");
		List<User> users = new ArrayList<>();
		users.add(user);
		Mockito.when(this.userRepository.findByEmailAndPassword("abc@def.com" , "temppassword")).thenReturn(users);
		Boolean result = this.userService.isTempPasswordValid("abc@def.com", "temppassword");
		assertEquals(true , result);
	}
	
	//Method to test false from isTempPasswordValid() method of user service
	@Test
	public void testIsTempPasswordValidFalse() {
		User user = new User();
		user.setPassword("temppassword");
		List<User> users = new ArrayList<>();
		users.add(user);
		Mockito.when(this.userRepository.findByEmail("abc@def.com")).thenReturn(users);
		Boolean result = this.userService.isTempPasswordValid("abc@def.com", "temppasswor");
		assertEquals(false , result);
	}
	
	//Method to test false from isTempPasswordValid() method of user service if no user found
	@Test
	public void testIsTempPasswordValidNull() {
		List<User> users = new ArrayList<>();
		Mockito.when(this.userRepository.findByEmail("abc@def.com")).thenReturn(users);
		Boolean result = this.userService.isTempPasswordValid("abc@def.com", "temppasswor");
		assertEquals(false , result);
	}
	
	//Method to test true from unlockAccount(email,password) method of user service
	@Test
	public void testUnlockAccountTrue() {
		User user = new User();
		user.setStatus("LOCKED");
		user.setPassword("temppassword");
		List<User> users = new ArrayList<>();
		users.add(user);
		Mockito.when(this.userRepository.findByEmailAndPassword("abc@def.com" , "temppassword")).thenReturn(users);
		Boolean unlocked = this.userService.unlockAccount("abc@def.com", "temppassword" , "newpassword");
		assertEquals(true, unlocked);
	}
	
	//Method to test false from unlockAccount(email,password) method of user service
	@Test
	public void testUnlockAccountFalse() {
		List<User> users = new ArrayList<>();
		Mockito.when(this.userRepository.findByEmailAndPassword("abc@def.com" , "temppassword")).thenReturn(users);
		Boolean unlocked = this.userService.unlockAccount("abc@def.com","newpassword" ,"newpassword");
		assertEquals(false, unlocked);
	}
	
	//Method to test true from isEmailRegistered(email) method of user service
	@Test
	public void testIsEmailRegisteredTrue() {
		User user = new User();
		List<User> users = new ArrayList<>();
		users.add(user);
		Mockito.when(this.userRepository.findByEmail("abc@def.com")).thenReturn(users);
		Boolean registered = this.userService.isEmailRegistered("abc@def.com");
		assertEquals(true,registered);
	}
	
	//Method to test false from isEmailRegistered(email) method of user service
	@Test
	public void testIsEmailRegisteredFalse() {
		List<User> users = new ArrayList<>();
		Mockito.when(this.userRepository.findByEmail("abc@def.com")).thenReturn(users);
		Boolean registered = this.userService.isEmailRegistered("abc@def.com");
		assertEquals(false,registered);
	}
}
