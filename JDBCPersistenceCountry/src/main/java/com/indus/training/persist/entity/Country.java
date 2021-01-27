package com.indus.training.persist.entity;

import java.io.Serializable;

public class Country implements Serializable

{
	// Step 1: Serializable
	
	private static final long serialVersionUID = 1L;

	// Step 2: Default Constructor
	
	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// Step 3: Define Instance Variables
    // Primary Key column is mandatory
	
	private String countryId;
	private String countryName;
	private int regionId;

	// Step 4: Getter Setter Methods
	
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	
	
	// Step 5: Hashcode & Equals
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryId == null) ? 0 : countryId.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (countryId == null) {
			if (other.countryId != null)
				return false;
		} else if (!countryId.equals(other.countryId))
			return false;
		return true;
	}
	
	// Step 6: Generate toString
	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", countryName=" + countryName + ", regionId=" + regionId + "]";
	}
	
}
