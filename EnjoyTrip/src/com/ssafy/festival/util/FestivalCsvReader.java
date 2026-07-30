package com.ssafy.festival.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.festival.model.dto.FestivalDto;

public class FestivalCsvReader {

    public List<FestivalDto> read(String filePath) {
        List<FestivalDto> festivals = new ArrayList<>();

        try (
            BufferedReader br = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(filePath),
                    StandardCharsets.UTF_8
                )
            )
        ) {
            String headerLine = br.readLine();

            if (headerLine == null) {
                return festivals;
            }

            List<String> headers = parseCsvLine(headerLine);

            int regionIndex = findColumnIndex(
                headers,
                "광역자치단체명"
            );

            int festivalIndex = findColumnIndex(
                headers,
                "축제명"
            );

            if (regionIndex == -1 || festivalIndex == -1) {
                throw new IllegalArgumentException(
                    "CSV에서 광역자치단체명 또는 축제명 열을 찾을 수 없습니다."
                );
            }

            String line;

            while ((line = br.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }

                List<String> values = parseCsvLine(line);

                if (values.size() <= regionIndex
                    || values.size() <= festivalIndex) {
                    continue;
                }

                String region = values.get(regionIndex).trim();
                String festivalName = values.get(festivalIndex).trim();

                // 예: "01. 서울" → "서울"
                region = region.replaceFirst("^\\d+\\.\\s*", "");

                if (region.isBlank() || festivalName.isBlank()) {
                    continue;
                }

                festivals.add(
                    new FestivalDto(region, festivalName)
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return festivals;
    }

    private int findColumnIndex(
        List<String> headers,
        String columnName
    ) {
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
     * 큰따옴표 내부의 쉼표를 구분자로 처리하지 않는 간단한 CSV 파서
     *
     * 예:
     * 서울,"한강, 여름 축제",2026
     */
    private List<String> parseCsvLine(String line) {
        List<String> values = new ArrayList<>();
        StringBuilder value = new StringBuilder();

        boolean insideQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);

            if (ch == '"') {
                // 큰따옴표 내부의 ""는 실제 따옴표 하나를 의미
                if (
                    insideQuotes
                    && i + 1 < line.length()
                    && line.charAt(i + 1) == '"'
                ) {
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