package com.ssafy.festival.model.service;

import java.util.List;

import com.ssafy.festival.model.dto.FestivalDto;
import com.ssafy.trip.model.dto.TripDto;
import com.ssafy.trip.model.dto.TripSearchDto;

public interface FestivalService {
	
	public List<FestivalDto> searchByRegion(String region);
	
}
