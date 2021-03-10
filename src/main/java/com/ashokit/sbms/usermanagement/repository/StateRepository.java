package com.ashokit.sbms.usermanagement.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.sbms.usermanagement.entity.State;

public interface StateRepository extends JpaRepository<State, Serializable> {

	//Method to get list of states based on country id
	List<State> findByCountryId(Long id);
}
