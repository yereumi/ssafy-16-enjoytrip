package com.ssafy.festival.model.dao;

import java.util.*;

import com.festival.model.dto.FestivalDto;

public interface FestivalDao {
	
	public void loadData();
	
	public List<FestivalDto> searchByRegion(String region);
}
