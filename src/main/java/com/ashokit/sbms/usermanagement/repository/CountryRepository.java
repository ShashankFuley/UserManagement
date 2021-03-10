package com.ashokit.sbms.usermanagement.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.sbms.usermanagement.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Serializable> {

}
