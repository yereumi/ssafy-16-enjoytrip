# ssafy-16-enjoytrip

# 개요

> 공공데이터를 활용한 EnjoyTrip 서비스

공공데이터 포털의 전국 관광지 정보 표준 데이터와 Java, STS, Jackson 라이브러리를 활용해 만든 서비스입니다.

# 기능

## 1) 관광지 정보 조회 (F101)
> 관광지 정보를 얻어와 화면에 목록으로 표시
<img width="1163" height="816" alt="스크린샷 2026-07-30 124125" src="https://github.com/user-attachments/assets/16b740ed-c34f-472a-bf26-f8b35535ee79" />


## 2) 관광지 정보 조건 검색 (F102)
> 관광지명, 주소를 조건으로 해당하는 관광지 정보들을 조회
### 주소
<img width="1166" height="834" alt="스크린샷 2026-07-30 124633" src="https://github.com/user-attachments/assets/a94fb3b4-2b13-44a5-ac05-363a4de86ca4" />

### 관광지명
<img width="1166" height="824" alt="스크린샷 2026-07-30 124115" src="https://github.com/user-attachments/assets/a7406eeb-a046-48e4-aae8-9067b88769fd" />

## 3) 관광지 정보 상세 조회 (F103)
> 관광지 목록 화면에 원하는 관광지 클릭 시, 왼쪽 화면에 상세 정보 표시
<img width="1166" height="822" alt="스크린샷 2026-07-30 124559" src="https://github.com/user-attachments/assets/b4554a5d-83b7-4447-a71f-f7964dba05b8" />

## 4) 지역 축제 조회 (F104)
> 관광지 상세 정보 조회 시 '지역 축제 보기'를 클릭하여 국내 지역 목록을 표시
<img width="686" height="493" alt="{1E6C56DB-80CD-4B17-9559-B36657413E15}" src="https://github.com/user-attachments/assets/5ec8962f-24be-4ab3-a107-ac6cc07a4c08" />


# 구현 소스 코드
## TripInfoView
### 01
```java
searchBt.addActionListener(e -> searchTrips());
```

## TripServiceImpl
### 02
```java
return tripDao.search(num);
```

## TripDaoImpl
### 03
```java
for (TripDto tripDto : tripInfo) {
	if (tripDto.getNum() == num) {
			return tripDto;
	}
}
	
return null;
```

## TouristDestinationSAXHandler
### 04
```java
tripDto = new TripDto(num ++);
Random ran = new Random();
tripDto.setImg("image0" + ran.nextInt(9) +".jpg");
trips.add(tripDto);
```

### 05-07
```java
@Override
public void endElement(String uri, String localName, String qName) {
	if (qName.equals("관광지명")) {
		// complete code #05
		tripDto.setTouristDestination(temp);
	} else if (qName.equals("소재지도로명주소")) {
		tripDto.setStreetAddress(temp);
	} else if (qName.equals("소재지지번주소")) {
		tripDto.setLotAddress(temp);
	} else if (qName.equals("위도")) {
		if (temp.length() != 0)
			tripDto.setLat(Double.parseDouble(temp));
	} else if (qName.equals("경도")) {
		// complete code #06
		if (temp.length() != 0)
			tripDto.setLng(Double.parseDouble(temp));
	} else if (qName.equals("관광지소개")) {
		tripDto.setInfo(temp);
	} else if (qName.equals("관리기관전화번호")) {
		// complete code #07
		tripDto.setTel(temp);
	}
}
```

## TouristDestinationSAXParser
### 08
```java
// complete code #08
// 전국관광지정보표준데이터.xml을 loading하도록 처리하세요.
loadData();
```

