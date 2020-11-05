package io.hullaert.springboot.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;


public class DateServiceImpl implements DateService {

    @Override
    public Date getDateTime() {
        return new Date();
    }

    @Override
    public String getCurrentDay() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getDayOfWeek()
                .getDisplayName(TextStyle.FULL, new Locale("en", "EN"))
                .toLowerCase();
    }
}
