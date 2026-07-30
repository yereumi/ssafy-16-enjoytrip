package com.festival.model.service;

import java.util.List;

import com.festival.model.dto.FestivalDto;
import com.ssafy.festival.model.dao.FestivalDao;
import com.ssafy.festival.model.dao.FestivalDaoImpl;
import com.ssafy.trip.model.dto.TripSearchDto;
import com.ssafy.trip.util.TouristDestinationSAXParser;

public class FestivalServiceImpl implements FestivalService {

	private FestivalDao festivalDao;
	
	public FestivalServiceImpl() {
		festivalDao = new FestivalDaoImpl();
	}
	
	@Override
	public List<FestivalDto> searchAll(TripSearchDto festivalSearchDto) {
		return festivalDao.searchByRegion(festivalDao);
		return null;
	}
	
}
