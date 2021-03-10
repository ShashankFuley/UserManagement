package com.ashokit.sbms.usermanagement.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.sbms.usermanagement.entity.City;

public interface CityRepository extends JpaRepository<City, Serializable> {

	List<City> findByStateId(Long id);
}
