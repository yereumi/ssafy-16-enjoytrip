package com.ssafy.festival.util;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.ssafy.festival.model.dto.FestivalDto;


public class FestivalCsvReader {

    public List<FestivalDto> read(String filePath) {
        List<FestivalDto> festivals = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(filePath),
                        StandardCharsets.UTF_8))) {

        	for (int i = 0; i < 4; i++) {
        	    br.readLine();
        	}

            String headerLine = br.readLine();
            
            br.readLine();
            br.readLine();
            br.readLine();

            if (headerLine == null) {
                return festivals;
            }

            List<String> headers = parseCsvLine(headerLine);

            int regionIndex = findColumnIndex(headers, "광역자치단체명");
            int festivalIndex = findColumnIndex(headers, "축제명");
            int placeIndex = findColumnIndex(headers, "개최 장소");

            System.out.println(headers);
            if (regionIndex == -1 || festivalIndex == -1 || placeIndex == -1) {
                throw new IllegalArgumentException(
                        "CSV에서 광역자치단체명, 축제명 또는 개최장소 열을 찾을 수 없습니다.");
            }

            String line;

            while ((line = br.readLine()) != null) {

                if (line.isBlank()) {
                    continue;
                }

                List<String> values = parseCsvLine(line);

                if (values.size() <= Math.max(regionIndex,
                        Math.max(festivalIndex, placeIndex))) {
                    continue;
                }

                String region = values.get(regionIndex).trim();
                String festivalName = values.get(festivalIndex).trim();
                String place = values.get(placeIndex).trim();

                // 01. 서울 → 서울
                region = region.replaceFirst("^\\d+\\.\\s*", "");

                if (region.isBlank() || festivalName.isBlank()) {
                    continue;
                }

                festivals.add(
                        new FestivalDto(
                                festivalName,
                                place,
                                region
                        )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return festivals;
    }

    private int findColumnIndex(List<String> headers, String columnName) {
        for (int i = 0; i < headers.size(); i++) {
            String header = headers.get(i)
                    .replace("\uFEFF", "")
                    .trim();

            if (header.equals(columnName)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 큰따옴표 내부 쉼표 처리
     */
    private List<String> parseCsvLine(String line) {
        List<String> values = new ArrayList<>();
        StringBuilder value = new StringBuilder();

        boolean insideQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);

            if (ch == '"') {
                if (insideQuotes
                        && i + 1 < line.length()
                        && line.charAt(i + 1) == '"') {
                    value.append('"');
                    i++;
                } else {
                    insideQuotes = !insideQuotes;
                }
            } else if (ch == ',' && !insideQuotes) {
                values.add(value.toString());
                value.setLength(0);
            } else {
                value.append(ch);
            }
        }

        values.add(value.toString());

        return values;
    }
}