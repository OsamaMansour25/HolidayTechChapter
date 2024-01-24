package com.Holiday.API;

import com.Holiday.Entity.HolidayEntity;
import com.Holiday.service.HolidayService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class HolidayAPI {
    HolidayService holidayService;

    public HolidayAPI(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping(value = "isHoliday/{date}")
    public boolean isHoliday(@PathVariable String date) {
        return holidayService.isHoliday(date);
    }

    @GetMapping(value = "nationalHolidays/{startDate}/{endDate}")
    public List<HolidayEntity> getNationalHolidaysInTimePeriod(
            @PathVariable String startDate,
            @PathVariable String endDate) {
        return holidayService.getHolidaysInTimePeriod(startDate, endDate);
    }

}

