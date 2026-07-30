package com.ssafy.festival.model.dao;

import java.util.ArrayList;
import java.util.List;

import com.festival.model.dto.FestivalDto;
import com.ssafy.festival.util.FestivalCsvReader;

public class FestivalDaoImpl implements FestivalDao {

    private List<FestivalDto> festivals;

    public FestivalDaoImpl() {
        festivals = new ArrayList<>();
        loadData();
    }

    @Override
    public void loadData() {
        FestivalCsvReader reader = new FestivalCsvReader();

        festivals = reader.read("data/festival.csv");
    }

    @Override
    public List<FestivalDto> searchByRegion(String region) {
        List<FestivalDto> result = new ArrayList<>();

        if (region == null || region.isBlank()) {
            return result;
        }

        // 관광지에서 받은 지역명 정규화
        String searchRegion = normalizeRegion(region);

        for (FestivalDto festival : festivals) {
            // CSV에 저장된 지역명 정규화
            String festivalRegion =
                    normalizeRegion(festival.getRegion());

            if (festivalRegion.equals(searchRegion)) {
                result.add(festival);
            }
        }

        return result;
    }

    /**
     * 지역 약칭을 정식 시·도명으로 변환합니다.
     *
     * 예:
     * 충남 -> 충청남도
     * 서울 -> 서울특별시
     * 경기 -> 경기도
     */
    private String normalizeRegion(String region) {
        if (region == null) {
            return "";
        }

        region = region.trim();

        switch (region) {
            case "서울":
            case "서울시":
            case "서울특별시":
                return "서울특별시";

            case "부산":
            case "부산시":
            case "부산광역시":
                return "부산광역시";

            case "대구":
            case "대구시":
            case "대구광역시":
                return "대구광역시";

            case "인천":
            case "인천시":
            case "인천광역시":
                return "인천광역시";

            case "광주":
            case "광주시":
            case "광주광역시":
                return "광주광역시";

            case "대전":
            case "대전시":
            case "대전광역시":
                return "대전광역시";

            case "울산":
            case "울산시":
            case "울산광역시":
                return "울산광역시";

            case "세종":
            case "세종시":
            case "세종특별자치시":
                return "세종특별자치시";

            case "경기":
            case "경기도":
                return "경기도";

            case "강원":
            case "강원도":
            case "강원특별자치도":
                return "강원특별자치도";

            case "충북":
            case "충청북도":
                return "충청북도";

            case "충남":
            case "충청남도":
                return "충청남도";

            case "전북":
            case "전라북도":
            case "전북특별자치도":
                return "전북특별자치도";

            case "전남":
            case "전라남도":
                return "전라남도";

            case "경북":
            case "경상북도":
                return "경상북도";

            case "경남":
            case "경상남도":
                return "경상남도";

            case "제주":
            case "제주도":
            case "제주특별자치도":
                return "제주특별자치도";

            default:
                return region;
        }
    }
}