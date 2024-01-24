package com.Holiday.service;

import com.Holiday.Entity.HolidayEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class HolidayService {
    @Value("${app.token}")
    String token;
    WebClient client;

    public HolidayService() {
        this.client = WebClient.create();
    }

    public boolean isHoliday(String date) {
        try {
            String URL = "https://api.sallinggroup.com/v1/holidays/is-holiday?date=" + date;
            Boolean isHoliday = WebClient.create()
                    .get()
                    .uri(URL)
                    .header("Authorization", "Bearer " + token)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            return isHoliday != null && isHoliday;
        } catch (Exception e) {
            return false;
        }

    }
    public List<HolidayEntity> getHolidaysInTimePeriod(String startDate, String endDate) {
        try {

            String URL = "https://api.sallinggroup.com/v1/holidays?startDate=" + startDate + "&endDate=" + endDate;
            List<HolidayEntity> allHolidays = WebClient.create()
                    .get()
                    .uri(URL)
                    .header("Authorization", "Bearer " + token)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToFlux(HolidayEntity.class)
                    .collectList()
                    .block();

            List<HolidayEntity> nationalHolidays = allHolidays.stream()
                    .filter(holiday -> holiday.isNationalHoliday())
                    .collect(Collectors.toList());

            return nationalHolidays;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

}
