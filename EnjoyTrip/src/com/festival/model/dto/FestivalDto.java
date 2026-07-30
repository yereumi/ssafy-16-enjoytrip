package com.festival.model.dto;

public class FestivalDto {
	private String festivalName;
	private String place;
	
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
