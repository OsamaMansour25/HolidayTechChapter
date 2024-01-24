package com.Holiday.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class HolidayEntity {
    private String date;
    private String name;
    private boolean nationalHoliday;

}
