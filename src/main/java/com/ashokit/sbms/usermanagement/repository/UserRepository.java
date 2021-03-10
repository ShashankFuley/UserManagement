package com.ashokit.sbms.usermanagement.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.sbms.usermanagement.entity.User;

public interface UserRepository extends JpaRepository<User, Serializable> {
	
	List<User> findByEmail(String email);
	
	List<User> findByEmailAndPassword(String email , String password);
}