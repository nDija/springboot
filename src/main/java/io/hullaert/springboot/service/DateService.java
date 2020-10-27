package io.hullaert.springboot.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

@Service
public class DateService implements DateServiceImpl {

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
