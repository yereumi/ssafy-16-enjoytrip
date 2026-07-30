package com.ssafy.festival.model.dao;

import java.util.*;

import com.ssafy.festival.util.FestivalCsvReader;

public class FestivalDaoImpl implements FestivalDao {

	private List<FestivalDto> festivals;

    public FestivalDaoImpl() {
        festivals = new ArrayList<>();
        loadData();
    }
	
	@Override
	public void loadData() {
		// TODO Auto-generated method stub
		FestivalCsvReader reader = new FestivalCsvReader();
		festivals = reader.read("res/2026년_지역축제_개최_계획_현황(공개용).csv");
	}

	@Override
    public List<FestivalDto> searchByRegion(String region) {
        List<FestivalDto> result = new ArrayList<>();

        if (region == null || region.trim().isEmpty()) {
            return result;
        }

        String searchRegion = region.trim();

        for (FestivalDto festival : festivals) {
            if (festival.getRegion().equals(searchRegion)) {
                result.add(festival);
            }
        }

        return result;
    }
}
