package com.indus.training.persist.dao;

import java.util.Set;

import com.indus.training.persist.entity.Country;
import com.indus.training.persist.exception.PersistException;

public interface ICountryDAO 
{

	public Country searchByCountryId(String countryId) throws PersistException;
	public Country searchByCountryName(String countryName) throws PersistException;
	public Boolean insert(Country countryObj) throws PersistException;
	public Boolean update(String countryId) throws PersistException;
	public Boolean deleteByCountryId(String countryId) throws PersistException;
	public Set <Country> searchByRegionId(int regionId) throws PersistException;
	
}
