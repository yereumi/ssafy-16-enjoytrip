package com.festival.model.dto;

public class FestivalDto {
	private String festivalName;
	private String place;
	private String region;
	
	public FestivalDto() {};
	
	public FestivalDto(String festivalName, String place, String region) {
		this.festivalName = festivalName;
		this.place = place;
		this.region = region;
	}
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getFestivalName() {
		return festivalName;
	}
	
	public void setFestivalName(String festivalName) {
		this.festivalName = festivalName;
	}
	
	public String getPlace() {
		return place;
	}
	
	public void setPlace(String place) {
		this.place = place;
	}

	@Override
	public String toString() {
		return "FestivalDto [festivalName=" + festivalName + ", place=" + place + "]";
	}
	
	
}
