package com.festival.model.service;

import java.util.List;

import com.festival.model.dto.FestivalDto;
import com.ssafy.trip.model.dto.TripDto;
import com.ssafy.trip.model.dto.TripSearchDto;

public interface FestivalService {
	
	public List<FestivalDto> searchAll(String region);
	
}
