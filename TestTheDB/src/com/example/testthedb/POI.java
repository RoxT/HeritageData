package com.example.testthedb;

public class POI {
	
	private int SiteID;
	private String name;
	private String street;
	private String plaqueLocation;
	private String province;
	private String town;
	
	String designation;
	double latitude;
	double longitude;
	boolean wantToVisit;
	boolean visited;
	
	public POI() {
		wantToVisit = false;
		visited = false;
		
	}

	public int getSiteID() {
		return SiteID;
	}

	public void setSiteID(int siteID) {
		SiteID = siteID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPlaqueLocation() {
		return plaqueLocation;
	}

	public void setPlaqueLocation(String plaqueLocation) {
		this.plaqueLocation = plaqueLocation;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public boolean isWantToVisit() {
		return wantToVisit;
	}

	public void setWantToVisit(boolean wantToVisit) {
		this.wantToVisit = wantToVisit;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	

}
